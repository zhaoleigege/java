package com.busekylin.shutdown;

public class TestApplication {
    public static void main(String[] args) {
        TestApplication application = new TestApplication();

        System.out.println("开始执行任务");
        application.execute();
        System.out.println("任务执行完毕");
    }

    public TestApplication() {
        this.shutdownHook = new ShutdownHook(Thread.currentThread());
    }

    private ShutdownHook shutdownHook;

    public void execute() {
        while (!shutdownHook.isShutdown()) {
            System.out.println("开始休眠");
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                System.out.println("休眠被唤醒");
            }
            System.out.println("现在没有休眠");
        }

        System.out.println("整个线程执行完毕");
    }
}
