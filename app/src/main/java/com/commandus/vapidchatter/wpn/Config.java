package com.commandus.vapidchatter.wpn;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class Config {
    private static final String TAG = Config.class.getSimpleName();
    public Credentials credentials;
    public Keys keys;
    public Subscriptions subscriptions;

    public Config(String json) {
        try {
            JSONObject js = new JSONObject(json);
            parse(js);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return "{\"credentials\": " + credentials.toString()
                + ", \"keys\": " + keys.toString()
                + ", \"subscriptions\": [" + subscriptions.toString() + "]}";
    }

    public void parse(JSONObject value) {
        if (value.has("credentials")) {
            try {
                credentials = new Credentials(value.getJSONObject("credentials"));
            } catch (JSONException e) {
                credentials = new Credentials();
            }
        } else {
            credentials = new Credentials();
        }
        if (value.has("keys")) {
            try {
                keys = new Keys(value.getJSONObject("keys"));
            } catch (JSONException e) {
                keys = new Keys();
            }
        } else {
            keys = new Keys();
        }
        if (value.has("subscriptions")) {
            try {
                subscriptions = new Subscriptions(value.getJSONArray("subscriptions"));
            } catch (JSONException e) {
                subscriptions = new Subscriptions();
            }
        } else {
            subscriptions = new Subscriptions();
        }
    }

}
