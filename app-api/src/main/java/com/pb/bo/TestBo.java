package com.pb.bo;

import java.io.Serializable;

/**
 * Created by zhangqiang on 2016/7/21.
 */
public class TestBo implements Serializable{

    private static final long serialVersionUID = 3568786797230147446L;

    private int id;

    private String name;

    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
