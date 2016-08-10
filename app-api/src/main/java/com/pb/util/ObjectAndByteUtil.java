package com.pb.util;

/**
 * Created by zhangqiang on 2016/8/2.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ObjectAndByteUtil {
    /**
     * 对象转数组
     * @param obj
     * @return
     */
    public static  byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("ip","127.0.0.1");
        Map res = (Map)toObject( toByteArray(map));
        System.err.println(res);
    }
}
