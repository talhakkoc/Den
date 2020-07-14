package com.example.myapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;



import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zXingScannerView;
    final Context context = this;
    String st;
    String nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
    }

    public void scan(View view){
        zXingScannerView =new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result result) {
        nom = result.getText();
        Toast.makeText(getApplicationContext(),nom,Toast.LENGTH_SHORT).show();
        zXingScannerView.resumeCameraPreview(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String yourStirng = extras.getString("yourKeyString");
        }

        new AlertDialog.Builder(context)
                .setTitle("Barcode Result")

                .setMessage("Do You Want To Take This Result\n"+ nom)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(Scanner.this,Alindi.class);

                        i.putExtra("Value",nom);
                        startActivity(i);
                        finish();

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
