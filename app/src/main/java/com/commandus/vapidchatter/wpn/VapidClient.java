package com.commandus.vapidchatter.wpn;

import android.content.Context;
import android.util.Log;

public class VapidClient {
    private static final String TAG = VapidClient.class.getSimpleName();
    // config file name
    private String filename;
    // client descriptor
    private String envDescriptor;
    // not used. Registry descriptor
    private String regDescriptor;

    public VapidClient(Context context) {
        this.filename = context.getFilesDir() + "/wpn.js";
        connect();
    }

    public VapidClient(String filename) {
        this.filename = filename;
        connect();
    }

    public int connect() {
        Log.d(TAG, "connect, config file: " + filename);
        envDescriptor = wpnAndroid.openEnv(filename);
        Log.d(TAG, "env descriptor: " + envDescriptor
                + ", code: " + wpnAndroid.envErrorCode(envDescriptor)
                + ", description: " + wpnAndroid.envErrorDescription(envDescriptor));

        regDescriptor = wpnAndroid.openRegistryClientEnv(envDescriptor);
        Log.d(TAG, "registration client descriptor: " + regDescriptor);

        boolean isRegistered = wpnAndroid.validateRegistration(regDescriptor);
        if (isRegistered) {
            Log.d(TAG, "Registration success");
        } else {
            Log.e(TAG, "Error registration code: " + wpnAndroid.regErrorCode(regDescriptor)
                + ", description: " + wpnAndroid.regErrorDescription(regDescriptor));
        }
        Log.d(TAG, "connected, config file: " + filename);
        return 0;
    }

    public boolean save() {
        String js = wpnAndroid.env2json(envDescriptor);
        Log.d(TAG, "Save config: " + js);
        wpnAndroid.saveEnv(envDescriptor);
        return true;
    }

    public boolean saveAs(String fileName) {
        String js = wpnAndroid.env2json(envDescriptor);
        Log.d(TAG, "SaveAs config: " + js);
        wpnAndroid.saveEnvAs(envDescriptor, fileName);
        return true;
    }

    public boolean save(Config config) {
        setConfig(config);
        return save();
    }

    public void disconnect() {
        Log.d(TAG, "disconnect");
        wpnAndroid.closeRegistryClientEnv(regDescriptor);
        wpnAndroid.saveEnv(envDescriptor);
        wpnAndroid.closeEnv(envDescriptor);
        save(getConfig());
        Log.d(TAG, "disconnected, save config file: " + filename);
    }

    public Config getConfig() {
        String js;
        try {
            js = FileHelper.getStringFromFile(filename);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            js = "";
        }
        return new Config(js);
    }

    public void setConfig(Config config) {
        String js = config.toString();
        wpnAndroid.setConfigJson(envDescriptor, js);
    }

    public String getEnvDescriptor() {
        return envDescriptor;
    }

    public String getRegDescriptor() {
        return regDescriptor;
    }

}
