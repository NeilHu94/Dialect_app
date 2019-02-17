package com.demo.neilhu.dialect;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    //String result = excute("安庆");
    static WeatherAdapter  adapter;
    public final static String weatherURL ="http://v.juhe.cn/weather/index?format=2&cityname=%e5%ae%89%e5%ba%86&key=23ca2a752c9ef8a5db252f9a79562943";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        ListView weatherListView = (ListView) findViewById(R.id.weather_list);

        // Create a new adapter that takes the list_item of weathers as input
        adapter = new WeatherAdapter(this,new ArrayList<Weather>());
        // Set the adapter on the {@link ListView}
        // so the list_item can be populated in the user interface
        weatherListView.setAdapter(adapter);
        WeatherActivity.weatherSyncTask task = new WeatherActivity.weatherSyncTask();
        task.execute(weatherURL);
        Toast.makeText(this, "位置：安庆", Toast.LENGTH_LONG).show();


    }

    static class weatherSyncTask extends AsyncTask<String, Void, List<Weather>> {

        @Override
        protected List<Weather> doInBackground(String... params) {
            if (params.length < 1 || params[0] == null) {
                return null;
            }
            List<Weather> result = QueryUtils.creatHttpRequest(params[0]);
            return result;
        }
        @Override
        protected void onPostExecute(List<Weather> weather) {
            adapter.clear();
            if (weather!=null&&!weather.isEmpty())
            {
                adapter.addAll(weather);
            }
        }
    }
}
