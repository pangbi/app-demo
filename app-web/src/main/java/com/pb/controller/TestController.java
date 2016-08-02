package com.pb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pb.bo.TestBo;
import com.pb.controller.base.BaseController;
import com.pb.facde.TestFacde;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/7/21.
 */
@Controller
public class TestController extends BaseController {
    private Log log = LogFactory.getLog(TestController.class);

    @Reference
    private TestFacde testFacde;

    @RequestMapping("/user/test")
    public ModelAndView test(){
        log.debug("TestController begin");
        ModelAndView view = new ModelAndView("test");
        List<TestBo> testBoList = testFacde.test();
        String redisData = testFacde.getDataFromRedis();
        Map<String, Object> avgMap =  testFacde.reqestAvgTime();
        log.debug("testBoList info"+testBoList);
        log.debug("redisData"+redisData);
        log.debug("avgMap"+avgMap);
        view.addObject("testBoList",testBoList);
        view.addObject("redisData",redisData);
        view.addObject("avgMap",avgMap);
        log.debug("TestController end");
        return view;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView view = new ModelAndView("login");
        return view;
    }

    @RequestMapping("/doLogin")
    public void doLogin(String username, String password) throws ServletException, IOException {
        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("username",username);
        userInfo.put("password",password);
        super.session.setAttribute("userInfo",userInfo);
        super.response.sendRedirect("/user/test");
    }

    @RequestMapping("/testSave")
    public void testSave(){
        testFacde.saveData();
    }
}
