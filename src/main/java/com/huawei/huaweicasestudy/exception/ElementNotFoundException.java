package com.huawei.huaweicasestudy.exception;

public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(Class<?> clazz, Long id) {
        super(clazz.getSimpleName() + " Not Found. ID : " + id);
    }
}
