import org.junit.Test;

public class SingleModel {


    static class SingleOne{

        private static final SingleOne singleOne = new SingleOne();
        private SingleOne(){};
        public static SingleOne getSingleOne(){return singleOne;}

    }

    static class SingleOne2{

        private static volatile SingleOne2 singleOne2 = null;
        private SingleOne2(){};
        public static SingleOne2 getSingleOne2(){
            if(singleOne2 == null){
                synchronized (SingleOne2.class){
                    if(singleOne2 == null){
                        singleOne2 = new SingleOne2();
                    }
                }
            }
            return singleOne2;
        }

    }

    static class SingleOne3{

        private SingleOne3(){
            //初始化
        }


        private static class Sg3Help{
            private static SingleOne3 singleOne3 = new SingleOne3();
        }

        public static SingleOne3 getInstance(){
            return Sg3Help.singleOne3;
        }



    }


    @Test
    public void test(){

        //形态一 基本不用
        SingleOne singleOne = SingleOne.getSingleOne();

        //形态二 偶尔用 双重锁校验
        SingleOne2 singleOne2 = SingleOne2.getSingleOne2();

        //形态三 静态内部类 经常用
        SingleOne3 sg3 = SingleOne3.getInstance();




    }


}
