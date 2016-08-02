package com.pb.dao;

import com.pb.bo.TestBo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/7/25.
 */
public interface TestDao {
    List<TestBo> list();
    int save(TestBo testBo);
}
