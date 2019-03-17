package com.busekylin.agent.javassist.proxy.jdk;

public class ElectricCar implements Rechargable, Vehicle {
    @Override
    public void recharge() {
        System.out.println("电充车重复使用");
    }

    @Override
    public void drive() {
        System.out.println("电动车启动");
    }
}
