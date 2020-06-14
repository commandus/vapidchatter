package com.commandus.vapidchatter.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.commandus.vapidchatter.wpn.Config;
import com.commandus.vapidchatter.wpn.Subscription;
import com.commandus.vapidchatter.wpn.SubscriptionPropertiesList;
import com.commandus.vapidchatter.wpn.VapidClient;
import com.commandus.vapidchatter.wpn.wpnAndroid;
import com.google.zxing.integration.android.IntentIntegrator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class Settings {
    public static final String VAPID_PUBLIC_KEY = "publicKey";
    public static final String VAPID_AUTH_SECRET = "authSecret";
    public static final String VAPID_TOKEN = "token";
    public static final String SUBSCRIPTION = "subscription";

    private static final String TAG = Settings.class.getSimpleName();
    private static final String LINK_PREFIX = "https://vapidchatter.commandus.com/code/?";

    private static VapidClient mVapidClient = null;
    private SubscriptionPropertiesList subscriptionPropertiesList;
    private static Settings mInstance = null;
    private final Context context;

    static final String PREFS_NAME = "vapidchatter";
    private static final String PREF_IPV6_LIST = "ipv6";

    public Settings(Context context) {
        this.context = context;
        load();
    }

    public synchronized static VapidClient getVapidClient(Context context) {
        if (mVapidClient == null) {
            mVapidClient = new VapidClient(context);
        }
        return mVapidClient;
    }

    public synchronized static Settings getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Settings(context);
        }
        return mInstance;
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

    public static boolean checkVapidToken(String subscriptionToken) {
        if (subscriptionToken == null) {
            return false;
        }
        return wpnAndroid.checkVapidToken(subscriptionToken);
    }

    /**
     * Get text from the clipboard
     * @param context application context
     * @return clipboard text
     */
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

    public static Subscription subscribe2VapidKey(Context context, String vapidPublicKey, String authSecret) {
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

    public static String getShareLink(String publicKey, String auth) {
        try {
            return LINK_PREFIX
                + Settings.VAPID_PUBLIC_KEY + "=" + URLEncoder.encode(publicKey, "utf-8") + "&"
                + Settings.VAPID_AUTH_SECRET + "=" + URLEncoder.encode(auth, "utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
            return LINK_PREFIX;
        }
    }

    /**
     * Return share link from the VAPID token
     * @param token subscription token
     * @param authSecret auth secret
     * @return share link from the VAPID token
     */
    public static String getShareLinkSubscription(String token, String authSecret) {
        try {
            return LINK_PREFIX
                + Settings.VAPID_TOKEN + "=" + URLEncoder.encode(token, "utf-8") + "&"
                + Settings.VAPID_AUTH_SECRET + "=" + URLEncoder.encode(authSecret, "utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
            return LINK_PREFIX;
        }
    }

    public void save(Config config) {
        // save client's subscriptions
        VapidClient client = Settings.getVapidClient(context);
        String env = client.getEnvDescriptor();
        String js = config.toString();
        wpnAndroid.setConfigJson(env, js);
        // wpnAndroid.saveEnv(env);

        // save IPv6 address list
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (subscriptionPropertiesList != null) {
            editor.putString(PREF_IPV6_LIST, subscriptionPropertiesList.toString());
        }
        editor.apply();
    }

    private void load() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        String js = settings.getString(PREF_IPV6_LIST, "");
        subscriptionPropertiesList = new SubscriptionPropertiesList(js);
    }


}
