package com.busekylin.agent.javassist.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHanlder implements InvocationHandler {
    private ElectricCar car;

    public DynamicProxyHanlder(ElectricCar car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理执行：" + method.getName() + "方法");
        method.invoke(car, args);
        System.out.println("结束代理执行：" + method.getName() + "方法");
        return null;
    }
}
