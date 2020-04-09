package com.commandus.vapidchatter.wpn;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class Config {
    public Credentials credentials;
    public Keys keys;
    public Subscriptions subscriptions;

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return "\"credentials\": " + credentials.toString()
                + ", \"keys\": " + keys.toString()
                + ", \"subscriptions\": \"" + subscriptions.toString() + "}";
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
