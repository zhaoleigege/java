package com.busekylin.codebyte.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class LoggingProxy {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                .method(named("load")).intercept(MethodDelegation.to(LoggerInterceptor.class))
                .make()
                .load(LoggingProxy.class.getClassLoader())
                .getLoaded()
                .newInstance();

        loggingDatabase.load("赵磊");
    }
}