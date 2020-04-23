package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
 private EditText  name,PunchPower,PunchSpeed,PunchDamage;
 Button    btnSubmit;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     ParseInstallation.getCurrentInstallation().saveInBackground();// build a connection with parse server
   //  FancyToast.makeText(MainActivity.this, "This is Custom Toast with no android icon", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, R.drawable.warning_shape, false).show();
     name = findViewById(R.id.edtName);

     PunchDamage = findViewById(R.id.edtPunchDamage);
     PunchPower = findViewById(R.id.edtPunchPower);
     PunchSpeed = findViewById(R.id.edtPunchSpeed);
     btnSubmit = findViewById(R.id.button);

     btnSubmit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             try {
                 ParseObject kick = new ParseObject("kickBoxer");
                 String name1 = name.getText().toString();
                 int Power = Integer.parseInt(PunchPower.getText().toString());
                 int Damage = Integer.parseInt(PunchDamage.getText().toString());
                 int Speed = Integer.parseInt(PunchSpeed.getText().toString());
                 kick.put("name", name1);
                 kick.put("punchSpeed", Speed);
                 kick.put("punchPower", Power);
                 kick.put("PunchDamage", 600);
                // FancyToast.makeText(MainActivity.this, "This is Custom Toast with no android icon", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, R.drawable.info_shape, false).show();
                 kick.saveInBackground(new SaveCallback() {
                     @Override
                     public void done(ParseException e) {
                         if (e == null) {
                             FancyToast.makeText(MainActivity.this, " Successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                         }
                     }
                 });

             }catch(Exception e){
                 FancyToast.makeText(MainActivity.this, e.getMessage()+"", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

             }
         }
     });
 }
}

