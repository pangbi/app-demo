package com.pb.facde;

import com.pb.bo.TestBo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/7/21.
 */
public interface TestFacde {

    List<TestBo> test();

    int saveData();

    String getDataFromRedis();

    public Map<String, Object> reqestAvgTime();
}
