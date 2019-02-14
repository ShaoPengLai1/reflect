package com.example.netlibrary.medel;

import com.example.netlibrary.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestDataGet(String url, String params, Class clazz, MyCallBack myCallBack);

    void requestDataPost(String url, Map<String, String> params, Class clazz, MyCallBack myCallBack);
}
