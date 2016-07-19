package com.zeng.youji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zeng.youji.R;
import com.zeng.youji.bean.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-7-17.
 */
public class TripActivityListViewAdapter extends BaseAdapter {

    private Context mContext;

    private List<Trip.TripDay> tripDaysList;

    public TripActivityListViewAdapter(Context context, List<Trip.TripDay> tripDays){
        super();
        this.mContext = context;
        this.tripDaysList = tripDays;
    }

    @Override
    public int getCount() {
        return tripDaysList.size();
    }

    @Override
    public Object getItem(int i) {
        return tripDaysList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Trip.TripDay tripDay = (Trip.TripDay) getItem(i);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.trip_activty_lv_item,null);
            holder.tvDay = (TextView) convertView.findViewById(R.id.trip_activity_lv_item_day);
            holder.myListView = (ListView) convertView.findViewById(R.id.trip_activity_lv_item_my_lv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDay.setText("DAY"+tripDay.getDay()+" ,"+tripDay.getTrip_date());

        int nodeListLength = tripDay.getNodes().size();
        List<Trip.TripDay.Node.Note> noteList;
        List<Trip.TripDay.Node.Note> noteListTotal = new ArrayList<>();
        TripActivityChildListViewAdapter lvChildAdapter;
        for(int j=0; j<nodeListLength; j++){
             noteList = tripDay.getNodes().get(j).getNotes();
            if(noteList != null){
                for(Trip.TripDay.Node.Note note : noteList){
                    noteListTotal.add(note);
                }
            }
        }
        if(noteListTotal != null){
            lvChildAdapter = new TripActivityChildListViewAdapter(mContext,noteListTotal);
            holder.myListView.setAdapter(lvChildAdapter);
            setListViewHeightBasedOnChildren(holder.myListView);
        }
        return convertView;
    }

    private void setListViewHeightBasedOnChildren(ListView lv2) {
        if( lv2 == null ) return;
        ListAdapter listAdapter = lv2.getAdapter();
        if( listAdapter == null ){
            return;
        }
        int totalHeight = 0;
        for(int i=0; i<listAdapter.getCount(); i++){
            View listItem = listAdapter.getView(i, null, lv2);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv2.getLayoutParams();
        params.height = totalHeight + (lv2.getDividerHeight()*(listAdapter.getCount()-1));
        lv2.setLayoutParams(params);
    }


    class ViewHolder{
        TextView tvDay;
        ListView myListView;
    }


}
