package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName,edtpassword,edtEmail;
    Button btnLogin,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   getSupportActionBar().setTitle("Sign Up");
        ParseInstallation.getCurrentInstallation().saveInBackground();// build a connection with parse server
         edtEmail=findViewById(R.id.edtEmail);
         edtpassword=findViewById(R.id.edtPassword);
         edtpassword.setOnKeyListener(new View.OnKeyListener() {
             @Override
             public boolean onKey(View v, int keyCode, KeyEvent event) {
                 if( keyCode== KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){

onClick(btnSignUp);
                 }
                 return false;
             }
         });
         edtUserName=findViewById(R.id.edtUserName);
         btnLogin=findViewById(R.id.btnLogin);
         btnSignUp=findViewById(R.id.btnSignUp);

            btnSignUp.setOnClickListener(MainActivity.this);
            btnLogin.setOnClickListener(MainActivity.this);
            if(ParseUser.getCurrentUser()  !=null) {
                 ParseUser.getCurrentUser().logOut();

            }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                if (edtEmail.getText().toString().equals("")||
                edtpassword.getText().toString().equals("")||edtUserName.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this,"NEED ALL REQUIRED TO FILL UP ",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }else {

                    final ParseUser AppUser = new ParseUser();
                    AppUser.setEmail(edtEmail.getText().toString());
                    AppUser.setPassword(edtpassword.getText().toString());
                    AppUser.setUsername(edtUserName.getText().toString());
                    final ProgressDialog process = new ProgressDialog(MainActivity.this);
                    process.setMessage(" Signing Up " + edtUserName.getText().toString());
                    process.show();
                    AppUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                FancyToast.makeText(MainActivity.this, AppUser.getUsername() + " is Signed up", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                trasistionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage() + "ERROR", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            process.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:
                Intent   intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void RootcCickMethod(View view) {
try {
    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

}catch(Exception e){
    e.printStackTrace();

}
    }
    private void trasistionToSocialMediaActivity(){
        Intent intent=new Intent(MainActivity.this,SocialMedia_Activity.class);
        startActivity(intent);
    }
}

