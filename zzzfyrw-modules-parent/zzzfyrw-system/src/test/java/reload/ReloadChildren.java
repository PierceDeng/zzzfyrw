package reload;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ReloadChildren extends reloadParent{


    public void test1(){

        System.out.println("ok");

    }

    @Test
    public void test1(String hh){


//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        countDownLatch.countDown();
//
//
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
//
//        cyclicBarrier.getParties();


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        System.out.println(Arrays.toString(list.toArray()));


        return ;
    }


    public String test2(){
        return "ok";
    }



}
