package site.bc.logger;


import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import site.bc.logger.support.LoggerFactory;

public abstract class Logger {
	public static boolean isDebug() {
		for(String e:ManagementFactory.getRuntimeMXBean().getInputArguments()) {
			 if (e.startsWith("-Xrunjdwp") || e.startsWith("-agentlib:jdwp")) {
				 return true;
			 }
		}
		return false;
	}
	public static void out(Object... msgs) {
		log("out", msgs);
	}

	public static void err(Object... msgs) {
		log("err", msgs);
	}

	public static void info(Object... msgs) {
		log("info", msgs);
	}

	public static void warn(Object... msgs) {
		log("warn", msgs);
	}

	public static void log(String logtype, Object... msgs) {
		LoggerFactory.get(logtype).doLog(msgs);
	}

	public abstract void config(String logtype, Map<String, Object> params);

	public abstract void doLog(Object[] msgs);

	public abstract void close();
	/**
	 			basePath = (String) params.getOrDefault("basePath", "/var/log/");
				_attachTimestamp = (boolean) params.getOrDefault("attachTimestamp", true);
				_storeByDay = (boolean) params.getOrDefault("storeByDay", true);
				_fileExt = (String) params.getOrDefault("fileExt", ".log");
				_nolog=(String) params.getOrDefault("nolog", "nolog");
	 * @param params
	 */
	public static void config(Map<String, Object> params) {
		LoggerFactory.config(params);
	}
	public static void config(String...basePath_fileExt_storeByDay_attachTimestamp) {
		Map<String, Object> params=new HashMap<String,Object>();
		if(basePath_fileExt_storeByDay_attachTimestamp.length>0) {
			params.put("basePath", basePath_fileExt_storeByDay_attachTimestamp[0]);
		}
		if(basePath_fileExt_storeByDay_attachTimestamp.length>1) {
			params.put("fileExt", basePath_fileExt_storeByDay_attachTimestamp[1]);
		}
		if(basePath_fileExt_storeByDay_attachTimestamp.length>2) {
			params.put("basePath", (!("false".equalsIgnoreCase(basePath_fileExt_storeByDay_attachTimestamp[2]))));
		}
		if(basePath_fileExt_storeByDay_attachTimestamp.length>3) {
			params.put("basePath", (!("false".equalsIgnoreCase(basePath_fileExt_storeByDay_attachTimestamp[3]))));
		}
		if(basePath_fileExt_storeByDay_attachTimestamp.length>4) {
			params.put("nolog", basePath_fileExt_storeByDay_attachTimestamp[4]);
		}
		LoggerFactory.config(params);
	}
}
