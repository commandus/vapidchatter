package com.commandus.vapidchatter.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import com.commandus.vapidchatter.wpn.Subscription;
import com.commandus.vapidchatter.wpn.VapidClient;
import com.commandus.vapidchatter.wpn.wpnAndroid;
import com.google.zxing.integration.android.IntentIntegrator;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class Settings {
    public static final String VAPID_PUBLIC_KEY = "vapidPublicKey";
    public static final String VAPID_AUTH_SECRET = "authSecret";
    public static final String SUBSCRIPTION = "subscription";
    public static final String VAPID_TOKEN = "token";

    private static final String TAG = Settings.class.getSimpleName();

    private static VapidClient mVapidClient = null;

    public synchronized static VapidClient getVapidClient(Context context) {
        if (mVapidClient == null) {
            mVapidClient = new VapidClient(context);
        }
        return mVapidClient;
    }

    public static boolean checkVapidPublicKey(String vapidPublicKey) {
        if (vapidPublicKey == null) {
            return false;
        }
        return wpnAndroid.checkVapidPublicKey(vapidPublicKey);
    }

    public static boolean checkVapidAuthSecret(String authSecret) {
        if (authSecret == null) {
            return false;
        }
        return wpnAndroid.checkVapidAuthSecret(authSecret);
    }

    public static String getClipboardText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = "";
        if (clipboard != null && clipboard.hasPrimaryClip()) {
            try {
                ClipDescription clipDescription = clipboard.getPrimaryClipDescription();
                if (clipDescription != null && clipDescription.hasMimeType(MIMETYPE_TEXT_PLAIN)) {
                    ClipData clip = clipboard.getPrimaryClip();
                    if (clip != null) {
                        ClipData.Item item = clip.getItemAt(0);
                        pasteData = item.getText().toString();
                    }
                }
            } catch (NullPointerException e) {
                Log.e(TAG, e.toString());
            }
        }
        return pasteData;
    }

    public static Subscription subscribe2VapidKey(
            Context context, String vapidPublicKey, String authSecret) {
        String env = Settings.getVapidClient(context).getEnvDescriptor();
        return wpnAndroid.subscribe2VapidPublicKey(env, vapidPublicKey, authSecret);
    }

    public static void startScanCode(Activity context, String prompt) {
        IntentIntegrator integrator = new IntentIntegrator(context);
        integrator.setOrientationLocked(false);
        integrator.setPrompt(prompt);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();
    }

}
