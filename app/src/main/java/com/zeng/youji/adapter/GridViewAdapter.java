package com.zeng.youji.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.zeng.youji.CountryActivity;
import com.zeng.youji.MainActivity;
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final Area.Destination destination = (Area.Destination) getItem(i);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item,null,false);

            holder.ivCountry = (ImageButton) convertView.findViewById(R.id.iv_country);
            holder.tvNameCh = (TextView) convertView.findViewById(R.id.tv_name_ch_cn);
            holder.tvNameEn = (TextView) convertView.findViewById(R.id.tv_name_en);
            holder.tvNumberPlace = (TextView) convertView.findViewById(R.id.tv_num_place);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvNameCh.setText(destination.getName_zh_cn());
        holder.tvNameEn.setText(destination.getName_en());
        holder.tvNumberPlace.setText(destination.getPoi_count()+"个旅行地");

        bitmapUtils.display(holder.ivCountry,destination.getImage_url(),displayConfig);

        holder.ivCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"你点击了："+i,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,CountryActivity.class);
                intent.putExtra("id",destination.getId());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        ImageButton ivCountry;
        TextView tvNameCh;
        TextView tvNameEn;
        TextView tvNumberPlace;
    }


}
