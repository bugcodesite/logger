package site.bc.logger.support;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import site.bc.logger.Logger;

public class LocalBaseLogger extends Logger {
	private static boolean isdebug=false;
	private boolean _attachTimestamp = true;
	private String _name = "log";
	private boolean _storeByDay;
	private String _fileExt = ".log";
	private java.io.PrintStream _out = null;
	private String _basePath = "/var/logs/logger/";
	private String _curfn;
	private String _nolog="nolog";
	static {
		isdebug=isDebug();
	}
	@Override
	public void config(String logtype, Map<String, Object> params) {
		close();
		synchronized (this) {
			_name = logtype;
			if (params != null) {
				_basePath = (String) params.getOrDefault("basePath", "/var/log/");
				_attachTimestamp = (boolean) params.getOrDefault("attachTimestamp", true);
				_storeByDay = (boolean) params.getOrDefault("storeByDay", true);
				_fileExt = (String) params.getOrDefault("fileExt", ".log");
				_nolog=(String) params.getOrDefault("nolog", "nolog");
				if (_fileExt == null) {
					_fileExt = ".log";
				} else if (!_fileExt.startsWith(".")) {
					_fileExt = "." + _fileExt;
				}
			}
		}
	}

	@Override
	public void close() {
		synchronized (this) {
			if (_out != null) {
				try {
					_out.close();
				} catch (Exception e) {

				} finally {
					_out = null;
				}
			}
		}

	}

	protected PrintStream get_out() {
		try {
			
			String fn=this._name;
			if(this._storeByDay) {
				fn=fn+"_"+new java.text.SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
			}
			fn=fn+this._fileExt;
			if(_curfn!=fn) {
				//关闭当前
				if (_out != null) {
					try {
						_out.close();
					} catch (Exception e) {

					} finally {
						_out = null;
						_curfn=fn;
					}
				}
			}
			if(_out==null) {
				File f=new File(new File(this._basePath),fn);
				File fp=f.getParentFile();
				if(fp.isDirectory()&&fp.exists()) {
					
				}else {
					if(fp.mkdirs());
				}
				_out= new PrintStream(new java.io.FileOutputStream(f, true),true, "utf-8");
				
			}
			return _out;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void doLog(Object[] msgs) {
		synchronized (this) {
			if(new File(_basePath,_nolog).exists()) {
				//nolog
				return;
			}
			StringBuffer sb = new StringBuffer();
			try {
				if(this._attachTimestamp) {
					sb.append(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis()));
					sb.append("\t");
				}
				for(int i=0;i<msgs.length;i++) {
					if(i>0) {
						sb.append("\t");
					}
					if(msgs[i]==null) {
						sb.append("null");
					}else if(msgs[i] instanceof Exception) {
						StringWriter ss=null;
						try {
							PrintWriter s=new PrintWriter(ss=new java.io.StringWriter());
							((Exception)msgs[i]).printStackTrace(s);
							s.flush();
						}catch(Exception e) {
							
						}finally {
							if(ss!=null) {
								sb.append(ss);
								try {
									ss.close();
								}catch(Exception e1) {
									
								}
							}
						}
						
					}else {
						sb.append(msgs[i]);
					}
				}
			} catch (Exception e) {

			}
			if(isdebug) {
				System.out.println(sb);
			}
			PrintStream ps = get_out();
			if (ps != null) {
				synchronized (ps) {
					ps.println(sb.toString());
				}
			}
		}
	}

}
