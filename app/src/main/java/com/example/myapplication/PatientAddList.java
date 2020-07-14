package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientAddList extends AppCompatActivity {

    EditText editTextKitapAdi, editTextYazarAdi, editTextYayinevi, editTextSayfa, editTextKitapID;

    Button btnSave, btnSearch, btnDelete, btnGetAllBooks, btnUpdate,btnGec;

    DatabaseHelper database;

    String kitapAdi = null, yazarAdi = null, yayinevi = null;

    int sayfa = 0, kitapId = 0;

    TextView goruntule;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add_list);
        database = new DatabaseHelper(this);
       getControlView();
       setClickForView();



    }

    private void getControlView() {
        goruntule = (TextView) findViewById(R.id.goruntule);

        editTextKitapID = (EditText) findViewById(R.id.editTextKitapID);
        editTextKitapAdi = (EditText) findViewById(R.id.editTextKitapAdi);
        editTextYazarAdi = (EditText) findViewById(R.id.editTextYazarAdi);
        editTextYayinevi = (EditText) findViewById(R.id.editTextYayinevi);
        editTextSayfa = (EditText) findViewById(R.id.editTextSayfa);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnGetAllBooks = (Button) findViewById(R.id.btnGetAllBooks);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnGec = (Button) findViewById(R.id.btnGec);

       goruntule.setMovementMethod(new ScrollingMovementMethod());



    }



    private void setClickForView() {
        /*      /*ID bilgisi girilen kitap için arama yapar.*/
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kitapId = Integer.parseInt(editTextKitapID.getText().toString());
                String[] gelenKitapBilgileri = database.kitapGostermeYontem1(kitapId);
                Log.i("gelenKitapBilgileri", "" + gelenKitapBilgileri[0]);
                if (gelenKitapBilgileri.length > 1) {
                    editTextKitapAdi.setText(gelenKitapBilgileri[0]);
                    editTextYazarAdi.setText(gelenKitapBilgileri[1]);
                    editTextYayinevi.setText(gelenKitapBilgileri[2]);
                    editTextSayfa.setText(gelenKitapBilgileri[3]);
                } else {
                    CreateToastMessage.showMessage(getApplicationContext(), "Böyle bir kayıt yok.");
                }

            }
        });


        /*Kitap güncellemek için kullanılır.*/
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goruntule.setText(database.tumKitaplarYontem9(editTextYazarAdi.getText().toString()));

                CreateToastMessage.showMessage(getApplicationContext(), "Böyle bir USER KAYDI yok.");
            }


        });


        /*ID bilgisi girilen kitabı silmek için kullanılır.*/
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kitapId = Integer.parseInt(editTextKitapID.getText().toString());
                database.kitapSil(kitapId);
            }
        });
        /*kitap eklemek için kullanılır.*/
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kitapAdi = editTextKitapAdi.getText().toString();
                yazarAdi = editTextYazarAdi.getText().toString();
                yayinevi = editTextYayinevi.getText().toString();
                sayfa = Integer.parseInt(editTextSayfa.getText().toString());
                if (kitapAdi.equals("") || yazarAdi.equals("") || yayinevi.equals(""))  {

                    CreateToastMessage.showMessage(getApplicationContext(),"You Entered Missing Data");
                } else {
                    database.kitapEkle(kitapAdi, yazarAdi, yayinevi, sayfa);
                    editTextKitapAdi.setText("");
                    editTextYazarAdi.setText("");
                    editTextYayinevi.setText("");
                    editTextSayfa.setText("");

                }
            }
        });
        /*kayıtlı tüm kitapları listelemek için kullanılır.*/
        btnGetAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goruntule.setText(database.tumKitaplarYontem1());

                CreateToastMessage.showMessage(getApplicationContext(), database.tumKitaplarYontem1());
            }
        });

        btnGec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=editTextYazarAdi.getText().toString().trim();
                Intent i = new Intent(PatientAddList.this, DashboardUser.class);
                i.putExtra("key",value);
                startActivity(i);
                database.close(); //    BEN EKLEDIM
            }
        });

    }
}