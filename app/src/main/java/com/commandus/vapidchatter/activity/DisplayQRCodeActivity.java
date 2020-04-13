package com.commandus.vapidchatter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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

public class DisplayQRCodeActivity extends AppCompatActivity {

    private static final String TAG = DisplayQRCodeActivity.class.getSimpleName();
    private ImageView mImageViewQRCode;
    private ImageButton mImageButtonShareQRCode;
    private String vapidPublicKey;
    private String authSecret;
    private String subscriptionRecord;
    private ConstraintLayout mLayoutDisplayQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vapid_key);
        mLayoutDisplayQRCode = findViewById(R.id.layoutDisplayQRCode);
        mImageViewQRCode = findViewById(R.id.imageViewQRCode);
        mImageButtonShareQRCode = findViewById(R.id.imageButtonShareQRCode);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        if (intent != null) {
            vapidPublicKey = intent.getStringExtra(Settings.VAPID_PUBLIC_KEY);
            authSecret = intent.getStringExtra(Settings.VAPID_AUTH_SECRET);
            subscriptionRecord = intent.getStringExtra(Settings.SUBSCRIPTION);
        }
        if (vapidPublicKey == null)
            vapidPublicKey = "";
        if (authSecret == null)
            authSecret = "";
        if (subscriptionRecord == null)
            subscriptionRecord = "";
        if (vapidPublicKey.isEmpty() && subscriptionRecord.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_scan_code_wrong), Toast.LENGTH_LONG).show();
            finish();
        }
        if (mImageButtonShareQRCode != null) {
            mImageButtonShareQRCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareCode();
                }
            });
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

            String value = getCode();
            if (!value.isEmpty()) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(value, BarcodeFormat.QR_CODE, w, w);
                    mImageViewQRCode.setImageBitmap(bitmap);
                } catch(Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
    }

    private String getCode() {
        String value;
        if (!vapidPublicKey.isEmpty()) {
            value = vapidPublicKey + "," + authSecret;
        } else {
            if (!subscriptionRecord.isEmpty()) {
                Subscription s = new Subscription(subscriptionRecord);
                value = s.getToken()  + "," + s.authSecret;
            } else {
                value = "";
            }
        }
        return value;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void shareCode() {
        String value = getCode();
        if (!value.isEmpty()) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_code));
                String shareMessage = getString(R.string.msg_share_code) + " ";
                if (!vapidPublicKey.isEmpty()) {
                    shareMessage += Settings.getShareLink(vapidPublicKey, authSecret);
                } else {
                    Subscription s = new Subscription(subscriptionRecord);
                    shareMessage += Settings.getShareLinkSubscription(s.getToken(), s.authSecret);
                }

                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, getString(R.string.choose_share)));
            } catch(Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

}
