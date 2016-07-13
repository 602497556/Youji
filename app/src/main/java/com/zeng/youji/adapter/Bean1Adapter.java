package com.zeng.youji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.zeng.youji.R;
import com.zeng.youji.bean.Bean1;

import java.util.List;

/**
 * Created by Administrator on 16-7-12.
 */
public class Bean1Adapter extends ArrayAdapter<Bean1> {

    private int resourceId;

    private Context mContext;

    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig config;

    public Bean1Adapter(Context context, int textViewResourceId, List<Bean1> dates){
        super(context,textViewResourceId,dates);
        resourceId = textViewResourceId;
        mContext = context;
        bitmapUtils  = new BitmapUtils(context);
        config = new BitmapDisplayConfig();
        config.setBitmapConfig(Bitmap.Config.RGB_565);
        config.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bean1 item = getItem(position);
        View view;
        ViewHolder holder;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(resourceId,null);
            holder = new ViewHolder();
            holder.ivCoverPhoto = (ImageView) view.findViewById(R.id.iv_cover_photo);
            holder.ivUserImage = (ImageView) view.findViewById(R.id.iv_user_image);
            holder.tvDate = (TextView) view.findViewById(R.id.tv_date);
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvDays = (TextView) view.findViewById(R.id.tv_days);
            holder.tvPhotosCount = (TextView) view.findViewById(R.id.tv_photos_count);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(item.getName());
        holder.tvDate.setText(item.getStart_date());
        holder.tvDays.setText("/"+item.getDays()+"天");
        holder.tvPhotosCount.setText(item.getPhotos_count()+"图");
        bitmapUtils.display(holder.ivCoverPhoto,item.getFront_cover_photo_url(),config);
        bitmapUtils.display(holder.ivUserImage,item.getUser().getImage(),config);
        return view;
    }

    class ViewHolder {
        ImageView ivCoverPhoto;
        ImageView ivUserImage;
        TextView tvName;
        TextView tvDate;
        TextView tvDays;
        TextView tvPhotosCount;
    }






}
