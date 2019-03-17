package com.busekylin.agent.javassist.proxy.javassist;

import javassist.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TicketProxyMain {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool classPool = new ClassPool(true);
        classPool.importPackage("com.das.his.javaagent.javassist.proxy.javassist.Ticket");

        CtClass ctClass = classPool.makeClass("com.das.his.javaagent.javassist.proxy.TicketProxy");

        /* 设置接口 */
        CtClass tiketInterface = classPool.get("com.das.his.javaagent.javassist.proxy.javassist.Ticket");
        ctClass.setInterfaces(new CtClass[]{tiketInterface});

        /* 设置域 */
        CtField ctField = CtField.make("private Ticket ticket;", ctClass);
        ctClass.addField(ctField);

        /* 设置构造函数 */
        CtClass ticketClass = classPool.get("com.das.his.javaagent.javassist.proxy.javassist.DefaultTicketImpl");
        CtClass[] arrays = new CtClass[]{ticketClass};
        CtConstructor constructor = CtNewConstructor.make(arrays, null, CtNewConstructor.PASS_NONE, null, null, ctClass);
        constructor.setBody("{this.ticket=$1;}");
        ctClass.addConstructor(constructor);

        /* 添加一个新方法 */
        CtMethod method = CtMethod.make("public void sell(){}", ctClass);
        method.setBody("{System.out.println(\"开始代理售票\");ticket.sell();System.out.println(\"结束代理售票\");}");
        ctClass.addMethod(method);

        Class clazz = ctClass.toClass();
        Constructor proxyConstructor = clazz.getDeclaredConstructor(DefaultTicketImpl.class);
        Ticket ticket = (Ticket) proxyConstructor.newInstance(new DefaultTicketImpl());
        ticket.sell();
    }
}

