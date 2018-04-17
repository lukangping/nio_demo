package com.nio.demo.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by roger.lu on 2017/12/22.
 */
public class DynamicProxy {

    public static void main(String[] args) {
        //委托类
        SubjectImpl subjectImpl = new SubjectImpl();
        ProxyHandler proxyHandler = new ProxyHandler(subjectImpl);

        //代理类
        Subject subject = (Subject)Proxy.newProxyInstance(
            SubjectImpl.class.getClassLoader(),
            SubjectImpl.class.getInterfaces(),
            proxyHandler);
        subject.request();
    }
}

