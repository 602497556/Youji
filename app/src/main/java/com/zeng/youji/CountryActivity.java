package com.zeng.youji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zeng.youji.adapter.CityInfoAdapter;
import com.zeng.youji.bean.CItyInfo;

import java.lang.reflect.Type;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    private ListView mListView;

    private Toolbar toolbar;

    private CityInfoAdapter cityInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_country);
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
                Type typeList = new TypeToken<List<CItyInfo>>(){}.getType();
                Gson gson = new Gson();
                List<CItyInfo> cItyInfoList = gson.fromJson(responseInfo.result,typeList);
                if(cItyInfoList != null){
                    Log.d("************",cItyInfoList.size()+"***********");
                    cityInfoAdapter = new CityInfoAdapter(getApplicationContext(),
                                                            R.layout.countryactivity_lv_item,
                                                            cItyInfoList);
                    mListView.setAdapter(cityInfoAdapter);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(CountryActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
