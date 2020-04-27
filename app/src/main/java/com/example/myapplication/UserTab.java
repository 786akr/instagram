package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserTab extends Fragment {
   private ListView listView;
    private ArrayList arrayList;
  private ArrayAdapter arrayAdapter;

    public UserTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_tab, container, false);
        listView = view.findViewById(R.id.Listview);
        arrayList = new ArrayList();

        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);


        ParseQuery<ParseUser> user = ParseUser.getQuery();
        user.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        user.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseUser user : objects) {
                            arrayList.add(user.getUsername());
                       }
                        FancyToast.makeText(getContext(),"done here",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        listView.setAdapter(arrayAdapter);
                }
            }
        }




        });

        return view; }

}