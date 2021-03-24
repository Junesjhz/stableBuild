package com.iskandar.playgrounds.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.iskandar.playgrounds.MenuPage;
import com.iskandar.playgrounds.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView bg_splash, logo_splash;
    TextView tv_splash;
    LottieAnimationView lottie_splash;
    private static int SPLASH_TIME_OUT = 3200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        bg_splash = findViewById(R.id.bg_splash);
        logo_splash = findViewById(R.id.logo_splash);
        tv_splash = findViewById(R.id.tv_splash);
        lottie_splash = findViewById(R.id.lottie_splash);

        bg_splash.animate().translationY(-3200).setDuration(1000).setStartDelay(2500);
        logo_splash.animate().translationY(2800).setDuration(1000).setStartDelay(2500);
        tv_splash.animate().translationY(2800).setDuration(1000).setStartDelay(2500);
        lottie_splash.animate().translationY(2800).setDuration(1000).setStartDelay(2500);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent nextScreenIntent = new Intent(SplashScreenActivity.this, MenuPage.class);
                startActivity(nextScreenIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}