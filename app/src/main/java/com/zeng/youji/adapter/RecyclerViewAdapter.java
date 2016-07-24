package com.zeng.youji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.zeng.youji.R;
import com.zeng.youji.bean.Trip;

import java.util.List;

/**
 * Created by Administrator on 16-7-23.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig displayConfig;

    private List<Trip.TripDay.Node.Note> noteList;

    public RecyclerViewAdapter(Context context, List<Trip.TripDay.Node.Note> dataset) {
        super();
        noteList = dataset;
        bitmapUtils = new BitmapUtils(context);
        displayConfig = new BitmapDisplayConfig();
        displayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        displayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  View view = View.inflate(parent.getContext(), R.layout.trip_activty_lv_item,null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_activty_lv_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvDay.setText("DAY"+noteList.get(position).getDay_note()+" "+noteList.get(position).getTrip_date_note());
        String description = noteList.get(position).getDescription();
        if(description != null){
            holder.tvDescription.setText(noteList.get(position).getDescription());
        } else {
            holder.tvDescription.setText("<无描述信息>");
        }
        Trip.TripDay.Node.Note.Photo photo = noteList.get(position).getPhoto();
        if(photo != null) {
            bitmapUtils.display(holder.ivPic,photo.getUrl(),displayConfig);
        } else {
            holder.ivPic.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDay;
        public ImageView ivPic;
        public TextView tvDescription;

        public ViewHolder(View view) {
            super(view);
            tvDay = (TextView) view.findViewById(R.id.trip_activity_lv_item_day);
            ivPic = (ImageView) view.findViewById(R.id.trip_activity_lv_item_pic);
            tvDescription = (TextView) view.findViewById(R.id.trip_activity_lv_item_description);
        }

    }


}
