package com.pb.job.test.example3;

/**
 * Created by zhangqiang on 2016/8/30.
 */
public class Test3 {
    public static void main(String args[]){
        Father1 father1 = new Father1();
        father1.foo(1,"bac","ef");
    }
}

class Father1{
    public final void fMethod(){
        System.out.println("father method");
    }

    public void foo(int b,String ...args){
        System.out.println(b);
        for(String arg : args){
            System.out.print(arg + '\t');
        }
    }
}

class Sun1 extends Father1{
        /*@Override
        public  void fMethod(){
            System.out.println("father method");
        }*/
}