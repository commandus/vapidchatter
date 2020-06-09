package com.commandus.vapidchatter.wpn;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SubscriptionPropertiesList {
    private static final String TAG = SubscriptionPropertiesList.class.getSimpleName();
    public HashMap<String, SubscriptionProperties> values;

    public SubscriptionPropertiesList() {
        values = new HashMap<>();
    }

    public SubscriptionPropertiesList(String js) {
        try {
            JSONArray a = new JSONArray(js);
            for (int i = 0; i < a.length(); i++) {
                JSONObject p = a.getJSONObject(i);
                if (p != null) {
                    SubscriptionProperties v = new SubscriptionProperties(p);
                    this.values.put(v.publicKey, v);
                }
            }

        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (SubscriptionProperties value : values.values()) {
            sb.append(prefix);
            sb.append(value.toString());
            prefix = ", ";
        }
        return sb.toString();
    }

    public void clear() {
        values.clear();
    }

    public void put(String key, String ipv6Address) {
        values.put(key, new SubscriptionProperties(key, ipv6Address));
    }
}
