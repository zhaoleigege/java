package com.busekylin.transactional.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionInvocationHandler implements InvocationHandler {
    private Object proxyObject;
    private TransactionManager transactionManager;

    public TransactionInvocationHandler(Object proxyObject, TransactionManager transactionManager) {
        this.proxyObject = proxyObject;
        this.transactionManager = transactionManager;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        transactionManager.start();

        Object result = null;

        try {
            result = method.invoke(proxyObject, args);
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }

        return result;
    }
}
