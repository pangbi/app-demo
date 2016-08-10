package com.pb.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pb.dto.Constants;
import com.pb.facde.RedisFacde;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2016/7/26.
 */
@Component
@Aspect
public class ControllerAspect {
    Logger log = LoggerFactory.getLogger(ControllerAspect.class);

    @Reference
    private RedisFacde redisFacde;

    /**
     * Around
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice
     * 执行完AfterAdvice，再转到ThrowingAdvice
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.pb.controller.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
       // System.out.println("-----aroundAdvice().invoke-----");
       // System.out.println(" 此处可以做类似于Before Advice的事情");
        //开始时间
        long begin = System.currentTimeMillis();
        //调用核心逻辑
        Object retVal = pjp.proceed();
        //结束时间
        long end = System.currentTimeMillis();
        String clazzName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        log.debug(clazzName+"." + methodName + " runs " + (end - begin) + " ms");
        Map<String,String> map = new HashMap<String,String>();
        String key = clazzName+"."+methodName;
        long time = (end - begin);
        map.put("action",key);
        map.put("time",time+"");
        List<Map<String,String>> actionList =  redisFacde.get(Constants.REDIS_ACTION_REQUEST);
        if(actionList == null){
            actionList = new ArrayList<Map<String,String>>();
        }
        actionList.add(map);
        redisFacde.set(Constants.REDIS_ACTION_REQUEST,actionList);

        //System.out.println(" 此处可以做类似于After Advice的事情");
       // System.out.println("-----End of aroundAdvice()------");
        return retVal;
    }

}
