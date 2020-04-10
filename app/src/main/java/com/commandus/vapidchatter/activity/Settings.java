package com.commandus.vapidchatter.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import com.commandus.vapidchatter.wpn.wpnAndroid;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class Settings {

    public static final String VAPID_PUBLIC_KEY = "vapidPublicKey";
    private static final String TAG = Settings.class.getSimpleName();

    public static boolean checkVapidPublicKey(String vapidPublicKey) {
        if (vapidPublicKey == null) {
            return false;
        }
        return wpnAndroid.checkVapidPublicKey(vapidPublicKey);
    }

    public static String getClipboardText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = "";
        if (clipboard != null && clipboard.hasPrimaryClip()) {
            try {
                if (clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN)) {
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                    pasteData = item.getText().toString();
                }
            } catch (NullPointerException e) {
                Log.e(TAG, e.toString());
            }
        }
        return pasteData;
    }
}
