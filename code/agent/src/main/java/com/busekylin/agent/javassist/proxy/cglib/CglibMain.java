package com.busekylin.agent.javassist.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * cglib使用教程 https://dzone.com/articles/cglib-missing-manual
 */
public class CglibMain {
    public static void main(String[] args) {
        Programmer programmer = new Programmer();
        MethodHacker hacker = new MethodHacker();

        /* cglib中的加强器，用来创建动态代理 */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(programmer.getClass());/* 设置要创建动态代理的类 */
        enhancer.setCallback(hacker);/* 设置回调方法，代理类上的所有方法都会调用callback，执行intercept方法 */
        Programmer proxy = (Programmer) enhancer.create();

        proxy.code();
    }
}
