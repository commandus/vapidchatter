package com.commandus.vapidchatter.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

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

        if (mEditTextVapidKey != null) {
            mEditTextVapidKey.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_DPAD_CENTER:
                            case KeyEvent.KEYCODE_ENTER:
                                save();
                                return true;
                            default:
                                break;
                        }
                    }
                    return false;
                }
            });
        }

        if (mButtonVapidKey != null) {
            mButtonVapidKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save();
                }
            });
        }
    }

    private void save() {
        Toast.makeText(this, getString(R.string.prompt_scan_code_processing), Toast.LENGTH_SHORT).show();
        Subscription s = Settings.subscribe2VapidKey(EnterVapidKeyActivity.this, mEditTextVapidKey.getText().toString());
        if (s == null) {
            Toast.makeText(EnterVapidKeyActivity.this, R.string.msg_err_subscribe_to_vapid_key, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = getIntent();
            intent.putExtra(Settings.SUBSCRIPTION, s.toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
