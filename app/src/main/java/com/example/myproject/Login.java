package com.example.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.Model.login_user;
import com.example.myproject.ViewModel.loginUser_ViewModel;

import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText userName;
    private EditText password;
    private Button loginButton;
    private loginUser_ViewModel viewModel;
    private CheckBox remember;
    private static List<login_user> userInf;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init
        userName = findViewById(R.id.login_user);
        password = findViewById(R.id.login_passwd);
        loginButton = findViewById(R.id.login_button);
        remember = findViewById(R.id.remember_passwd);
        loginButton.setOnClickListener(this);
        remember.setOnClickListener(this);
        preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        String preUserID = preferences.getString("USERID", "");
        String prePassWd = preferences.getString("PASSWD", "");
        userName.setText(preUserID);
        password.setText(prePassWd);
        if(!preUserID.equals("")){
            remember.setChecked(true);
        }


        //ViewModel
        viewModel = ViewModelProviders.of(this).get(loginUser_ViewModel.class);
        viewModel.getLoginUser().observe(this, login_users -> userInf = login_users);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            checkUser();
        }

    }

    private void checkUser() {
        String username = userName.getText().toString();
        String passwd = password.getText().toString();

        if (!username.equals("") && !passwd.equals("")) {
            for (int i = 0; i < userInf.size(); i++) {
                if (userInf.get(i).getUsername().equals(username)) {
                    if (userInf.get(i).getPasswd().equals(passwd)) {

                        SharedPreferences.Editor editor = preferences.edit();
                        if (remember.isChecked()) {
                            editor.putString("USERID", userName.getText().toString());
                            editor.putString("PASSWD", password.getText().toString());
                        }else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("userId", userInf.get(i).getUserId());
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast("Wrong Password");
                    }
                } else {
                    if (i - 1 == userInf.size())
                        Toast("NO username");
                }
            }
        } else {
            Toast("Please input your information.");
        }
    }

    private void Toast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

}




