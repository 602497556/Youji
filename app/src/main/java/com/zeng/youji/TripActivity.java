package com.zeng.youji;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zeng.youji.adapter.RecyclerViewAdapter;
import com.zeng.youji.adapter.TripActivityListViewAdapter;
import com.zeng.youji.bean.Trip;
import com.zeng.youji.utils.MyDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class TripActivity extends AppCompatActivity {

    private StringBuilder stringBuilder;

    private HttpUtils httpUtils;

    private TripActivityListViewAdapter adapter;

    private Toolbar toolbar;

    private CoordinatorLayout rootLayout;

    private AppBarLayout appBarLayout;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private RelativeLayout headLayout;

    private RecyclerView recyclerView;

    private TextView titleName;
    private ImageView coverImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trip);
        initViews(getIntent().getExtras());

        recyclerView = (RecyclerView) findViewById(R.id.trip_activity_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        long id = getIntent().getExtras().getLong("trip_id");
        stringBuilder = new StringBuilder("http://chanyouji.com/api/trips/").append(id).append(".json");
        //请求数据
        requestData(stringBuilder.toString());
    }

    private void initViews(Bundle bundle) {
        titleName = (TextView) findViewById(R.id.trip_activity_title);
        titleName.setText(bundle.getString("title_name"));
        coverImage = (ImageView) findViewById(R.id.trip_activity_cover_image);
        Glide.with(this).load(bundle.getString("cover_photo_url")).into(coverImage);
        toolbar = (Toolbar) findViewById(R.id.trip_activity_tb);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rootLayout = (CoordinatorLayout) findViewById(R.id.trip_activity_cl);
        headLayout = (RelativeLayout) findViewById(R.id.trip_activity_head_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.trip_activity_app_bar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.trip_activity_collapsing_tb);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset < -headLayout.getHeight()/2){
                    mCollapsingToolbarLayout.setTitle("游");
                } else {
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });
        loadBlurAndStatusBar(bundle.getString("cover_photo_url"));
    }

    private void loadBlurAndStatusBar(String imageUrl) {
        //设置状态栏半透明
//        StatusBarUtil.setTranslucent(TripActivity.this,StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
//        Glide.with(this).load(imageUrl).bitmapTransform(new BlurTransformation(this,100))
//                .into(new SimpleTarget<GlideDrawable>() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                headLayout.setBackgroundDrawable(resource);
//              //  rootLayout.setBackground(resource);
//            }
//        });

        Glide.with(this).load(imageUrl).bitmapTransform(new BlurTransformation(this,100))
                .into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                mCollapsingToolbarLayout.setContentScrim(resource);
            }
        });
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
//                    lvAdapter = new TripActivityListViewAdapter(getApplicationContext(),tripData.getTrip_days());
//                    listView.setAdapter(lvAdapter);
                    List<Trip.TripDay.Node> nodeList;
                    List<Trip.TripDay.Node.Note> noteList;
                    List<Trip.TripDay.Node.Note> noteListTotal = new ArrayList<>();
                    List<Trip.TripDay> tripDayList = tripData.getTrip_days();
                    for(int i=0; i<tripDayList.size();i++){
                        String trip_date = tripDayList.get(i).getTrip_date();
                        int day = tripDayList.get(i).getDay();
                        nodeList = tripDayList.get(i).getNodes();
                            for(int j=0; j<nodeList.size();j++){
                                noteList = nodeList.get(j).getNotes();
                                if(noteList != null){
                                    for(Trip.TripDay.Node.Note note : noteList){
                                        note.setTrip_date_note(trip_date);
                                        trip_date = "";
                                        note.setDay_note(day);
                                        noteListTotal.add(note);
                                    }

                                }
                            }
                    }
                    Log.d("************",noteListTotal.size()+"");
//                    adapter = new TripActivityListViewAdapter(getApplicationContext(),noteListTotal);
//                    listView.setAdapter(adapter);
                    recyclerView.setAdapter(new RecyclerViewAdapter(TripActivity.this,noteListTotal));
                    //使滑动流畅
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.addItemDecoration(new MyDividerDecoration(TripActivity.this,MyDividerDecoration.VERTICAL_LIST));
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getApplicationContext(),"网络连接出错...",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
