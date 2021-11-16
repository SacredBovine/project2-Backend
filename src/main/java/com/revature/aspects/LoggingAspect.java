package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("within(com.revature.controller.*)")
    public void logControllerMethods(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget() + " invoked " + joinPoint.getSignature() /*+ " with param " + joinPoint.getArgs()[0].toString()*/);
    }

    @Before("within(com.revature.service.*)")
    public void logServiceMethods(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget() + " invoked " + joinPoint.getSignature() /*+ " with param " + joinPoint.getArgs()[0].toString()*/);
    }

    @Before("execution(* set*(..)) && within(com.revature.models.*)")
    public void logSetters(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget() + " invoked " + joinPoint.getSignature() /*+ " with param " + joinPoint.getArgs()[0].toString()*/);
    }

    @Before("execution(* login(..)) && within(com.revature.service.LoginService)")
    public void logLogin(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget() + " invoked " + joinPoint.getSignature() /*+ " with param " + joinPoint.getArgs()[0].toString()*/);
    }

    @Before("execution(* logout(..)) && within(com.revature.service.LoginService)")
    public void logLogout(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget() + " invoked " + joinPoint.getSignature()/* + " with param " + joinPoint.getArgs()[0].toString()*/);
    }


}
