package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView tw;
    TextView tw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tw =(TextView)findViewById(R.id.tw);
        tw2 =(TextView)findViewById(R.id.tw2);


    }
}
