package com.commandus.vapidchatter;

import android.content.Context;
import android.util.Log;

import com.commandus.utilipv6.Address;
import com.commandus.vapidchatter.activity.Settings;
import com.commandus.vapidchatter.activity.SubscriptionsAdapter;
import com.commandus.vapidchatter.wpn.Config;
import com.commandus.vapidchatter.wpn.Subscription;
import com.commandus.vapidchatter.wpn.Subscriptions;
import com.commandus.vapidchatter.wpn.VapidClient;
import com.commandus.vapidchatter.wpn.wpnAndroid;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WpnInstrumentedTest {
    private static final String TAG = WpnInstrumentedTest.class.getSimpleName();
    private void print(String s) {
        Log.i(TAG, s);
        // System.out.println(s);
    }

    @Test
    public void checkRegistration() {
        // Context of the app under test.
        print("=================================");
        print("checkRegistration");
        
        print("Version: " + wpnAndroid.version());
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String filename = appContext.getFilesDir() + "/wpn.js";
        print("config file: " + filename);

        String env = wpnAndroid.openEnv(filename);
        print("env descriptor: " + env);
        print("env code: " + wpnAndroid.envErrorCode(env));
        print("env error description: " + wpnAndroid.envErrorDescription(env));
        String js = wpnAndroid.env2json(env);
        print("config: " + js);

        String reg = wpnAndroid.openRegistryClientEnv(env);
        print("registration client descriptor: " + reg);

        boolean isRegistered = wpnAndroid.validateRegistration(reg);
        print("isRegistered: " + isRegistered);
        if (isRegistered) {
            print("Registration success");
            print("registration client code: " + wpnAndroid.regErrorCode(reg));
            print("registration client error description: " + wpnAndroid.regErrorDescription(reg));
        } else {
            print("Error registration");
            print("registration client code: " + wpnAndroid.regErrorCode(reg));
            print("registration client error description: " + wpnAndroid.regErrorDescription(reg));
        }

        print("registration done------");

        js = wpnAndroid.env2json(env);
        print("config: " + js);
        wpnAndroid.closeRegistryClientEnv(reg);

        wpnAndroid.saveEnv(env);
        wpnAndroid.closeEnv(env);
        print("=================================");
    }

    @Test
    public void checkClientFromContext() {
        print("=================================");
        print("checkClientFromContext");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        VapidClient c = new VapidClient(appContext);
        print(c.getConfig().toString());
        print("=================================");
    }

    @Test
    public void checkClientFromFile() {
        print("=================================");
        print("checkClientFromFile");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String filename = appContext.getFilesDir() + "/wpn.js";
        print("config file: " + filename);
        VapidClient c = new VapidClient(filename);
        print(c.getConfig().toString());
        print("=================================");
    }


    @Test
    public void checkIP6GlobalAddress() {
        print("=================================");
        String g = Address.getIPv6GlobalAddress();
        print("IP6GlobalAddress: " + g);
        print("=================================");
    }

    @Test
    public void checkIPAddress() {
        print("=================================");
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (intf.isLoopback()) {
                    continue;
                }
                print("Interface " + intf.getDisplayName() + " " + intf.getName()
                        + " " + intf.toString());
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    print("IP " + addr.toString());
                    if (!addr.isLoopbackAddress()) {
                        String h = addr.getHostAddress();
                        if (addr instanceof Inet4Address) {
                            print("IP v4 " + h);
                        }
                        if (addr instanceof Inet6Address) {
                            Inet6Address a = (Inet6Address) addr;
                            if (a.isSiteLocalAddress() || a.isLinkLocalAddress()) {
                                print("Local IP v6 " + h);
                                int delim = h.indexOf('%'); // drop ip6 zone suffix
                                String hh = delim < 0 ? h : h.substring(0, delim);
                                print("Local IP v6 " + hh);
                            } else {
                                print("Global IP v6 " + h);
                                int delim = h.indexOf('%'); // drop ip6 zone suffix
                                String hh = delim < 0 ? h : h.substring(0, delim);
                                print("Global IP v6 " + hh);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            print(e.toString());
        }
        print("=================================");
    }

    @Test
    public void checkSubscriptionsAdapter() {
        print("=================================");
        print("checkSubscriptionsAdapter");
        
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        VapidClient client = Settings.getVapidClient(context);
        Config config = client.getConfig();
        print("Subscriptions (0): " + config.subscriptions.toString());
        for (int i = 0; i < 10; i++) {
            String id = String.valueOf(i);
            String name = "Subscription " + id;
            String endpoint = "";
            String publicKey = "";
            String authSecret = "";
            Subscription subscription = new Subscription(
                id, name, endpoint, publicKey, authSecret
            );
            config.subscriptions.subscriptions.add(subscription);
        }
        print("Subscriptions(1): " + config.subscriptions.toString());
        config.subscriptions.subscriptions.clear();
        Settings.getInstance(context).save(config);
        print("=================================");
    }

}
