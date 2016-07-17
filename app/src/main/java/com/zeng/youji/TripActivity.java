package com.zeng.youji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zeng.youji.adapter.TripActivityListViewAdapter;
import com.zeng.youji.bean.Trip;

import java.lang.reflect.Type;
import java.util.List;

public class TripActivity extends AppCompatActivity {

    private StringBuilder stringBuilder;

    private HttpUtils httpUtils;

    private TripActivityListViewAdapter lvAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        listView = (ListView) findViewById(R.id.trip_activity_lv);
        long id = getIntent().getLongExtra("trip_id",0);
        stringBuilder = new StringBuilder("http://chanyouji.com/api/trips/").append(id).append(".json");
        //请求数据
        requestData(stringBuilder.toString());
    }

    /*
    使用xUtils发送网络请求
     */
    private void requestData(String url) {
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if(responseInfo.result != null){
                    Gson gson = new Gson();
                    Trip tripData = gson.fromJson(responseInfo.result,Trip.class);
                    Log.d("***************", tripData.getName());
                    lvAdapter = new TripActivityListViewAdapter(getApplicationContext(),tripData.getTrip_days());
                    listView.setAdapter(lvAdapter);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"网络连接出错...",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
