package com.pb.facde.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.pb.bo.TestBo;
import com.pb.facde.TestFacde;
import com.pb.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/7/21.
 */
@Service(protocol = {"dubbo"})
public class TestFacdeImpl implements TestFacde {
    private Logger log = LoggerFactory.getLogger(TestFacdeImpl.class);

    @Autowired
    private TestService testService;

    public List<TestBo> test() {
        log.debug("TestFacdeImpl begin");
        List<TestBo> list = testService.list();
        log.debug("TestFacdeImpl end");
        return list;
    }

    public int saveData() {
        return testService.saveData();
    }

    public String getDataFromRedis() {
        return testService.getDataFromRedis();
    }

    public Map<String, Object> reqestAvgTime(){
        return testService.reqestAvgTime();
    }
}
