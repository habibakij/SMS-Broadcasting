package com.notificationshare.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_KEY=0;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.otpbtn);
        editText= findViewById(R.id.otptext);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, PERMISSION_REQUEST_KEY);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestcode, String permissions[], int[] grandresults){
        switch (requestcode){
            case PERMISSION_REQUEST_KEY:
                if (grandresults.length>0 && grandresults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Thanks for permitings", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "You must permite me", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
