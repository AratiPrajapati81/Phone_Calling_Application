package com.example.phonecalling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_call;
    EditText et_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_call=findViewById(R.id.buttoncallid);
        et_num=findViewById(R.id.et_numid);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                call_the_Number();
            }
        });

    }

    private void call_the_Number()
    {
        String number=et_num.getText().toString().trim();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1234);

        }
        else
        {
            if(number.length()>0)
            {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
                startActivity(intent);
            }
            else
            {
                et_num.setError("This is mandatory field");
            }
        }

    }
}