package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import static com.shashank.sony.fancytoastlib.FancyToast.ERROR;
import static com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG;
import static com.shashank.sony.fancytoastlib.FancyToast.SUCCESS;
import static com.shashank.sony.fancytoastlib.FancyToast.makeText;

public class MainActivity extends AppCompatActivity {
    private EditText name, PunchPower, PunchSpeed, PunchDamage;
    Button btnSubmit,getall;
    private TextView getdata;
    private String kick;
    private String kick2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();// build a connection with parse server
        //  FancyToast.makeText(MainActivity.this, "This is Custom Toast with no android icon", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, R.drawable.warning_shape, false).show();
        name = findViewById(R.id.edtName);
        getall=findViewById(R.id.getAll);
        PunchDamage = findViewById(R.id.edtPunchDamage);
        PunchPower = findViewById(R.id.edtPunchPower);
        PunchSpeed = findViewById(R.id.edtPunchSpeed);
        btnSubmit = findViewById(R.id.button);
       getdata= findViewById(R.id.GetData);
        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kick="";
                kick2="";

                ParseQuery<ParseObject>  all=ParseQuery.getQuery("kickBoxer");
                all.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null) {
                            if (objects.size() > 0) {
                                for(ParseObject kicky: objects){
                                    kick=kick+kicky.get("PunchDamage")+"\n";

                                }
                                FancyToast.makeText(MainActivity.this, kick , LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }else{
                                FancyToast.makeText(MainActivity.this, "hello", LENGTH_LONG, ERROR, true).show();
                            }
                        }
                        }

                });
            }
        });
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ParseQuery<ParseObject> parseObj = ParseQuery.getQuery("kickBoxer");
                    parseObj.getInBackground("5idFM6TUZF", new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                                  if(object != null && e == null) {
                                      getdata.setText(object.get("punchPower") + "" + "\n" + object.get("name"));
                                      makeText(MainActivity.this, "hello", LENGTH_LONG, ERROR, true).show();
                                  }
                        }
                    });

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

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
                                makeText(MainActivity.this, " Successfully!", LENGTH_LONG, SUCCESS, true).show();
                            }
                        }
                    });

                } catch (Exception e) {
                    makeText(MainActivity.this, e.getMessage() + "", LENGTH_LONG, ERROR, true).show();

                }
            }
        });


    }
}

