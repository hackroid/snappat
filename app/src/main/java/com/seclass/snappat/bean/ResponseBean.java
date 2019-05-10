package com.seclass.snappat.bean;

import java.io.Serializable;



public class ResponseBean<T> implements Serializable {

    public int code;
    public String message;
    public T data = null;

}