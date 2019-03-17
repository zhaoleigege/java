package com.busekylin.agent.agent;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JVMAgent {
    public static void premain(String args, Instrumentation inst){
        System.out.println("java探针性能监控");

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            Metric.printMemoryInfo();
            Metric.printGCInfo();
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
