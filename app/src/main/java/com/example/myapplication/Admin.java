package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin extends AppCompatActivity{

    ImageView logoadmin;
    Button btnadmin;
    Button btnmain;
    EditText adminpass;
    EditText adminname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminname = (EditText) findViewById(R.id.adminname);
        adminpass = (EditText) findViewById(R.id.adminpass);
        logoadmin = (ImageView) findViewById(R.id.logoadmin);
        btnadmin = (Button) findViewById(R.id.btnadmin);
        btnmain = (Button) findViewById(R.id.btnmain);


        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String strNamea;
                strNamea = adminname.getText().toString();
                String strPassa;
                strPassa = adminpass.getText().toString();

                if (strNamea.equals("admin") && strPassa.equals("123"))
                    startActivity(new Intent(Admin.this, Dashboard.class));
                else
                    Toast.makeText(getApplicationContext(), "Wrong Name or Password", Toast.LENGTH_SHORT).show();
            }
        });

        btnmain.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this,MainActivity.class));


            }
        });
    }

}