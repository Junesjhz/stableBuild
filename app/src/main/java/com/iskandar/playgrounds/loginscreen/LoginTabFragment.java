package com.iskandar.playgrounds.loginscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.iskandar.playgrounds.R;

public class LoginTabFragment extends Fragment {

    EditText login_Email, login_Password;
    Button login_Button;
    float zero = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        login_Email = root.findViewById(R.id.et_loginEmail);
        login_Password = root.findViewById(R.id.et_loginPassword);
        login_Button = root.findViewById(R.id.btn_login);

        login_Email.setTranslationY(800);
        login_Password.setTranslationY(800);
        login_Button.setTranslationY(800);

        login_Email.setAlpha(zero);
        login_Password.setAlpha(zero);
        login_Button.setAlpha(zero);

        login_Email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        login_Password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        login_Button.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();


        return root;
    }
}
