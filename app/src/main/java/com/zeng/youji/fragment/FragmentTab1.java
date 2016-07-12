package com.zeng.youji.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zeng.youji.R;
import com.zeng.youji.adapter.DateAdapter;
import com.zeng.youji.bean.Date;

import java.lang.reflect.Type;
import java.util.List;

public class FragmentTab1 extends Fragment {

    private ListView mListView;

    private DateAdapter mDateAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view);
        getDate("http://chanyouji.com/api/trips/featured.json?page=1");
        return view;
    }

    /*
    使用xUtils发送网络请求，在onSuccess()方法中通过Gson解析得到数据
     */
    private void getDate(String url) {
        HttpUtils http = new HttpUtils();
        http.send( HttpRequest.HttpMethod.GET,
                    url,
                    new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.d("******************",responseInfo.result+"**************");
                        Type listType = new TypeToken<List<Date>>(){}.getType();
                        Gson gson = new Gson();
                        List<Date> dateList = gson.fromJson(responseInfo.result,listType);
                        if(dateList != null){
                            mDateAdapter = new DateAdapter(getContext(),R.layout.list_view_item,dateList);
                            mListView.setAdapter(mDateAdapter);
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getActivity(),"网络连接出错！",Toast.LENGTH_SHORT).show();
                    }
                });
    }




}
