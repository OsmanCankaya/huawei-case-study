package com.huawei.huaweicasestudy.exception;

public class ItemIsNotDeleteableException extends RuntimeException {
    public ItemIsNotDeleteableException(Class<?> clazz) {
        super(clazz.getSimpleName() + " is not deleteable");
    }
}