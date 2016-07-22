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
import com.zeng.youji.adapter.Fragment2ListViewAdapter;
import com.zeng.youji.bean.Area;

import java.lang.reflect.Type;
import java.util.List;

public class FragmentTab2 extends Fragment {

    private ListView listView;

    private Fragment2ListViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        listView = (ListView) view.findViewById(R.id.tab2_list_view);
        getData();
        return view;
    }

    private void getData() {
        HttpUtils http = new HttpUtils();
        http.send( HttpRequest.HttpMethod.GET,
                   "http://chanyouji.com/api/destinations.json", new RequestCallBack<String>() {
                       @Override
                       public void onSuccess(ResponseInfo<String> responseInfo) {
                           Type listType = new TypeToken<List<Area>>(){}.getType();
                           Gson gson = new Gson();
                           List<Area> areaLists = gson.fromJson(responseInfo.result,listType);
                           if(areaLists != null){
                               Log.d("*************",areaLists.size()+"*************");
                               adapter = new Fragment2ListViewAdapter(getContext(),areaLists);
                               listView.setAdapter(adapter);
                           }
                       }

                       @Override
                       public void onFailure(HttpException e, String s) {
                           Toast.makeText(getContext(),"网络连接出错",Toast.LENGTH_SHORT).show();
                       }
                   });

    }

}
