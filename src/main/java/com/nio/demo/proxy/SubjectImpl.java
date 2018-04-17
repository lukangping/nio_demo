package com.nio.demo.proxy;

/**
 * Created by roger.lu on 2017/12/22.
 */


public class SubjectImpl implements Subject {
    @Override
    public void request() {
        System.out.println("request.");
    }
}


