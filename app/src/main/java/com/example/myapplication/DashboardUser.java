package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardUser extends AppCompatActivity {
        LinearLayout addPatient,goHelp,addMedicine,goLogin,goQR;
        DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

      databaseHelper = new DatabaseHelper(this);


        LinearLayout patient = (LinearLayout )findViewById(R.id.addPatient);
        LinearLayout medicine = (LinearLayout )findViewById(R.id.addMedicine);
        LinearLayout login = (LinearLayout )findViewById(R.id.goLogin);
        LinearLayout goQR = (LinearLayout )findViewById(R.id.goQR);

        goQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DashboardUser.this,Scanner.class);
                startActivity(i1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DashboardUser.this,MainActivity.class);
                startActivity(i1);
            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DashboardUser.this,MedicineAddList.class);
                startActivity(i1);
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DashboardUser.this,PatientAddList.class);
                startActivity(i1);
            }
        });
        LinearLayout help = (LinearLayout )findViewById(R.id.goHelp);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(DashboardUser.this,Help.class);
                startActivity(i2);
            }
        });


    }
}
