package com.iskandar.playgrounds.loginscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.iskandar.playgrounds.R;

public class SignupTabFragment extends Fragment {

    EditText signup_Email, signup_mobileNumber, signup_Password, signup_ConfirmPassword;
    Button signup_Button;
    float zero = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        signup_Email = root.findViewById(R.id.et_signupEmail);
        signup_mobileNumber = root.findViewById(R.id.et_signupPassword);
        signup_Password = root.findViewById(R.id.et_signupPassword);
        signup_ConfirmPassword = root.findViewById(R.id.et_confirmPassword);
        signup_Button = root.findViewById(R.id.btn_signUp);

        signup_Email.setTranslationY(800);
        signup_mobileNumber.setTranslationY(800);
        signup_Password.setTranslationY(800);
        signup_ConfirmPassword.setTranslationY(800);
        signup_Button.setTranslationY(800);

        signup_Email.setAlpha(zero);
        signup_mobileNumber.setAlpha(zero);
        signup_Password.setAlpha(zero);
        signup_ConfirmPassword.setAlpha(zero);
        signup_Button.setAlpha(zero);

        signup_Email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(10).start();
        signup_mobileNumber.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        signup_Password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        signup_ConfirmPassword.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        signup_Button.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        return root;
    }
}
