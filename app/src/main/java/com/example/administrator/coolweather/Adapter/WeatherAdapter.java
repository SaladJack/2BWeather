package com.example.administrator.coolweather.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.coolweather.R;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public class WeatherAdapter extends ArrayAdapter<HourlyWeather> {

    private int resourceId;

    public WeatherAdapter(Context context, int textViewResourceId,
                          List<HourlyWeather> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        HourlyWeather wea = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView clock_text = (TextView) view.findViewById(R.id.clock);
        TextView tmp_text = (TextView) view.findViewById(R.id.tmp);
        TextView pop_text = (TextView) view.findViewById(R.id.pop);
        TextView wind_text = (TextView) view.findViewById(R.id.wind);
        clock_text.setText(wea.getClock());
        tmp_text.setText(wea.getTmp());
        pop_text.setText(wea.getPop());
        wind_text.setText(wea.getWind());
        return view;
    }

}