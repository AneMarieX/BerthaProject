package com.project.berthaproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

public class DataListItemAdapter extends ArrayAdapter<Data>{

    private final int resource;

    public DataListItemAdapter(Context context, int resource, List<Data> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public DataListItemAdapter(Context context, int resource, Data[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Data data = getItem(position);
        String temperature = data.getTemperature();
        //String userId = data.getUserId();
        LinearLayout dataView;
        if (convertView == null) {
            dataView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, dataView, true);
        } else {
            dataView = (LinearLayout) convertView;
        }
        TextView temperatureView = dataView.findViewById(R.id.datalist_item_temperature);
        // TextView userIdView = dataView.findViewById(R.id.datalist_item_userId);
        //userIdView.setText(userId);//
        temperatureView.setText(temperature);
        return dataView;

    }

}
