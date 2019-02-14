package com.example.netlibrary.callback;

public interface MyCallBack<T> {
    void setData(T data);
    void failed(Exception e);
}
