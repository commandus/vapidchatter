package com.commandus.vapidchatter;

import android.content.Context;
import android.util.Log;

import com.commandus.vapidchatter.wpn.VapidClient;
import com.commandus.vapidchatter.wpn.wpnAndroid;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WpnInstrumentedTest {
    private static final String TAG = WpnInstrumentedTest.class.getSimpleName();

    @Test
    public void check01() {
        // Context of the app under test.
        Log.i(TAG, "=================================");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String filename = appContext.getFilesDir() + "/wpn.js";
        Log.i(TAG, "config file: " + filename);

        String env = wpnAndroid.openEnv(filename);
        Log.i(TAG, "env descriptor: " + env);
        Log.i(TAG, "env code: " + wpnAndroid.envErrorCode(env));
        Log.i(TAG, "env error description: " + wpnAndroid.envErrorDescription(env));
        String js = wpnAndroid.env2json(env);
        Log.i(TAG, "config: " + js);

        String reg = wpnAndroid.openRegistryClientEnv(env);
        Log.i(TAG, "registration client descriptor: " + reg);

        boolean isRegistered = wpnAndroid.validateRegistration(reg);
        Log.i(TAG, "isRegistered: " + isRegistered);
        if (isRegistered) {
            Log.i(TAG, "Registration success");
            Log.i(TAG, "registration client code: " + wpnAndroid.regErrorCode(reg));
            Log.i(TAG, "registration client error description: " + wpnAndroid.regErrorDescription(reg));
        } else {
            Log.e(TAG, "Error registration");
            Log.e(TAG, "registration client code: " + wpnAndroid.regErrorCode(reg));
            Log.e(TAG, "registration client error description: " + wpnAndroid.regErrorDescription(reg));
        }

        Log.i(TAG, "registration done------");

        js = wpnAndroid.env2json(env);
        Log.i(TAG, "config: " + js);
        wpnAndroid.closeRegistryClientEnv(reg);

        wpnAndroid.saveEnv(env);
        wpnAndroid.closeEnv(env);
        Log.i(TAG, "=================================");
    }

    @Test
    public void check02() {
        Log.i(TAG, "=================================");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        VapidClient c = new VapidClient(appContext);
        Log.i(TAG, c.config.toString());
        Log.i(TAG, "=================================");
    }

    @Test
    public void check03() {
        Log.i(TAG, "=================================");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String filename = appContext.getFilesDir() + "/wpn.js";
        Log.i(TAG, "config file: " + filename);
        VapidClient c = new VapidClient(filename);
        Log.i(TAG, c.config.toString());
        Log.i(TAG, "=================================");
    }

}
