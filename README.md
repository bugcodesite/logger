# logger
!!
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
!!
![254](https://user-images.githubusercontent.com/28992783/204738511-d6c0d5c7-b643-403b-a0c0-70a16b4fe296.pn

....
