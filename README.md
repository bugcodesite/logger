# site.bc.logger
### simple logger for java application,global static,saft invoke
```
//global static,saft invoke
import site.bc.logger.Logger;
public  class test{
    public static void main(String...args){
        Logger.config("/var/log/test");
        Logger.out("ok");
        System.out.println("test end,please see "+"/var/log/test");
    }
}
```

/**
### config options:
	basePath ="/var/log/";
	attachTimestamp =true;
	storeByDay =true;
	fileExt =".log";
	nolog="" //or "nolog";
**/
```
```
public class Logger {
	public static void config(Map<String, Object> params) {
		//...
	}
	public static void config(String basePath,fileExt,storeByDay,attachTimestamp) {
		//...
	}
	public static boolean isDebug(){
		//...
	}
	
	public static void out(Object... msgs){
		//...
	}

	public static void err(Object... msgs){
		//...
	}

	public static void info(Object... msgs){
		//...
	}

	public static void warn(Object... msgs){
		//...
	}

	public static void log(String logtype, Object... msgs){
		//...
	}
}
```
