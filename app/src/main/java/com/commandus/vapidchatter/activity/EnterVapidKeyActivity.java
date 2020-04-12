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

/**
 * Copy e.g.
 * BMWbr4dF-V8-fdxch8ZaWrGMgvnF_gJ4sQAGJ4ByUKs7hDQmaixBuJkKvoXi6RYYL2DtOtU7Ktig2-IfowSsb4A,Wyg-77tbRl79qfDHL6EWGQ
 */
public class EnterVapidKeyActivity extends AppCompatActivity {

    private EditText mEditTextVapidKey;
    private EditText mEditTextAuthSecret;
    private ImageButton mButtonVapidKey;
    private String mEnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_vapid_key);
        mEditTextVapidKey = findViewById(R.id.editTextVapidKey);
        mEditTextAuthSecret = findViewById(R.id.editTextAuthSecret);
        mButtonVapidKey = findViewById(R.id.imageButtonEnterVapidKey);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        mEnv = Settings.getVapidClient(this).getEnvDescriptor();
        String vapidPublicKey = "";
        String authSecret = "";
        Intent intent = getIntent();
        if (intent != null) {
            vapidPublicKey = intent.getStringExtra(Settings.VAPID_PUBLIC_KEY);
            authSecret = intent.getStringExtra(Settings.VAPID_AUTH_SECRET);
        }
        if (vapidPublicKey == null || vapidPublicKey.isEmpty()) {
            String cs = Settings.getClipboardText(this);
            // clipboard can contain public key or public key with auth(comma delimited)
            String[] d = cs.split(",");
            if (d.length == 2) {
                vapidPublicKey = d[0];
                authSecret = d[1];
            } else {
                vapidPublicKey = cs;
            }
        }
        if (Settings.checkVapidPublicKey(vapidPublicKey)) {
            if (mEditTextVapidKey != null) {
                mEditTextVapidKey.setText(vapidPublicKey);
            }
        }
        if (Settings.checkVapidAuthSecret(authSecret)) {
            if (mEditTextAuthSecret != null) {
                mEditTextAuthSecret.setText(authSecret);
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
        if (mEditTextAuthSecret != null) {
            mEditTextAuthSecret.setOnKeyListener(new View.OnKeyListener() {
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
        String vapidPublicKey =  mEditTextVapidKey.getText().toString();
        String authSecret = mEditTextAuthSecret.getText().toString();
        if (vapidPublicKey.isEmpty() || authSecret.isEmpty()) {
            return;
        }
        if (!Settings.checkVapidPublicKey(vapidPublicKey) || !Settings.checkVapidAuthSecret(authSecret)) {
            return;
        }

        Toast.makeText(this, getString(R.string.prompt_scan_code_processing), Toast.LENGTH_SHORT).show();
        Subscription s = Settings.subscribe2VapidKey(EnterVapidKeyActivity.this,
                vapidPublicKey, authSecret);
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
