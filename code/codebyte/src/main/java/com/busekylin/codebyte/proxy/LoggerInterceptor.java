package com.busekylin.codebyte.proxy;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.List;
import java.util.concurrent.Callable;

public class LoggerInterceptor {
    public static List<String> log(@SuperCall Callable<List<String>> zuper)
            throws Exception {
        System.out.println("访问数据库之前");
        try {
            return zuper.call();
        } finally {
            System.out.println("访问数据库之后");
        }
    }
}
