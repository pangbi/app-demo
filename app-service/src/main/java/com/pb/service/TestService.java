package com.pb.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pb.bo.TestBo;
import com.pb.dao.TestDao;
import com.pb.dto.Constants;
import com.pb.facde.RedisFacde;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhangqiang on 2016/7/25.
 */
@Service
public class TestService {

    private Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestDao testDao;

    @Reference
    private RedisFacde redisFacde;

    public List<TestBo> list() {
        log.debug("TestService begin ...");
        List<TestBo> list = testDao.list();
        log.debug("list info: " + list);
        log.debug("TestService end ...");
        return list;
    }

    public int saveData(){
        TestBo testBo = new TestBo();
        testBo.setName("张三子");
        testBo.setRemark("就是干"+(new Date()));
        testDao.save(testBo);
        //if(1==1)
        //    throw new RuntimeException("save err");
        return 1;
    }

    public String getDataFromRedis() {
        redisFacde.set("pb", "胖比");
        return redisFacde.get("pb");
    }


    public Map<String, Object> reqestAvgTime() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, String>> list = redisFacde.get(Constants.REDIS_ACTION_REQUEST);
        if (list == null || list.size() == 0)
            return null;
        long avgTime = 0;
        long count = 0;
        long totalTime = 0;
        long time;
        String key;
        Map<String, List<Long>> actionMap = new HashMap<String, List<Long>>();
        List<Long> tempList;
        for (Map<String, String> map : list) {
            count++;
            time = Long.valueOf(map.get("time"));
            totalTime += time;
            key = map.get("action");
            if (actionMap.containsKey(key))
                tempList = actionMap.get(key);
            else
                tempList = new ArrayList<Long>();
            tempList.add(time);
            actionMap.put(key,tempList);
        }
        if (count == 0 || totalTime == 0)
            return null;
        avgTime = totalTime / count;
        result.put("avgTime", avgTime);//接口响应平均时间

        List<Map<String,String>> actionTimeList = new ArrayList<Map<String,String>>();
        Map<String,String> tempMap;
        long tempTotalTime = 0 ;
        Set<Map.Entry<String, List<Long>>> entries= actionMap.entrySet();
        for(Map.Entry<String, List<Long>> entry : entries){
            tempMap = new HashMap<String,String>();
            for(Long tm :entry.getValue())
                tempTotalTime+=tm;
            tempMap.put("action",entry.getKey());
            tempMap.put("avgTime",tempTotalTime/entry.getValue().size()+"");
            tempMap.put("count",entry.getValue().size()+"");
            tempTotalTime = 0;
            actionTimeList.add(tempMap);
        }
        Collections.sort(actionTimeList,new Comparator<Map<String,String>>(){
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return Integer.valueOf(o2.get("avgTime"))-Integer.valueOf(o1.get("avgTime"));
            }
        });
        result.put("avgTime", avgTime);//action响应平均时间
        result.put("singleAction", actionTimeList);//单个action响应时间和 次数
        return result;
    }
}
