package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public void telegramImmortalIdiot(View v) {
        try {
            Intent telegram = new Intent(Intent.ACTION_VIEW);
            telegram.setData(Uri.parse("https://t.me/Immortal_Idiot"));
            startActivity(telegram);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void telegramBestPrincess(View v) {
        try {
            Intent telegram = new Intent(Intent.ACTION_VIEW);
            telegram.setData(Uri.parse("https://t.me/+79102632122"));
            startActivity(telegram);
        } catch (Exception e) { throw new RuntimeException(e); }
    }


    public void finishActivity(View view) { this.finish(); }
}