package com.commandus.vapidchatter;

import android.util.Log;

import com.commandus.vapidchatter.wpn.SubscriptionPropertiesList;
import com.commandus.vapidchatter.wpn.wpnAndroid;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WpnUnitTest {
    private static final String TAG = WpnUnitTest.class.getSimpleName();
    @Test
    public void version() {
    }

    @Test
    public void ipv6listtest() {
        SubscriptionPropertiesList l = new SubscriptionPropertiesList();
        System.out.println("=================================");
        l.put("google dns 1", "2001:4860:4860::8888");
        l.put("google dns 2", "2001:4860:4860::8844");
        l.put("google dns 2", "8.8.8.8");
        System.out.println("list: " + l.toString());
        l.put("google dns 2", "2001:4860:4860::8844");
        System.out.println("list: " + l.toString());
        System.out.println("=================================");
    }
}