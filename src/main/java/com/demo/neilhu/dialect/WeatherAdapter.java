package com.demo.neilhu.dialect;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NeilHu on 2017/5/31.
 */

public class WeatherAdapter extends ArrayAdapter<Weather>{

    public WeatherAdapter(Context context, ArrayList<Weather> weathers) {
        super(context,0,weathers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.weather_list_item, parent, false);
        }

        Weather currentWeather = getItem(position);
        TextView weekTextView = (TextView) listItemView.findViewById(R.id.weekDay);
        weekTextView.setText(currentWeather.getWeek());

        TextView temperatureView = (TextView) listItemView.findViewById(R.id.temperatureNumber);
        temperatureView.setText(currentWeather.getTemperature());


        TextView weatherView = (TextView) listItemView.findViewById(R.id.weatherInfo);
        weatherView.setText(currentWeather.getWeather());

        return listItemView;
    }
}
