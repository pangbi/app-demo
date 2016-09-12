package com.pb.job.test.example3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangqiang on 2016/9/6.
 */
public class MapTest {
    public static void main(String args[]){
        Map<Object,Object> map = new HashMap<Object, Object>();
        TreeMap<Object,Object> treeMap = new TreeMap<Object, Object>();
        for(int i=0;i<1000000;i++){
            map.put(i,"value:"+i);
            treeMap.put(i,"value:"+i);
        }
        for(int i=0;i<10000;i++){
            Long begin = System.nanoTime();
            treeMap.get(i);
            Long end = System.nanoTime();
            System.out.println((end-begin));
        }
    }
}

