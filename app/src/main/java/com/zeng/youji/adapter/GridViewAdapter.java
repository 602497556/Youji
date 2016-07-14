package com.zeng.youji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.zeng.youji.R;
import com.zeng.youji.bean.Area;

import java.util.List;

/**
 * Created by Administrator on 16-7-14.
 */
public class GridViewAdapter extends BaseAdapter {

    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig displayConfig;

    private List<Area.Destination> destinationList;
    private Context mContext;

    public GridViewAdapter(Context context, List<Area.Destination> destinations){
        super();
        this.mContext = context;
        this.destinationList = destinations;
        bitmapUtils = new BitmapUtils(context);
        displayConfig = new BitmapDisplayConfig();
        displayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        displayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
    }

    @Override
    public int getCount() {
        if(destinationList == null){
            return 0;
        } else {
            return destinationList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if(destinationList == null){
            return null;
        } else {
            return this.destinationList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item,null,false);
            holder.ivCountry = (ImageView) convertView.findViewById(R.id.iv_country);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        bitmapUtils.display(holder.ivCountry,destinationList.get(i).getImage_url(),displayConfig);


        return convertView;
    }

    private class ViewHolder{
        ImageView ivCountry;
    }


}
