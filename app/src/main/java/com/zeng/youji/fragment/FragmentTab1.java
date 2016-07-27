package com.zeng.youji.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wang.avi.AVLoadingIndicatorView;
import com.zeng.youji.R;
import com.zeng.youji.TripActivity;
import com.zeng.youji.adapter.Fragment1ListViewAdapter;
import com.zeng.youji.bean.Bean1;

import java.lang.reflect.Type;
import java.util.List;

public class FragmentTab1 extends Fragment {

    private ListView mListView;

    private Fragment1ListViewAdapter adapter;

    private PullToRefreshListView pullToRefresh;

    private HttpUtils httpUtils;

    private List<Bean1> dateList;

    private int page = 2;

    private AVLoadingIndicatorView loadingView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
//        mListView = (ListView) view.findViewById(R.id.list_view);
        loadingView = (AVLoadingIndicatorView) view.findViewById(R.id.fragment1_loading_view);
        pullToRefresh = (PullToRefreshListView) view.findViewById(R.id.pullToRefresh);
        getData("http://chanyouji.com/api/trips/featured.json?page=1");
        return view;
    }

    /*
    使用xUtils发送网络请求，在onSuccess()方法中通过Gson解析得到数据
     */
    private void getData(String url) {
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                loadingView.setVisibility(View.GONE);
                Type listType = new TypeToken<List<Bean1>>() {}.getType();
                Gson gson = new Gson();
                dateList = gson.fromJson(responseInfo.result, listType);
                if (dateList != null) {
                    adapter = new Fragment1ListViewAdapter(getContext(), R.layout.fragment_1_lv_item, dateList);
//                    mListView.setAdapter(adapter);
                    pullToRefresh.setAdapter(adapter);
                    pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
                    initPullToRefresh();
                    pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                        @Override
                        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                            pullToRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    pullToRefresh.onRefreshComplete();
                                }
                            },5000);
                            Log.e("-------->","onPullDownToRefresh execute!!!");
                            
                        }

                        @Override
                        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                            Log.e("-------->","onPullUpToRefresh execute!!!");
                            StringBuilder builder = new StringBuilder("http://chanyouji.com/api/trips/featured.json?page=");
                            builder.append(page);
                            requestDataPullUp(builder.toString());
                        }
                    });
                    pullToRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            int i = position -1;
                            Toast.makeText(getContext(), "你点击了：" + i, Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("title_name",dateList.get(i).getName());
                            bundle.putString("start_date",dateList.get(i).getStart_date());
                            bundle.putInt("days",dateList.get(i).getDays());
                            bundle.putString("cover_photo_url",dateList.get(i).getFront_cover_photo_url());
                            bundle.putLong("trip_id",dateList.get(i).getId());
                            bundle.putString("head_image_url",dateList.get(i).getUser().getImage());
                            Intent intent = new Intent(getContext(), TripActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                loadingView.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "网络连接出错！", Toast.LENGTH_SHORT).show();
            }
        });

    }



    /*
    设置刷新时的文本显示
     */
    private void initPullToRefresh() {
        //下拉时的文本显示
        ILoadingLayout startLabels = pullToRefresh.getLoadingLayoutProxy(true,false);
        startLabels.setPullLabel("下拉刷新...");
        startLabels.setRefreshingLabel("正在载入...");
        startLabels.setReleaseLabel("放开刷新...");
        //上拉时的文本显示
        ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(false,true);
        endLabels.setPullLabel("上拉刷新...");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");
    }

    /*
    上拉时从网络请求数据
     */
    private void requestDataPullUp(String url) {
        page += 1;
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type listType = new TypeToken<List<Bean1>>() {}.getType();
                Gson gson = new Gson();
                List<Bean1> requestDataList = gson.fromJson(responseInfo.result, listType);
                for(Bean1 bean : requestDataList){
                    dateList.add(bean);
                }
                pullToRefresh.onRefreshComplete();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getContext(),"刷新失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
