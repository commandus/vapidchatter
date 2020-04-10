package com.commandus.vapidchatter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.commandus.vapidchatter.R;
import com.commandus.vapidchatter.wpn.Subscription;

public class EnterVapidKeyActivity extends AppCompatActivity {

    private EditText mEditTextVapidKey;
    private ImageButton mButtonVapidKey;
    private String mEnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_vapid_key);
        mEditTextVapidKey = findViewById(R.id.editTextVapidKey);
        mButtonVapidKey = findViewById(R.id.imageButtonEnterVapidKey);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mEnv = Settings.getVapidClient(this).getEnvDescriptor();
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

        if (mButtonVapidKey != null) {
            mButtonVapidKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Subscription s = Settings.subscribe2VapidKey(EnterVapidKeyActivity.this, mEditTextVapidKey.getText().toString());
                    if (s == null) {
                        Toast.makeText(EnterVapidKeyActivity.this, R.string.msg_err_subscribe_to_vapid_key, Toast.LENGTH_LONG);
                    } else {
                        Intent intent = getIntent();
                        intent.putExtra(Settings.SUBSCRIPTION, s.toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
