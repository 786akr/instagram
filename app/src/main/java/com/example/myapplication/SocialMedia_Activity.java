package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class SocialMedia_Activity extends AppCompatActivity {
private Toolbar toolbar;
private ViewPager viewPageR;
private TabLayout tabLayout;
private TabAdapter tabAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_);
      setTitle("Social media App");
      toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       viewPageR=findViewById(R.id.viewPager);
       tabAdapter=new TabAdapter(getSupportFragmentManager());
       viewPageR.setAdapter(tabAdapter);
       tabLayout=findViewById(R.id.tabLayout);
       tabLayout.setupWithViewPager(viewPageR,true);


    }
}
