package com.example.myapplication;
import android.widget.ImageView;
import android.widget.Toast;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister,adminim;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;
    ImageView logo;
    int score =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
        //   View v = this.getLayoutInflater().inflate(R.layout.progress_bar,null);
        //   dialog.setContentView(v);
        dialog.show();

        db = new DatabaseHelper(this);
        logo = (ImageView) findViewById(R.id.logo);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        adminim = (TextView)findViewById(R.id.adminim);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this,Register.class);
                startActivity(registerIntent);
            }
        });

        adminim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Admin.class);
                startActivity(i);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();

                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {
                    Intent i = new Intent(MainActivity.this,DashboardUser.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


        }


    }










