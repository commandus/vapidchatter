package com.commandus.vapidchatter.wpn;

public class wpnAndroid {
    // Used to load the 'wpn-lib' library on application startup.
    static {
        System.loadLibrary("wpn-lib");
    }

    public static native String version();
    public static native boolean checkVapidPublicKey(String vapidPublicKey);
    public static native boolean checkVapidAuthSecret(String authSecret);
    public static native boolean checkVapidPrivateKey(String vapidPrivateKey);
    public static native boolean checkVapidToken(String subscriptionToken);

    public static native String openEnv(String filename);
    public static native void closeEnv(String descriptor);

    /**
     * Save subscriptions
     * @param descriptor environment descriptor
     * @return true
     */
    public static native void saveEnv(String descriptor);
    public static native void saveEnvAs(String descriptor, String fileName);
    public static native String env2json(String descriptor);
    public static native void setConfigJson(String descriptor, String json);
    public static native int envErrorCode(String descriptor);
    public static native String envErrorDescription(String descriptor);

    public static native String openRegistryClientEnv(String envDescriptor);
    public static native void closeRegistryClientEnv(String descriptor);
    public static native int regErrorCode(String descriptor);
    public static native String regErrorDescription(String descriptor);
    public static native boolean validateRegistration(String descriptor);

    public static Subscription subscribe2VapidPublicKey(String env, String key, String authSecret) {
        String js = subscribe2VapidPublicKeyJson(env, key, authSecret);
        if (js == null) {
            return null;
        }
        return new Subscription(js);
    }

    private static native String subscribe2VapidPublicKeyJson(String env, String key, String authSecret);

}
