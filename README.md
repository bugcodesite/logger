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
