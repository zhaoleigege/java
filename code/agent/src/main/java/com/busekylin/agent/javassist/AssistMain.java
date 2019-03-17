package com.busekylin.agent.javassist;

import javassist.*;

import java.lang.reflect.InvocationTargetException;

public class AssistMain {
    public static void main(String[] args) throws CannotCompileException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassPool classPool = new ClassPool(true);
        CtClass ctClass = classPool.makeClass("com.das.his.javaagent.javassist.Student");

        CtMethod ctMethod = CtNewMethod.make("public void code(){}", ctClass);
        ctMethod.setBody("System.out.println(\"程序员\");");
        ctClass.addMethod(ctMethod);

        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();

        clazz.getMethod("code", null).invoke(obj, null);
    }
}
