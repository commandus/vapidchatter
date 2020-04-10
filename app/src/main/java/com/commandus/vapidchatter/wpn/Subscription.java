package com.commandus.vapidchatter.wpn;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * {
 *  "subscribeMode": 2,
 *  "name": "www.itctrack.ru",
 *  "endpoint": "https://fcm.googleapis.com/wp/feYyPOQOMDY:APA91bFka9i0KPYz7UM8CNYiJ3AtZnhjdasPlGoXb6FbqU0rcIbGeon2fThkRfB2KYQLlQ6ed6MVLwcT4L1-ROcA41bC38DgncBpFd24Cv4DeHMK3VURnx4_3ADPijAdgEJ1VwPZVol_",
 *  "publicKey": "BMWbr4dF-V8-fdxch8ZaWrGMgvnF_gJ4sQAGJ4ByUKs7hDQmaixBuJkKvoXi6RYYL2DtOtU7Ktig2-IfowSsb4A",
 *  "id": 0
 */
public class Subscription {
    public String id;
    public String name;
    public String endpoint;
    public String publicKey;

    public Subscription(JSONObject value) {
        parse(value);
    }

    public Subscription(String value) {
        try {
            JSONObject v = new JSONObject(value);
            parse(v);
        } catch (JSONException e) {
            reset();
        }
    }

    public Subscription() {
        reset();
    }

    private void reset() {
        id = "0";
        name = "";
        endpoint = "";
        publicKey = "";
    }

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id
                + ", \"name\": \"" + name
                + "\", \"endpoint\": \"" + endpoint
                + "\", \"publicKey\": \"" + publicKey + "\"}";
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
        if (value.has("name")) {
            try {
                name = value.getString("name");
            } catch (JSONException e) {
                name = "";
            }
        } else {
            name = "";
        }

        if (value.has("endpoint")) {
            try {
                endpoint = value.getString("endpoint");
            } catch (JSONException e) {
                endpoint = "";
            }
        } else {
            endpoint = "";
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
    }

}
