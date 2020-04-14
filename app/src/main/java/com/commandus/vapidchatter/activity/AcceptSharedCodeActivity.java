package com.commandus.vapidchatter.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.commandus.vapidchatter.R;
import com.commandus.vapidchatter.wpn.Subscription;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Copy e.g.
 * BMWbr4dF-V8-fdxch8ZaWrGMgvnF_gJ4sQAGJ4ByUKs7hDQmaixBuJkKvoXi6RYYL2DtOtU7Ktig2-IfowSsb4A,Wyg-77tbRl79qfDHL6EWGQ
 */
public class AcceptSharedCodeActivity extends AppCompatActivity {

    private TextView mTextViewCodeType;
    private ImageButton mButtonAccept;
    private String mEnv;
    private String vapidPublicKey;
    private String authSecret;
    private String subscriptionToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_shared_code);
        mTextViewCodeType = findViewById(R.id.textViewLabelAccept2);
        mButtonAccept = findViewById(R.id.imageButtonAccept);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }


        mEnv = Settings.getVapidClient(this).getEnvDescriptor();
        Intent intent = getIntent();

        if (intent != null) {
            String action = intent.getAction();
            Uri data = intent.getData();
            if (data != null) {
                vapidPublicKey = data.getQueryParameter(Settings.VAPID_PUBLIC_KEY);
                authSecret = data.getQueryParameter(Settings.VAPID_AUTH_SECRET);
                subscriptionToken = data.getQueryParameter(Settings.VAPID_TOKEN);
            }
        }
        if (subscriptionToken != null && !subscriptionToken.isEmpty()) {
            if (!Settings.checkVapidToken(subscriptionToken)) {
                Toast.makeText(this, getString(R.string.msg_link_wrong), Toast.LENGTH_LONG).show();
            } else {
                if (mButtonAccept != null) {
                    mButtonAccept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            saveSubscription();
                        }
                    });
                }
            }

        } else {
            if (vapidPublicKey == null || vapidPublicKey.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_link_wrong), Toast.LENGTH_LONG).show();
            } else {
                if (!Settings.checkVapidPublicKey(vapidPublicKey) || !Settings.checkVapidAuthSecret(authSecret)) {
                    Toast.makeText(this, getString(R.string.msg_link_wrong), Toast.LENGTH_LONG).show();
                } else {
                    if (mButtonAccept != null) {
                        mButtonAccept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                subscribeToCode();
                            }
                        });
                    }
                }
            }
        }
    }

    private void saveSubscription() {
        if (Settings.saveSubscription(this, subscriptionToken, authSecret)) {
            Toast.makeText(this, R.string.msg_saved_subscription_successfully, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.msg_err_save_subscription, Toast.LENGTH_LONG).show();
        }
    }

    private void subscribeToCode() {
        Subscription s = Settings.subscribe2VapidKey(this, vapidPublicKey, authSecret);
        if (s == null) {
            Toast.makeText(this, R.string.msg_err_subscribe_to_vapid_key, Toast.LENGTH_LONG).show();
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
