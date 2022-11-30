# logger
simple logger for java application
{{
//global static,saft invoke
    import site.bc.logger.Logger;
    public  class test{
        public static void main(String...args){
            Logger.config("/var/log/test");
            Logger.out("ok");
            System.out.println("test end,please see "+"/var/log/test");
        }
    }
//
}}
![254](https://user-images.githubusercontent.com/28992783/204738243-46792ca6-92c1-4c2a-b1ed-445028ed1a23.png)
