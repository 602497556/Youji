package com.zeng.youji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.zeng.youji.R;
import com.zeng.youji.bean.Trip;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 16-7-17.
 */
public class TripActivityListViewAdapter extends BaseAdapter {

    private List<Trip.TripDay.Node.Note> noteList;

    private LayoutInflater inflater;

    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig config;

    private HashMap<Integer,View> integerViewHashMap = new HashMap<>();


    public TripActivityListViewAdapter(Context context, List<Trip.TripDay.Node.Note> noteList){
        super();
        this.noteList = noteList;
        inflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(context);
        config = new BitmapDisplayConfig();
        config.setBitmapConfig(Bitmap.Config.RGB_565);
        config.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return noteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Trip.TripDay.Node.Note noteItem = (Trip.TripDay.Node.Note) getItem (i);
        ViewHolder holder;
//        if(convertView == null){
//            holder = new ViewHolder();
//            convertView = inflater.inflate(R.layout.trip_activty_lv_item_2,null);
//            holder.tvDay = (TextView) convertView.findViewById(R.id.trip_activity_lv_item_day);
//            holder.ivPic = (ImageView) convertView.findViewById(R.id.trip_activity_lv_item_pic);
//            holder.tvDescription = (TextView) convertView.findViewById(R.id.trip_activity_lv_item_description);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.tvDescription.setText(noteItem.getDescription());
//        holder.tvDay.setText("DAY"+noteItem.getDay_note()+" "+noteItem.getTrip_date_note());
//        Trip.TripDay.Node.Note.Photo photo = noteItem.getPhoto();
//        if(photo != null) {
//            bitmapUtils.display(holder.ivPic,photo.getUrl(),config);
//        }
        if(integerViewHashMap.get(i) == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.trip_activty_lv_item,null);
            holder.tvDay = (TextView) convertView.findViewById(R.id.trip_activity_lv_item_day);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.trip_activity_lv_item_pic);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.trip_activity_lv_item_description);

            integerViewHashMap.put(i,convertView);
            convertView.setTag(holder);

            holder.tvDay.setText("DAY"+noteItem.getDay_note()+" "+noteItem.getTrip_date_note());
            holder.tvDescription.setText(noteItem.getDescription());
            Trip.TripDay.Node.Note.Photo photo = noteItem.getPhoto();
            if(photo != null) {
            bitmapUtils.display(holder.ivPic,photo.getUrl(),config);
            } else {
                holder.ivPic.setVisibility(View.GONE);
            }
        } else {
            convertView = integerViewHashMap.get(i);
        }
        return convertView;
    }


    class ViewHolder{
        TextView tvDay;
        ImageView ivPic;
        TextView tvDescription;
    }


}
