package com.commandus.vapidchatter.wpn;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * {"id": 0,
 *  "secret": 0,
 *  "privateKey": "g1ImXp1o9k7RGZMta6l_cbeCfVbGSk85yq0VSEMnMH4",
 *  "publicKey": "BFTCdlst8WLypLdtKmRHaKR6IFN-Jya6wlqXgHcihEzS3jkS9SGvyeEnATJO62neO0yojvXBdasuafWuAm86mYU",
 *  "authSecret": "6C9pHqYAI3z4tZEjWHnhDA"
 *  }
 */
public class Keys {
    public String id;
    public String secret;
    public String privateKey;
    public String publicKey;
    public String authSecret;

    public Keys(JSONObject value) {
        parse(value);
    }

    public Keys() {
        reset();
    }

    private void reset() {
        id = "0";
        secret = "0";
        privateKey = "";
        publicKey = "";
        authSecret = "";
    }

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id
                + ", \"secret\": " + secret
                + ", \"privateKey\": \"" + privateKey
                + "\", \"publicKey\": \"" + publicKey
                + "\", \"authSecret\": \"" + authSecret + "\"}";
    }

    public void parse(JSONObject value) {
        if (value.has("id")) {
            try {
                id = value.getString("id");
            } catch (JSONException e) {
                id = "";
            }
        } else {
            id = "";
        }
        if (value.has("secret")) {
            try {
                secret = value.getString("secret");
            } catch (JSONException e) {
                secret = "";
            }
        } else {
            secret = "";
        }

        if (value.has("privateKey")) {
            try {
                privateKey = value.getString("privateKey");
            } catch (JSONException e) {
                privateKey = "";
            }
        } else {
            privateKey = "";
        }

        if (value.has("publicKey")) {
            try {
                publicKey = value.getString("publicKey");
            } catch (JSONException e) {
                publicKey = "";
            }
        } else {
            publicKey = "";
        }

        if (value.has("authSecret")) {
            try {
                authSecret = value.getString("authSecret");
            } catch (JSONException e) {
                authSecret = "";
            }
        } else {
            authSecret = "";
        }
    }

}

