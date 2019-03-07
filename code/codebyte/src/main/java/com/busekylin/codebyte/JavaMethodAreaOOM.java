package com.busekylin.codebyte;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class JavaMethodAreaOOM {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        while (true) {
            Class<?> dynamicType = new ByteBuddy()
                    .subclass(Object.class)
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Hello World!"))
                    .make()
                    .load(JavaMethodAreaOOM.class.getClassLoader())
                    .getLoaded();

            System.out.println(dynamicType.newInstance().toString());
        }
    }
}
