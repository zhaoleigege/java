package com.busekylin.agent.classloader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestClassLoader {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        File file = new File(".");
        InputStream inputStream = new FileInputStream(file.getCanonicalFile() + "/target/classes/com/das/his/javaagent/classloader/Person.class");

        int count = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int hasRead;
        byte[] bytes = new byte[1024];

        while ((hasRead = inputStream.read(bytes)) != -1) {
            count += hasRead;
            outputStream.write(bytes, 0, hasRead);
        }

        CustomClassLoader classLoader = new CustomClassLoader();
        Class<?> clazz = classLoader.defineCustomClass("com.das.his.javaagent.classloader.Person", outputStream.toByteArray(), 0, count);

        Constructor constructor=clazz.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);

        Object obj = constructor.newInstance("test", 22);
        clazz.getMethod("hello", null).invoke(obj,  null);

    }
}
