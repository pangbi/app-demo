package com.pb.inteceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhangqiang on 2016/7/25.
 * 在preHandle中，可以进行编码、安全控制等处理；
 在postHandle中，有机会修改ModelAndView；
 在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
 */
@Component
public class LoginInteceptor extends HandlerInterceptorAdapter {
    Log log = LogFactory.getLog(LoginInteceptor.class);
    private List<String> mappingURLList;//利用正则映射到需要拦截的路径列表
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        Object session = request.getSession().getAttribute("userInfo");
        log.debug("request url : "+url);
        log.debug("session  : "+session);
        for(String mappingURL: mappingURLList){
            log.debug("url.matches("+mappingURL+")"+url.matches(mappingURL));
            if (url.matches(mappingURL) && session==null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object paramObject, ModelAndView modelAndView) throws Exception {
        Object userInfo = request.getSession().getAttribute("userInfo");
        if(modelAndView == null)
            modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo",userInfo);
        log.debug("postHandle working...");
    }

    public List<String> getMappingURLList() {
        return mappingURLList;
    }

    public void setMappingURLList(List<String> mappingURLList) {
        this.mappingURLList = mappingURLList;
    }

    public static void main(String args[]){
        String reg = "^.*/user/.*$";
        String url = "http://127.0.0.1:8080/index";
        String url1 = "http://127.0.0.1:8180/login";
        String url2 = "http:/user/155";
        System.out.println(url.matches(reg));
        System.out.println(url1.matches(reg));
        System.out.println(url2.matches(reg));
    }
}
