package com.example.myapplication;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MedicineAddList extends AppCompatActivity {

    EditText editTextIlacAdi, editTextYazarAdi, editTextYayinevi, editTextIlacBarcode, editTextIlacID;

    Button btnIlacSave, btnSearchMedicine, btnDeleteMedicine, btnGetAllMedicine, btnOzel, btnGec,btnFullDelete;

    DatabaseHelper database;

    String kitapAdi = null, yazarAdi = null, yayinevi = null;
    String ilacAdi = null;
    String aldik=null;
    int ilacBarcode=0;
    int sayfa = 0, kitapId = 0;

    TextView goruntule, goruntuleNick;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add_list);
        getControlView();
        setClickForView();
        database  =new DatabaseHelper(this);
        // SQLiteDatabase db = new Database(this).getWritableDatabase();
        database.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String value = getIntent().getStringExtra("key");
            goruntuleNick.setText( value);
            //The key argument here must match that used in the other activity

        }
    }

    private void getControlView() {
        goruntule = (TextView) findViewById(R.id.goruntule);
        goruntuleNick = (TextView) findViewById(R.id.goruntuleNick);
        editTextIlacAdi = (EditText) findViewById(R.id.editTextIlacAdi);
        editTextIlacID = (EditText) findViewById(R.id.editTextIlacID);
        editTextIlacBarcode = (EditText) findViewById(R.id.editTextIlacBarcode);
        editTextYazarAdi = (EditText) findViewById(R.id.editTextYazarAdi);
        btnSearchMedicine = (Button) findViewById(R.id.btnSearchMedicine);
        btnDeleteMedicine = (Button) findViewById(R.id.btnDeleteMedicine);
        btnIlacSave = (Button) findViewById(R.id.btnIlacSave);
        btnGetAllMedicine = (Button) findViewById(R.id.btnGetAllMedicine);
        btnOzel = (Button) findViewById(R.id.btnOzel);
        //btnGec = (Button) findViewById(R.id.btnGec);
        btnFullDelete = (Button) findViewById(R.id.btnFullDelete);

        goruntule.setMovementMethod(new ScrollingMovementMethod());
    }


    private void setClickForView() {
        /*ID bilgisi girilen kitap için arama yapar.*/
        btnSearchMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ilacAdi = Integer.parseInt(editTextIlacAdi.getText().toString());
                String[] gelenMedicineBilgileri = database.medicineGostermeYontem1(ilacAdi);
                Log.i("gelenMedicineBilgileri", "" + gelenMedicineBilgileri[0]);
                if (gelenMedicineBilgileri.length > 1) {
                    editTextIlacAdi.setText(gelenMedicineBilgileri[0]);
                    editTextIlacBarcode.setText(gelenMedicineBilgileri[1]);
                    editTextYazarAdi.setText(gelenMedicineBilgileri[2]);


                } else {
                    CreateToastMessage.showMessage(getApplicationContext(), "Böyle bir kayıt yok.");
                }

            }
        });

        btnOzel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goruntule.setText(database.tumMedicineYontem9(editTextYazarAdi.getText().toString()));

                CreateToastMessage.showMessage(getApplicationContext(), "Böyle bir USER KAYDI yok.");

            }
        });

        //   denedik();
        btnIlacSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ilacAdi = editTextIlacAdi.getText().toString();
                yazarAdi = editTextYazarAdi.getText().toString();
                ilacBarcode = Integer.parseInt(editTextIlacBarcode.getText().toString());
                aldik=goruntuleNick.getText().toString().trim();
                if (ilacAdi.equals("") || yazarAdi.equals("") ) {
                    CreateToastMessage.showMessage(getApplicationContext(), "You Entered Missing Data");

                } else {

                    database.ilacEkle2(ilacAdi, ilacBarcode,yazarAdi);

                    editTextIlacAdi.setText("");
                    editTextIlacID.setText("");
                    editTextIlacBarcode.setText("");
                }
            }
        });

        btnGetAllMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilacAdi = editTextIlacAdi.getText().toString();

                goruntule.setText(database.tumMedicineGostermeYontem1());

                CreateToastMessage.showMessage(getApplicationContext(), database.tumMedicineGostermeYontem1());

            }

        });

        btnFullDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteAll();
                CreateToastMessage.showMessage(getApplicationContext(), "ALL RECORDS HAVE BEEN DELETED");
                database.close();
            }
        });



    }
}
