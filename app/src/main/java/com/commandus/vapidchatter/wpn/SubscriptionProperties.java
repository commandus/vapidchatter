package com.commandus.vapidchatter.wpn;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SubscriptionProperties {
    private static final String TAG = SubscriptionProperties.class.getSimpleName();
    public String publicKey;
    public String addressIPv6;

    public SubscriptionProperties(String publicKey, String addressIPv6) {
        this.publicKey = publicKey;
        this.addressIPv6 = addressIPv6;
    }

    protected SubscriptionProperties(String json) throws JSONException {
        this(new JSONObject(json));
    }

    protected SubscriptionProperties(JSONObject js) {
        try {
            addressIPv6 = js.getString("publicKey");
            addressIPv6 = js.getString("addressIPv6");
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"publicKey\": \"" + publicKey + '\"' +
                ", \"addressIPv6\": \"" + addressIPv6 + '\"' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        try {
            o.put("publicKey", publicKey);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        try {
            o.put("addressIPv6", addressIPv6);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return o;
    }
}
