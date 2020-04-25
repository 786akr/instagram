package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtpassword1, edtEmail1;
    Button btnLogin1, btnSignUp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        edtEmail1 = findViewById(R.id.btnEmailLoginActivity);
        edtpassword1 = findViewById(R.id.btnPasswordLoginActivity);
        btnLogin1= findViewById(R.id.btnLoginLoginActivity);
        btnSignUp1 = findViewById(R.id.btnSignUpLoginActivity);
        btnSignUp1.setOnClickListener(LoginActivity.this);
        btnLogin1.setOnClickListener(LoginActivity.this);
        if(ParseUser.getCurrentUser()  !=null) {
            trasistionToSocialMediaActivity();

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginLoginActivity:
                if(edtEmail1.getText().toString().equals("")||edtpassword1.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this,"Fill Both to Login ",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }else {
                    ParseUser.logInInBackground(edtEmail1.getText().toString(), edtpassword1.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                trasistionToSocialMediaActivity();
                                FancyToast.makeText(LoginActivity.this,
                                        " is Login", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true)
                                        .show();
                            } else {
                                FancyToast.makeText(LoginActivity.this,
                                        "Eror", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true)
                                        .show();

                            }

                        }


                    });
                }
                break;
            case R.id.btnSignUpLoginActivity:
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;

        }
    }
    private void trasistionToSocialMediaActivity(){
        Intent intent=new Intent(LoginActivity.this,SocialMedia_Activity.class);
        startActivity(intent);
    }
}
