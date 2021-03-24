package com.iskandar.playgrounds.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.iskandar.playgrounds.R;

import java.util.List;

public class WeatherForcastActivity extends AppCompatActivity {

    Button btn_getCityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherforcast);

        btn_getCityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);

        final WeatherDataService weatherDataService = new WeatherDataService(WeatherForcastActivity.this);
        //click listeners
        btn_getCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(WeatherForcastActivity.this, WeatherDataService.ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        if(cityID == null) { onError(cityID); }
                        else { Toast.makeText(WeatherForcastActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_SHORT).show(); }
                    }
                });
            }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weatherDataService.getForecastByID(et_dataInput.getText().toString(), new WeatherDataService.forecastByIdResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(WeatherForcastActivity.this, WeatherDataService.ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(WeatherForcastActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        lv_weatherReports.setAdapter(arrayAdapter);
                    }
                });
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.GetCityForecastByNameCallBack(){
                    @Override
                    public void onError(String message) {
                        Toast.makeText(WeatherForcastActivity.this, WeatherDataService.ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(WeatherForcastActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        lv_weatherReports.setAdapter(arrayAdapter);
                        //for pull test
                    }
                });
            }
        });
    }
}
