package com.zeng.youji;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wang.avi.AVLoadingIndicatorView;
import com.zeng.youji.adapter.CountrySpotsAdapter;
import com.zeng.youji.bean.CItyInfo;

import java.lang.reflect.Type;
import java.util.List;

public class CountrySpotsActivity extends Activity implements View.OnTouchListener {

    private ListView mListView;

    private Toolbar toolbar;

    private CountrySpotsAdapter adapter;

    private AVLoadingIndicatorView loadingIndicatorView;

    //最低滑动距离
    private int mTouchSlop;

    private float mFirstY,mCurrentY;

    //记录手指滑动方向
    private int direction;

    //Toolbar是否显示
    private boolean mShow = true;

    private ObjectAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_spots);
        initView();
        int id = getIntent().getIntExtra("id",0);
        if(id != 0){
            StringBuilder stringBuilder = new StringBuilder("http://chanyouji.com/api/destinations/").append(id).append(".json");
            requestData(stringBuilder.toString());
        }
    }

    /*
    初始化控件,给Toolbar的导航图标设置点击事件
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.country_activity_lv);
        toolbar = (Toolbar) findViewById(R.id.country_activity_tb);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        loadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.country_activity_loading_view);
        //给ListView增加一个HeaderView,避免第一个item被ToolBar遮挡
        View headView = new View(this);
        headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        mListView.addHeaderView(headView);
        mListView.setOnTouchListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*
    请求数据
     */
    private void requestData(String url) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                loadingIndicatorView.setVisibility(View.GONE);
                Type typeList = new TypeToken<List<CItyInfo>>(){}.getType();
                Gson gson = new Gson();
                List<CItyInfo> cItyInfoList = gson.fromJson(responseInfo.result,typeList);
                if(cItyInfoList != null){
                    Log.d("************",cItyInfoList.size()+"***********");
                    adapter = new CountrySpotsAdapter(getApplicationContext(),
                                                            R.layout.country_spots_lv_item,
                                                            cItyInfoList);
                    mListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                loadingIndicatorView.setVisibility(View.GONE);
                Toast.makeText(CountrySpotsActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mFirstY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentY = event.getY();
                if(mCurrentY - mFirstY > mTouchSlop){
                    direction = 0; //down
                } else if(mFirstY-mCurrentY > mTouchSlop){
                    direction = 1; //up
                }
                if(direction == 1){
                    if(mShow){
                        toolbarAnim(0);//hide
                        mShow = !mShow;
                    }
                } else if(direction == 0) {
                    if (!mShow) {
                        toolbarAnim(1);//show
                        mShow = !mShow;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

    /*
    控制ToolBar显示或隐藏
     */
    private void toolbarAnim(int flag) {
        if(mAnimator != null && mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if(flag == 1){
            mAnimator = ObjectAnimator.ofFloat(toolbar,"translationY",toolbar.getTranslationY(),0);
        } else {
            mAnimator = ObjectAnimator.ofFloat(toolbar,"translationY",toolbar.getTranslationY(),-toolbar.getHeight());
        }
        mAnimator.start();
    }


}
