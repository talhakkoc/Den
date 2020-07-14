package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.renderscript.Sampler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class Alindi extends AppCompatActivity {

    DatabaseHelper database;

    EditText editTextGelecek, editTextDegisecek,Username;
    TextView bilgi;
    Button btnMedicinePage,btnMain,btnAdd;
    String st;
    String nom = null;
    Button alismi;
    ImageView imageView,im1,im2,im3,im4;

    String kitapAdi = null, yazarAdi = null, yayinevi = null;
    String ilacAdi = null;
    String aldik=null;
    int ilacBarcode=0;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alindi);

        database  =new DatabaseHelper(this);


        //   imageView = (ImageView) findViewById(R.id.imageView);
        im1 =(ImageView) findViewById(R.id.im1);
        im2 =(ImageView) findViewById(R.id.im2);
        im3 =(ImageView) findViewById(R.id.im3);
        im4 =(ImageView) findViewById(R.id.im4);


        btnMain =(Button)findViewById(R.id.btnMain);
        btnAdd =(Button)findViewById(R.id.btnAdd);
        btnMedicinePage =(Button)findViewById(R.id.btnMedicinePage);

        bilgi = (TextView) findViewById(R.id.bilgi);
        editTextGelecek = (EditText) findViewById(R.id.editTextGelecek);
        editTextDegisecek = (EditText) findViewById(R.id.editTextDegisecek);
        Username = (EditText) findViewById(R.id.Username);
        alismi = (Button) findViewById(R.id.alismi);
        editTextGelecek.setText("BAK BEKLIYOR");


        nom = getIntent().getExtras().getString("Value", null);
        editTextGelecek.setText(nom);
        nom.substring(nom.length() - 3);
        final String substring = nom.substring(Math.max(nom.length() - 2, 0));
        final int x = View.VISIBLE;
        if (nom.length() > 3) {

            editTextDegisecek.setText(substring);

        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yazarAdi = Username.getText().toString();

               yazarAdi = Username.getText().toString();
//                ilacBarcode = Integer.parseInt(editTextDegisecek.getText().toString());

                    database.ilacEkle2(ilacAdi, ilacBarcode,yazarAdi);


                CreateToastMessage.showMessage(getApplicationContext(), "EKLENDI.");
                }

        });

        alismi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setVisibility(View.VISIBLE);
                btnMedicinePage.setVisibility(View.VISIBLE);
                btnMain.setVisibility(View.VISIBLE);
                Username.setVisibility(View.VISIBLE);

                if (nom.equals("8699514095613")) {
                    ferrum();
                    editTextDegisecek.setText(ilacAdi);

                }
                else if (nom.equals("8699832090055"))
                    arveles();

                else if (nom.equals("8699809011625"))
                    delix();

                else if (nom.equals("8699525094643"))
                    bactrim();
            }



        });

    }

    public void ferrum() {

        im2.setVisibility(View.VISIBLE);
        im1.setVisibility(View.INVISIBLE);

        String ilacadi = "Ferrum";
        String barkodno = nom;
        int fiyat = (int) 30.5;

        int serino = (int) 290937000654982L;
        bilgi.setText("\n\n\nMEDICINE NAME             :  " + ilacadi + "\n\nBARKODE NO             :  " + nom + "\n\nPRICE             :  " + fiyat + "\n\nSeri No            :" + serino + "\n\n" + "-------" + "\n");


    }

    public void arveles() {
        im1.setVisibility(View.VISIBLE);

        String ilacadi = "Arveles";
        String barkodno = nom;
        int fiyat = (int) 20;

        int serino = (int) 00000127106142L;

        bilgi.setText("\n\n\nMEDICINE NAME             :  " + ilacadi + "\n\nBARKODE NO             :  " + nom + "\n\nPRICE             :  " + fiyat + "\n\nSeri No            :" + serino + "\n\n" + "-------" + "\n");

    }


    public void delix() {
        im3.setVisibility(View.VISIBLE);

        String ilacadi = "Delix";
        String barkodno = nom;
        int fiyat = (int) 12;

        int serino = (int) 33000167547913L;

        bilgi.setText("\n\n\nMEDICINE NAME             :  " + ilacadi + "\n\nBARKODE NO             :  " + nom + "\n\nPRICE             :  " + fiyat + "\n\nSeri No            :" + serino + "\n\n" + "-------" + "\n");

    }

    public void bactrim() {

        im4.setVisibility(View.VISIBLE);

        String ilacadi = "Bactrim";
        String barkodno = nom;
        int fiyat = (int) 100;

        int serino = (int) 33010005155918L;

        bilgi.setText("\n\n\nMEDICINE NAME             :  " + ilacadi + "\n\nBARKODE NO             :  " + nom + "\n\nPRICE             :  " + fiyat + "\n\nSeri No            :" + serino + "\n\n" + "-------" + "\n");

    }




}