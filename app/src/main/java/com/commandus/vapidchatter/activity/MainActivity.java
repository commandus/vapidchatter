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
import com.commandus.vapidchatter.wpn.*;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private static final int RET_ENTER_KEY = 1;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(wpnAndroid.version());
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
            case R.id.action_scan_vapid_key:
                Settings.startScanCode(this, getString(R.string.prompt_scan_vapid_key));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RET_ENTER_KEY:
                if (resultCode == 0) {
                    if (data != null) {
                        String s = data.getStringExtra(Settings.SUBSCRIPTION);
                        if (s != null) {
                            Subscription sub = new Subscription(s);
                            Log.d(TAG, sub.toString());
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
                        Toast.makeText(this, getString(R.string.prompt_scan_code_processing), Toast.LENGTH_SHORT).show();
                        String scanned = result.getContents();
                        Subscription sub = Settings.subscribe2VapidKey(MainActivity.this, scanned);
                        Log.d(TAG, sub.toString());
                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }

}
