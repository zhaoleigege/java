package com.busekylin.agent.javassist.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Proxy;

public class JDKDynamicProxyMain {
    public static void main(String[] args) {
        /* 动态代理类 */
        Object obj = Proxy.newProxyInstance(JDKDynamicProxyMain.class.getClassLoader(),
                ElectricCar.class.getInterfaces(),
                new DynamicProxyHanlder(new ElectricCar()));

        ((Vehicle) obj).drive();
        ((Rechargable) obj).recharge();

        ElectricCar car = new ElectricCar();

        byte[] classFile = ProxyGenerator.generateProxyClass("ElectricCarProxy", car.getClass().getInterfaces());
        String path = car.getClass().getResource(".").getPath();

        System.out.println(path);

        try (OutputStream outputStream = new FileOutputStream(path + "ElectricCarProxy.class")) {
            outputStream.write(classFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
