package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobbies,edtSport;
   private Button  btnUpdateInfo;
    public ProfileTab() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_profile_tab, container, false);
         edtProfileName=view.findViewById(R.id.edtName);
         edtProfileBio=view.findViewById(R.id.edtBio);
         edtProfileHobbies=view.findViewById(R.id.edtHobbies);
         edtProfileProfession=view.findViewById(R.id.etdProfession);
         edtSport=view.findViewById(R.id.edtSport);
         btnUpdateInfo=view.findViewById(R.id.btnUpdateInfo);
        final ParseUser  parseUser=ParseUser.getCurrentUser();

   btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
            parseUser.put("ProfileName",edtProfileName.getText().toString());
            parseUser.put("ProfileBio",edtProfileBio.getText().toString());
            parseUser.put("ProfileProfession",edtProfileProfession.getText().toString());
            parseUser.put("ProfileSport",edtSport.getText().toString());
            parseUser.put("ProfileHobbies",edtProfileHobbies.getText().toString());
          if( parseUser.get("ProfileName")==null){
              edtProfileName.setText("");
          }else{
               edtProfileName.setText(parseUser.get("ProfileName").toString());
           }
           if( parseUser.get("ProfileBio")==null){
               edtProfileBio.setText("");
           }else{
               edtProfileBio.setText(parseUser.get("ProfileBio").toString());
           }
           if( parseUser.get("ProfileProfession")==null){
               edtProfileProfession.setText("");
           }else{
               edtProfileProfession.setText(parseUser.get("ProfileProfession").toString());
           }
           if( parseUser.get("ProfileHobbies")==null){
               edtProfileHobbies.setText("");
           }else{
               edtProfileHobbies.setText(parseUser.get("ProfileHobbies").toString());
           }
           if( parseUser.get("ProfileSport")==null){
               edtSport.setText("");
           }else{
               edtSport.setText(parseUser.get("ProfileSport").toString());
           }

       parseUser.saveInBackground(new SaveCallback() {
           @Override
           public void done(ParseException e) {
               if(e==null){
                   FancyToast.makeText(getContext(),"Your Information is Update ",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
               }else{
                   FancyToast.makeText(getContext(),"Something is not Right",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
               }
           }
       });
       }
   });


    return  view;

    }

}
