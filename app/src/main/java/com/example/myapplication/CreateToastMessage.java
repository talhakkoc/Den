package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

public class CreateToastMessage {
    public static void showMessage(Context context, String incomingMessage) {
        Toast.makeText(context, incomingMessage, Toast.LENGTH_LONG).show();
    }
}
