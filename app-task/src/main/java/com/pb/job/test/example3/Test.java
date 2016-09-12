package com.pb.job.test.example3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhangqiang on 2016/8/23.
 */
public class Test {
    static void testMyQueue(){
        MyQueue<Integer> myQueue = new MyQueue<Integer>();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        int top =  myQueue.poll();
        System.err.println(top);
        System.err.println(myQueue.poll());
        System.err.println(myQueue.remove());
        System.err.println(myQueue.peek());
        System.err.println(myQueue.poll());
        System.err.println(myQueue.poll());
    }

    static void testMyStack(){
        MyStack<Integer> myStack = new MyStack<Integer>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        int top =  myStack.pop();
        System.err.println(top);
        System.err.println(myStack.pop());
        System.err.println(myStack.pop());
        System.err.println(myStack.peek());
        System.err.println(myStack.pop());
        System.err.println(myStack.empty());
    }
    public static void main(String[] args) throws InterruptedException {
        testMyQueue();
        System.err.println("---------------------------------");
        testMyStack();

        System.err.println("main thread1.");
        new Thread(new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("run");
            }
        }).start();

        System.err.println("main thread2.");
        float a = 0.001f;
        float b = 0.008f;
        System.err.println(a+b);

        final StringBuilder sb = new StringBuilder();
        new Thread(new Thread(){
            public void run(){
                for(int i=0;i<500000;i++){
                    sb.append("111");
                }
            }
        }).start();

        new Thread(new Thread(){
            public void run(){
                for(int i=0;i<500000;i++){
                    sb.append("aaa");
                }
            }
        }).start();
        new Thread(new Thread(){
            public void run(){
                for(int i=0;i<500000;i++){
                    sb.append("bbb");
                }
            }
        }).start();
        new Thread(new Thread(){
            public void run(){
                for(int i=0;i<500000;i++){
                    sb.append("bbb");
                }
            }
        }).start();

        System.err.println(sb.indexOf("aba"));
        //System.err.println(sb.substring(sb.indexOf("aba")-5,sb.indexOf("aba")+5));

        Integer ac = null;
        //System.err.println(ac.equals(1));

        String[] res = "12.02".split("\\.");
        System.err.println(res[0]);

        String sw = "abc";
        /*switch (sw){
            case "2":
                System.err.println("sw error");
                break;
        }*/

        String tmp =  "<?xml version=\"1.0\" encoding=\"ISO8859-1\"?><DATA><RESPONSE><STSCOD>%s</STSCOD><STSMSG>%s</STSMSG></RESPONSE></DATA>";
        System.err.println(String.format(tmp,"100","成功"));

        String abc = null;
        System.err.println(abc+"");

        System.err.println(Boolean.valueOf("true"));

        HashMap hashMap = new HashMap();
        Object rh = hashMap.put("hello","diulei");
        rh = hashMap.put("hello","world");
        System.out.println("rh:"+rh);

        Thread thread = new Thread();
        thread.setDaemon(true);//守护进程

    }
}

class MyQueue<E>{
    private Stack stack1 ;
    private Stack stack2 ;
    public MyQueue(){
        this.stack1 = new Stack();
        this.stack2 = new Stack();
    }

    /**
     * 入队列
     * @param e
     */
    void offer(E e){
        stack1.push(e);
    }

    /**
     * 出队列
     * @return
     */
    public E poll(){
        E e = null;
        while (!stack1.empty())//stack1元素反向复制到stack2
            stack2.push(stack1.pop());
        if(!stack2.isEmpty()){
            e = (E)stack2.pop();//弹出stack1的栈底元素
            while (!stack2.empty())//stack2元素反向复制到stack1
                stack1.push(stack2.pop());
        }
        return e;
    }

    /**
     * 入队列
     * @param e
     */
    public void add(E e){
        stack1.push(e);
    }

    /**
     * 删除队列头部元素，队列为空，则报错
     * @return
     */
    public E remove(){
        if(stack1.empty())
            throw new RuntimeException("queue is null");
        E e = null;
        while (!stack1.empty())//stack1元素反向复制到stack2
            stack2.push(stack1.pop());
        if(!stack2.isEmpty()){
            e = (E)stack2.pop();//弹出stack1的栈底元素
            while (!stack2.empty())//stack2元素反向复制到stack1
                stack1.push(stack2.pop());
        }
        return e;
    }

    /**
     * 获取队列头部元素但不删除
     * @return
     */
    public E peek(){
        E e = null;
        while (!stack1.empty())//stack1元素反向复制到stack2
            stack2.push(stack1.pop());
        if(!stack2.isEmpty()){
            e = (E)stack2.peek();//获取stack1的栈底元素
            while (!stack2.empty())//stack2元素反向复制到stack1
                stack1.push(stack2.pop());
        }
        return e;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean empty(){
        return  stack1.empty();
    }

}

class MyStack<E>{
    private LinkedList queue1;
    private LinkedList queue2;

    public MyStack(){
        queue1 = new LinkedList();
        queue2 = new LinkedList();
    }

    /**
     * 入栈
     * @param item
     * @return
     */
    public E push(E item) {
        queue1.add(item);
        return item;
    }

    /**
     * 出栈
     * @return
     */
    public  E pop() {
        E e = null;
        while (!queue1.isEmpty())
            queue2.offer(queue1.removeLast());
        e = (E)queue2.poll();
        while (!queue2.isEmpty())
            queue1.offer(queue2.removeLast());
        return e;
    }


    public  E peek() {
        E e = null;
        while (!queue1.isEmpty())
            queue2.offer(queue1.poll());
        e = (E)queue2.peek();
        while (!queue2.isEmpty())
            queue1.offer(queue2.poll());
        return e;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}