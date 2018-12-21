package com.busekylin.shutdown;

public class ShutdownHook extends Thread {
    private Thread mainThread;
    private boolean shutdown;

    public ShutdownHook(Thread mainThread) {
        super();
        this.mainThread = mainThread;
        this.shutdown = false;

        Runtime.getRuntime().addShutdownHook(this);
    }

    public boolean isShutdown() {
        return this.shutdown;
    }

    @Override
    public void run() {
        System.out.println("接收到关闭信号");
        this.shutdown = true;

        mainThread.interrupt();

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            System.out.println("主线程出现错误：" + e.getMessage());
        }

        System.out.println("应用成功关闭");
    }
}
