package com.crazy.portal.aop;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc: 操作日志
 * @Author: Bill
 * @Date: created in 11:36 2019-06-17
 * @Modified by:
 */
@Aspect
@Component
@Slf4j
public class OperationAspect extends BaseController {

    @After("@annotation(operationLog)")
    public void before(JoinPoint point, OperationLog operationLog) {
        try {
            Object[] objects = point.getArgs();
            String params = JSON.toJSONString(objects);
            Signature signature = point.getSignature();
            StringBuilder invoke = new StringBuilder(signature.getDeclaringTypeName())
                    .append(".")
                    .append(signature.getName())
                    .append("\n访问参数：")
                    .append(params);

            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String url = request.getRequestURI();
            String userName = super.getCurrentUser().getLoginName();

            String businessKey = String.format("%s.%s",point.getTarget().getClass().getSimpleName(),signature.getName());
            log.info("prepare sending log to server "+ businessKey);
            log.info("userName: {},url:{},invoke:{},businessKey:{}",userName,url,invoke.toString(),businessKey);
        } catch (Exception e) {
            log.error("The global operations log intercepts exceptions",e);
        }
    }
}
