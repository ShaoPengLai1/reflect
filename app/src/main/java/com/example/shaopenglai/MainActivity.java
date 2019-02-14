package com.example.shaopenglai;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.netlibrary.presenter.IPresenterImpl;
import com.example.netlibrary.view.IView;
import com.example.shaopenglai.adapter.FrescoAdapter;
import com.example.shaopenglai.bean.FrescoBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


public class MainActivity extends AppCompatActivity implements IView {
    private IPresenterImpl iPresenter;
    private XRecyclerView xRecyclerView;
    private FrescoAdapter adapter;
    private int mPage;
    private String path="http://www.zhaoapi.cn/product/getCatagory?page=%d";
    private String uri="https://pic.sogou.com/d?query=%CD%BC%C6%AC&st=255&mode=255&did=1#did7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        mPage=1;
        iPresenter=new IPresenterImpl(this);
        xRecyclerView=findViewById(R.id.xRecyclerView);
        adapter=new FrescoAdapter(this);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        final SimpleDraweeView draweeView=new SimpleDraweeView(this);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                loadData();

            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });
        loadData();
        showUrlBlur(draweeView,uri,200,500);
    }

    private void loadData() {
        iPresenter.startRequestGet(String.format(path,mPage),null,FrescoBean.class);
    }

    @Override
    public void getRequest(Object data) {
        if (data instanceof FrescoBean){
            FrescoBean bean= (FrescoBean) data;
            if (bean==null||!bean.isSuccess()){
                Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_LONG).show();
            }else {
                if (mPage==1){
                    adapter.setData(bean.getData());
                }else {
                    adapter.addData(bean.getData());
                }
                mPage++;
                xRecyclerView.loadMoreComplete();
                xRecyclerView.refreshComplete();
            }
        }

    }

    public static void showUrlBlur(SimpleDraweeView draweeView,String url,int iterations, int blurRadius){
        try {
            Uri uri = Uri.parse(url);
            ImageRequest request=ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                    .build();
            AbstractDraweeController controller=Fresco.newDraweeControllerBuilder()
                    .setOldController(draweeView.getController())
                    .setImageRequest(request)
                    .build();
            draweeView.setController(controller);
        }catch (Exception e){

        }
    }
}
