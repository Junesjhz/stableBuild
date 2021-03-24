package com.iskandar.playgrounds.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.iskandar.playgrounds.R;

public class LoginPage extends AppCompatActivity {

    TabLayout tl_loginSignup;
    ViewPager vp_loginSignup;
    FloatingActionButton fab_facebook, fab_twitter, fab_instagram;
    float zero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        tl_loginSignup = findViewById(R.id.tl_tabLayout01);
        vp_loginSignup = findViewById(R.id.vp_pager01);
        fab_facebook = findViewById(R.id.fab_item01);
        fab_twitter = findViewById(R.id.fab_item02);
        fab_instagram = findViewById(R.id.fab_item03);

        tl_loginSignup.addTab(tl_loginSignup.newTab().setText("Login"));
        tl_loginSignup.addTab(tl_loginSignup.newTab().setText("Sign Up"));
        tl_loginSignup.setTabGravity(tl_loginSignup.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this);
        vp_loginSignup.setAdapter(adapter);
        tl_loginSignup.setupWithViewPager(vp_loginSignup);

        vp_loginSignup.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl_loginSignup));

        fab_facebook.setTranslationY(300);
        fab_twitter.setTranslationY(300);
        fab_instagram.setTranslationY(300);
        tl_loginSignup.setTranslationY(300);

        fab_facebook.setAlpha(zero);
        fab_twitter.setAlpha(zero);
        fab_instagram.setAlpha(zero);
        tl_loginSignup.setAlpha(zero);

        fab_facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        fab_twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        fab_instagram.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tl_loginSignup.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }
}