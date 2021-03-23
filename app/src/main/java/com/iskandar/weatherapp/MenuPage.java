package com.iskandar.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuPage extends AppCompatActivity implements View.OnClickListener{
    private CardView CV_ITEM01, CV_ITEM02;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page);

        //define cards
        CV_ITEM01 = (CardView) findViewById(R.id.cv_item01);
        CV_ITEM02 = (CardView) findViewById(R.id.cv_item02);

        //add onclicklistener
        CV_ITEM01.setOnClickListener(this);
        CV_ITEM02.setOnClickListener(this);
    }

    @Override
    public void onClick(View cv_onclick){
        Intent onClick_intent;

        switch (cv_onclick.getId()){
            //icon button
            case R.id.cv_item01 : onClick_intent = new Intent(this,ToDoList.class);startActivity(onClick_intent); break;
            case R.id.cv_item02 : onClick_intent = new Intent(this,WeatherForcastActivity.class);startActivity(onClick_intent); break;
            default : break;
        }
    }
}
