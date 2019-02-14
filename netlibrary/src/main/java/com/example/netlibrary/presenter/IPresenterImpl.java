package com.example.netlibrary.presenter;

import com.example.netlibrary.callback.MyCallBack;
import com.example.netlibrary.medel.IModelImpl;
import com.example.netlibrary.view.IView;

import java.util.Map;

public class IPresenterImpl implements IPresenter{

    private IView mIview;
    private IModelImpl model;

    public IPresenterImpl(IView iview) {
        mIview = iview;
        model = new IModelImpl();
    }

    @Override
    public void startRequestGet(String url, String params, Class clazz) {
        model.requestDataGet(url, params, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                mIview.getRequest(data);
            }

            @Override
            public void failed(Exception e) {
                mIview.getRequest(e.getMessage());
            }
        });
    }


    @Override
    public void startRequestPost(String url, Map<String, String> params, Class clazz) {
        model.requestDataPost(url, params, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                mIview.getRequest(data);
            }

            @Override
            public void failed(Exception e) {
                mIview.getRequest(e.getMessage());
            }
        });
    }
    public void onDetach(){
        if(model!=null){
            model = null;
        }
        if (mIview!=null){
            mIview = null;
        }
    }
}
