package com.iskandar.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";
    public static final String ERROR_MESSAGE = "Name or ID is invalid.";

    Context context;
    String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityID);
    }

    public void getCityID(String cityName, VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_CITY_ID + cityName;
        JsonArrayRequest requestID = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(cityID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(ERROR_MESSAGE);
            }
        });
        DataServiceSingleton.getInstance(context).addToRequestQueue(requestID);
    }

    public interface forecastByIdResponse {
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModel);
    }

    public void getForecastByID(String cityID, forecastByIdResponse ForecastByIdResponse){
        List<WeatherReportModel> weatherReports = new ArrayList<>();
        //get JSON object
        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityID;
        JsonObjectRequest requestID = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");
                    for(int i = 0; i < consolidated_weather_list.length(); i++) {
                        WeatherReportModel one_day_weather = new WeatherReportModel();
                        JSONObject one_day_from_api = (JSONObject) consolidated_weather_list.get(i);
                        one_day_weather.setId(one_day_from_api.getInt("id"));
                        one_day_weather.setWeather_state_name(one_day_from_api.getString("weather_state_name"));
                        one_day_weather.setWeather_state_abbr(one_day_from_api.getString("weather_state_abbr"));
                        one_day_weather.setWind_direction_compass(one_day_from_api.getString("wind_direction_compass"));
                        one_day_weather.setCreated(one_day_from_api.getString("created"));
                        one_day_weather.setApplicable_date(one_day_from_api.getString("applicable_date"));
                        one_day_weather.setMin_temp(one_day_from_api.getLong("min_temp"));
                        one_day_weather.setMax_temp(one_day_from_api.getLong("max_temp"));
                        one_day_weather.setThe_temp(one_day_from_api.getLong("the_temp"));
                        one_day_weather.setWind_speed(one_day_from_api.getLong("wind_speed"));
                        one_day_weather.setWind_direction(one_day_from_api.getLong("wind_direction"));
                        one_day_weather.setAir_pressure(one_day_from_api.getInt("air_pressure"));
                        one_day_weather.setHumidity(one_day_from_api.getInt("humidity"));
                        one_day_weather.setVisibility(one_day_from_api.getLong("visibility"));
                        one_day_weather.setPredictability(one_day_from_api.getInt("predictability"));
                        weatherReports.add(one_day_weather);
                    }
                    ForecastByIdResponse.onResponse(weatherReports);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ForecastByIdResponse.onError(ERROR_MESSAGE);
            }
        });
        DataServiceSingleton.getInstance(context).addToRequestQueue(requestID);
    }

    public interface GetCityForecastByNameCallBack{
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModels);
    }

    public void getCityForecastByName(String cityName, GetCityForecastByNameCallBack getCityForecastByNameCallBack){
        getCityID(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                getCityForecastByNameCallBack.onError(ERROR_MESSAGE);
            }

            @Override
            public void onResponse(String cityID) {
                getForecastByID(cityID, new forecastByIdResponse() {
                    @Override
                    public void onError(String message) {
                        getCityForecastByNameCallBack.onError(ERROR_MESSAGE);
                    }
                    @Override
                    public void onResponse(java.util.List<com.iskandar.weatherapp.WeatherReportModel> weatherReportModel) {
                        getCityForecastByNameCallBack.onResponse(weatherReportModel);
                    }
                });
            }
        });
    }
}
