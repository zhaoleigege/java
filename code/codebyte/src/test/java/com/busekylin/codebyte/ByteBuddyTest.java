package com.busekylin.codebyte;

import org.junit.jupiter.api.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyTest {

    @Test
    public void simpleTest() throws InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(this.getClass().getClassLoader())
                .getLoaded();

        System.out.println(dynamicType.newInstance().toString());
    }

}
