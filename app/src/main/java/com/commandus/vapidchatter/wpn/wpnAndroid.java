package com.commandus.vapidchatter.wpn;

public class wpnAndroid {
    // Used to load the 'wpn-lib' library on application startup.
    static {
        System.loadLibrary("wpn-lib");
    }

    public static native String version();
    public static native String openEnv(String filename);

    public static native void closeEnv(String descriptor);
    public static native void saveEnv(String descriptor);
    public static native String env2json(String descriptor);
    public static native int envErrorCode(String descriptor);
    public static native String envErrorDescription(String descriptor);

    public static native String openRegistryClientEnv(String envDescriptor);
    public static native void closeRegistryClientEnv(String descriptor);
    public static native int regErrorCode(String descriptor);
    public static native String regErrorDescription(String descriptor);
    public static native boolean validateRegistration(String descriptor);

}
