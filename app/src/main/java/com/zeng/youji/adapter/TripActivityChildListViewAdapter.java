package com.zeng.youji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
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

import java.util.List;

/**
 * Created by Administrator on 16-7-17.
 */
public class TripActivityChildListViewAdapter extends BaseAdapter {

    private Context mContext;

    private List<Trip.TripDay.Node.Note> noteList;

    private BitmapUtils bitmapUtils;

    private BitmapDisplayConfig displayConfig;

    public TripActivityChildListViewAdapter(Context context,List<Trip.TripDay.Node.Note> notes){
        super();
        this.mContext = context;
        this.noteList = notes;
        bitmapUtils = new BitmapUtils(mContext);
        displayConfig = new BitmapDisplayConfig();
        displayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        displayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
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
        Trip.TripDay.Node.Note note = (Trip.TripDay.Node.Note) getItem(i);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.trip_activity_lv_child_item,null);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.trip_activity_lv_child_item_description);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.trip_activity_lv_child_item_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String description = note.getDescription();
        String imageUrl = null;
        if(note.getPhoto() != null){
            imageUrl = note.getPhoto().getUrl();
        }
        if(!TextUtils.isEmpty(description) && !TextUtils.isEmpty(imageUrl)){
            holder.tvDescription.setText(description);
            bitmapUtils.display(holder.ivPic,imageUrl,displayConfig);
        } else if(!TextUtils.isEmpty(imageUrl) && TextUtils.isEmpty(description)){
            holder.tvDescription.setVisibility(View.GONE);
            bitmapUtils.display(holder.ivPic,imageUrl,displayConfig);
        } else if(!TextUtils.isEmpty(description) && TextUtils.isEmpty(imageUrl)){
            holder.tvDescription.setText(description);
            holder.ivPic.setVisibility(View.GONE);
        }
        return convertView;
    }


    class ViewHolder{
        TextView tvDescription;
        ImageView ivPic;
    }


}
