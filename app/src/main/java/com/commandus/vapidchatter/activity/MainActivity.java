package com.commandus.vapidchatter.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.commandus.vapidchatter.R;
import com.commandus.vapidchatter.service.VapidChatterService;
import com.commandus.vapidchatter.wpn.*;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RET_ENTER_KEY = 1;
    private static final int RET_DISPLAY_KEY = 2;

    private VapidClient mClient;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = Settings.getVapidClient(this);
        mSubscription = new Subscription();

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(wpnAndroid.version());

        init();
    }

    private void init() {
        startService(new Intent(this, VapidChatterService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_enter_vapid_key:
                Intent intent = new Intent(this, EnterVapidKeyActivity.class);
                startActivityForResult(intent, RET_ENTER_KEY);
                break;
            case R.id.action_display_vapid_key:
                if (mClient != null) {
                    Intent intent2 = new Intent(this, DisplayQRCodeActivity.class);
                    intent2.putExtra(Settings.VAPID_PUBLIC_KEY, mClient.getConfig().keys.publicKey);
                    intent2.putExtra(Settings.VAPID_AUTH_SECRET, mClient.getConfig().keys.authSecret);
                    startActivityForResult(intent2, RET_DISPLAY_KEY);
                }
                break;
            case R.id.action_display_subscription:
                if (mClient != null) {
                    displaySubscriptionQRCode();
                }
                break;
            case R.id.action_scan_vapid_key:
                Settings.startScanCode(this, getString(R.string.toast_scan_vapid_key));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySubscriptionQRCode() {
        Intent intent2 = new Intent(this, DisplayQRCodeActivity.class);
        intent2.putExtra(Settings.SUBSCRIPTION, mSubscription.toString());
        startActivityForResult(intent2, RET_DISPLAY_KEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RET_ENTER_KEY:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String s = data.getStringExtra(Settings.SUBSCRIPTION);
                        if (s != null) {
                            mSubscription = new Subscription(s);
                            Log.d(TAG, mSubscription.toString());
                            displaySubscriptionQRCode();
                        }
                    }
                }
                break;
            default:
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {
                    if (result.getContents() == null) {
                        Toast.makeText(this, getString(R.string.msg_scan_code_cancelled), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, getString(R.string.toast_scan_code_processing), Toast.LENGTH_SHORT).show();
                        String scanned = result.getContents();
                        String[] sa = scanned.split(",");
                        if (sa.length == 2) {
                            mSubscription = Settings.subscribe2VapidKey(MainActivity.this, sa[0], sa[1]);
                            Log.d(TAG, mSubscription.toString());
                            displaySubscriptionQRCode();
                        } else {
                            Toast.makeText(this, getString(R.string.msg_scan_code_wrong), Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }

}
