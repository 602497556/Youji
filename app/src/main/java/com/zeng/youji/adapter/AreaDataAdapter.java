package com.zeng.youji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zeng.youji.R;
import com.zeng.youji.bean.Area;
import com.zeng.youji.view.MyGridView;

import java.util.List;

/**
 * Created by Administrator on 16-7-14.
 */
public class AreaDataAdapter extends BaseAdapter {

    private List<Area> areaList;
    private Context mContext;

    private String[] areas = {"国外·亚洲","国外·欧洲","美洲、大洋洲、非洲与南极洲","国内·港澳台","国内·大陆"};

    /*
    构造方法
     */
    public AreaDataAdapter(Context context,List<Area> areas){
        super();
        this.mContext = context;
        this.areaList = areas;
    }

    @Override
    public int getCount() {
        if(areaList == null){
            return 0;
        } else {
            return this.areaList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if(areaList == null){
            return null;
        } else {
            return this.areaList.get(i);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item_2,null,false);
            holder.tvCategory = (TextView) convertView.findViewById(R.id.tv_category);
            holder.myGridView = (MyGridView) convertView.findViewById(R.id.grid_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCategory.setText(areas[i]);

        List<Area.Destination> destinations = areaList.get(i).getDestinations();
//        if(destinations == null){
//            Log.d("***********",destinations+"为空************");
//        } else {
//            Log.d("***********",destinations.size()+"************");
//        }
        GridViewAdapter gridViewAdapter  = new GridViewAdapter(mContext,destinations);
        holder.myGridView.setAdapter(gridViewAdapter);
        return convertView;
    }




    private class ViewHolder{
        TextView tvCategory;
        MyGridView myGridView;
    }



}
