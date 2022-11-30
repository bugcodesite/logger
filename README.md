# logger
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

![254](https://user-images.githubusercontent.com/28992783/204740846-2246bed7-710f-438f-8ccb-8643171f8501.png)
