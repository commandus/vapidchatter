package com.commandus.vapidchatter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.commandus.vapidchatter.R;

public class EnterVapidKeyActivity extends AppCompatActivity {

    private EditText mEditTextVapidKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_vapid_key);
        mEditTextVapidKey = findViewById(R.id.editTextVapidKey);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String vapidPublicKey = "";
        Intent intent = getIntent();
        if (intent != null) {
            vapidPublicKey = intent.getStringExtra(Settings.VAPID_PUBLIC_KEY);
        }
        if (vapidPublicKey == null || vapidPublicKey.isEmpty()) {
            vapidPublicKey = Settings.getClipboardText(this);
        }
        if (Settings.checkVapidPublicKey(vapidPublicKey)) {
            if (mEditTextVapidKey != null) {
                mEditTextVapidKey.setText(vapidPublicKey);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
