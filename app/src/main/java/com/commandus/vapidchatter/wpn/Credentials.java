package com.commandus.vapidchatter.wpn;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  {
 *     "appId": "cc0AH-Yx6VQ",
 *     "androidId": 4857243934503540620,
 *     "securityToken": 6811236943488200321,
 *     "GCMToken": "fm9j5ImA6sg:APA91bENRCP6gPej9ENDFMcq6xsgzKAI1OuQ4rpae_jWRSrRJ4Zp6mR-DW_qDSqHNKMhEwmYeFI6DCJl_bUtcpVEIbloBS3dj0N2rwrw2MJiLACJvzjTpjD17yWd-VRhImiIsEWLK1FT"
 *  }
 */
public class Credentials {
    public String appId;
    public String androidId;
    public String securityToken;
    public String GCMToken;

    public Credentials(JSONObject value) {
        parse(value);
    }

    public Credentials() {
        reset();
    }

    private void reset() {
        appId = "0";
        androidId = "0";
        securityToken = "";
        GCMToken = "";
    }

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return "{"
            + "\"appId\": " + appId
            + ", \"androidId\": " + androidId
            + ", \"securityToken\": " + securityToken
            + ", \"GCMToken\": \"" + GCMToken + "\"}";
    }

    public void parse(JSONObject value) {
        if (value.has("appId")) {
            try {
                appId = value.getString("appId");
            } catch (JSONException e) {
                appId = "";
            }
        } else {
            appId = "";
        }

        if (value.has("androidId")) {
            try {
                androidId = value.getString("androidId");
            } catch (JSONException e) {
                androidId = "";
            }
        } else {
            androidId = "";
        }

        if (value.has("GCMToken")) {
            try {
                GCMToken = value.getString("GCMToken");
            } catch (JSONException e) {
                GCMToken = "";
            }
        } else {
            GCMToken = "";
        }
    }

}
