package com.hackday.special;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Arrays;

@Configurable
@Aspect
public class ControllerProxy {

    @Before(value = "execution (* com.hackday.controller.*.*(..))")
    public void request(JoinPoint joinPoint) {
        LoggingUtility.i("Controller: " + joinPoint.getSignature() + " Params: " + Arrays.toString(joinPoint.getArgs()));
    }
}
