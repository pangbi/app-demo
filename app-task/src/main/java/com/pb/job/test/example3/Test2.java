package com.pb.job.test.example3;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhangqiang on 2016/8/24.
 */
public class Test2 {
    static{
        int x=5;
    }
    static int x,y;
    public static void myMethod( ){
        y=x++ + ++x;
        System.out.println(y+":"+x);
    }
    public static void main(String[] args){
       /* x--;
        myMethod( );
        System.out.println(x+y+ ++x);*/

        Integer i = 200;
        Integer j = 200;
        System.err.println(i==j);
    }
}


