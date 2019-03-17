package com.busekylin.agent.javassist.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodHacker implements MethodInterceptor {
    /* 实现方法拦截器接口 */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("拦截方法执行前");
        methodProxy.invokeSuper(o, objects);
        System.out.println("拦截方法执行后");

        return null;
    }
}
