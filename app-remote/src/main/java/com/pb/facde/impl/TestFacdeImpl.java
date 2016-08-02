package com.pb.facde.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.pb.bo.TestBo;
import com.pb.facde.TestFacde;
import com.pb.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/7/21.
 */
@Service(protocol = {"dubbo"})
public class TestFacdeImpl implements TestFacde {
    private Log log = LogFactory.getLog(TestFacdeImpl.class);

    @Autowired
    private TestService testService;

    public List<TestBo> test() {
        log.debug("TestFacdeImpl begin");
        List<TestBo> list = testService.list();
        log.debug("TestFacdeImpl end");
        return list;
    }

    @Override
    public int saveData() {
        return testService.saveData();
    }

    @Override
    public String getDataFromRedis() {
        return testService.getDataFromRedis();
    }

    @Override
    public Map<String, Object> reqestAvgTime(){
        return testService.reqestAvgTime();
    }
}
