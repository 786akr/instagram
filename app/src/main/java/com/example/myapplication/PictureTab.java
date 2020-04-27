package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PictureTab extends Fragment implements View.OnClickListener{
private ImageView imgPicture;
private Button btnShareImage;
private EditText  edtDesc;
    public PictureTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  view = inflater.inflate(R.layout.fragment_picture_tab, container, false);
      btnShareImage=view.findViewById(R.id.btnShareImage);
      imgPicture=view.findViewById(R.id.imgPicture);
      edtDesc=view.findViewById(R.id.edtDesc);
        imgPicture.setOnClickListener(PictureTab.this);
        btnShareImage.setOnClickListener(PictureTab.this);
      return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imgPicture:
                if(android.os.Build.VERSION.SDK_INT>=23&&
                        ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]  {Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                }else{
                    getChosenImage();
                }


                break;
            case R.id.btnShareImage:
                break;

        }

        }

    private void getChosenImage() {

        FancyToast.makeText(getContext(),"Success",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if( grantResults.length >=0 &&
            grantResults[0]==PackageManager.PERMISSION_GRANTED){
        getChosenImage();
    }

    }
}
