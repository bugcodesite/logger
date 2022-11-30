package site.bc.logger.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import site.bc.logger.Logger;



public class LoggerFactory {
	protected static HashMap<String, Object> _params = new HashMap<String, Object>();
	protected static Class<? extends Logger> _loggerClass = LocalBaseLogger.class;
	protected static HashMap<String, Logger> _loggers = new HashMap<String, Logger>();

	public static void config(Map<String, Object> params) {
		synchronized (LoggerFactory.class) {
			_params.putAll(params);
			for(Entry<String, Logger> e:_loggers.entrySet()) {
				e.getValue().config(e.getKey(),params);
			}
		}
	}

	public static void setImplClass(Class<? extends Logger> loggerClass) {
		if (null == loggerClass) {
			return;
		}
		synchronized (LoggerFactory.class) {
			_loggerClass = loggerClass;
		}
	}

	public static Logger get(String logtype) {
		Logger r = null;
		synchronized (LoggerFactory.class) {
			r = _loggers.get(logtype);
			if (r == null) {
				try {
					_loggers.put(logtype, r = _loggerClass.newInstance());
					r.config(logtype, _params);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return r;
	}

}
