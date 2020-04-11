package com.commandus.vapidchatter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.commandus.vapidchatter.R;
import com.commandus.vapidchatter.wpn.Subscription;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DisplayVapidKeyActivity extends AppCompatActivity {

    private static final String TAG = DisplayVapidKeyActivity.class.getSimpleName();
    private ImageView mImageViewQRCode;
    private String mEnv;
    private String vapidPublicKey;
    private ConstraintLayout mLayoutDisplayQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vapid_key);
        mLayoutDisplayQRCode = findViewById(R.id.layoutDisplayQRCode);
        mImageViewQRCode = findViewById(R.id.imageViewQRCode);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        mEnv = Settings.getVapidClient(this).getEnvDescriptor();
        vapidPublicKey = "";
        Intent intent = getIntent();
        if (intent != null) {
            vapidPublicKey = intent.getStringExtra(Settings.VAPID_PUBLIC_KEY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showQRCode();
    }


    private void showQRCode() {
        if (mImageViewQRCode != null) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int w = metrics.widthPixels;
            int h = metrics.heightPixels;
            if (w > h)
                w = h;
            if (!vapidPublicKey.isEmpty()) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(vapidPublicKey, BarcodeFormat.QR_CODE, w, w);
                    mImageViewQRCode.setImageBitmap(bitmap);
                } catch(Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
