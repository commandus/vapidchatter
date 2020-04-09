package com.commandus.vapidchatter.wpn;

import android.content.Context;
import android.util.Log;

public class VapidClient {
    private static final String TAG = VapidClient.class.getSimpleName();
    public Config config;
    private String filename;
    private String envDescriptor;
    private String regDescriptor;

    public VapidClient(Context context) {
        this.filename = context.getFilesDir() + "/wpn.js";
        loadConfig();
    }

    public VapidClient(String filename) {
        this.filename = filename;
        loadConfig();
    }

    public int connect() {
        Log.i(TAG, "connect, config file: " + filename);
        envDescriptor = wpnAndroid.openEnv(filename);
        Log.i(TAG, "env descriptor: " + envDescriptor
                + ", code: " + wpnAndroid.envErrorCode(envDescriptor)
                + ", description: " + wpnAndroid.envErrorDescription(envDescriptor));

        regDescriptor = wpnAndroid.openRegistryClientEnv(envDescriptor);
        Log.i(TAG, "registration client descriptor: " + regDescriptor);

        boolean isRegistered = wpnAndroid.validateRegistration(regDescriptor);
        if (isRegistered) {
            Log.i(TAG, "Registration success");
        } else {
            Log.e(TAG, "Error registration code: " + wpnAndroid.regErrorCode(regDescriptor)
                + ", description: " + wpnAndroid.regErrorDescription(regDescriptor));
        }
        save();
        Log.i(TAG, "connected, save config file: " + filename);
        return 0;
    }

    public void save() {
        String js = wpnAndroid.env2json(envDescriptor);
        this.config = new Config(js);
        Log.i(TAG, "config: " + js);

        wpnAndroid.saveEnv(envDescriptor);
    }

    public void disconnect() {
        Log.i(TAG, "disconnect");
        wpnAndroid.closeRegistryClientEnv(regDescriptor);
        wpnAndroid.saveEnv(envDescriptor);
        wpnAndroid.closeEnv(envDescriptor);
        save();
        Log.i(TAG, "disconnected, save config file: " + filename);
    }

    private void loadConfig() {
        String js;
        try {
            js = FileHelper.getStringFromFile(filename);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            js = "";
        }
        this.config = new Config(js);
    }
}
