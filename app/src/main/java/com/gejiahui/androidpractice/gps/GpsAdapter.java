package com.gejiahui.androidpractice.gps;

import android.graphics.Color;
import android.location.GpsSatellite;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gejiahui.androidpractice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejiahui on 2016/6/27.
 */
public class GpsAdapter extends BaseAdapter {
    private List<GpsSatellite> mDatas = new ArrayList<GpsSatellite>(); // 卫星信号
    int[] colors = { Color.WHITE, Color.rgb(219, 238, 244) };//RGB颜色
    public GpsAdapter(List<GpsSatellite> data) {
        mDatas = data;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;
        if(convertView != null){
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gps_listview,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) view.findViewById(R.id.id);
            viewHolder.snr = (TextView) view.findViewById(R.id.snr);
            viewHolder.elevation = (TextView) view.findViewById(R.id.elevation);
            viewHolder.azimuth = (TextView) view.findViewById(R.id.azimuth);
            viewHolder.bar = (View) view.findViewById(R.id.bar);

            view.setTag(viewHolder);
        }
        viewHolder.id.setText(mDatas.get(position).getPrn()+"");
        viewHolder.snr.setText((int)(mDatas.get(position).getSnr())+"");
        viewHolder.elevation.setText((int)(mDatas.get(position).getElevation())+"°");
        viewHolder.azimuth.setText((int)(mDatas.get(position).getAzimuth())+"°");



        ViewGroup.LayoutParams layoutParams = viewHolder.bar.getLayoutParams();
        layoutParams.width = (int)(mDatas.get(position).getSnr()*5);


        viewHolder.bar.setLayoutParams(layoutParams);

        view.setBackgroundColor(colors[position % 2]);// 每隔item之间颜色不同
        return view;
    }

    class ViewHolder{
        TextView id;
        TextView snr;
        TextView elevation;
        TextView azimuth;
        View bar;

    }
}
