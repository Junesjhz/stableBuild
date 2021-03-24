package com.iskandar.playgrounds.loginscreen;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    public LoginAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0: return "Login";
            case 1: return "Sign Up";
            default: return "";
        }
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragments(Fragment fragment) {
        fragments.remove(fragment);
    }

    public void removeFragments(int position) {
        fragments.remove(position);
    }
}
