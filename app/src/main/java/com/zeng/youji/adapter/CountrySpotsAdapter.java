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
import com.zeng.youji.bean.CItyInfo;

import java.util.List;

/**
 * Created by Administrator on 16-7-15.
 */
public class CountrySpotsAdapter extends ArrayAdapter<CItyInfo> {

    private  int resourceId;

    private Context mContext;

    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig config;


    public CountrySpotsAdapter(Context context, int textViewResourceId, List<CItyInfo> datas) {
        super(context, textViewResourceId,datas);
        resourceId = textViewResourceId;
        mContext = context;
        bitmapUtils  = new BitmapUtils(context);
        config = new BitmapDisplayConfig();
        config.setBitmapConfig(Bitmap.Config.RGB_565);
        config.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CItyInfo item = getItem(position);
        View view;
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(resourceId,null);
            holder.ivImage = (ImageView) view.findViewById(R.id.iv_activity_country_item);
            holder.tvCHName = (TextView) view.findViewById(R.id.tv_activity_country_item_name_ch);
            holder.tvEnName = (TextView) view.findViewById(R.id.tv_activity_country_item_name_en);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        bitmapUtils.display(holder.ivImage,item.getImage_url(),config);
        holder.tvCHName.setText(item.getName_zh_cn());
        holder.tvEnName.setText(item.getName_en());
        return view;
    }


    class ViewHolder{
        ImageView ivImage;
        TextView tvCHName;
        TextView tvEnName;
    }

}
