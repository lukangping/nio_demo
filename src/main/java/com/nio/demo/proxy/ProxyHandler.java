package com.nio.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by roger.lu on 2017/12/22.
 */
public class ProxyHandler implements InvocationHandler {

    private Object object;
    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        System.out.println("ProxyHandler before.");
        Object result = method.invoke(object, args);
        System.out.println("ProxyHandler after.");
        return result;
    }
}

