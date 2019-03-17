package com.busekylin.agent.classloader;

public class CustomClassLoader extends ClassLoader {
    public Class<?> defineCustomClass(String name, byte[] b, int off, int len) {
        return super.defineClass(name, b, off, len);
    }
}
