#include <jni.h>
#include <string>
#include <sstream>
#include "vapid-api.h"

static inline void* descriptorJ2C(const std::string &value)
{
    long long int r;
    std::istringstream(value) >> r;
    return (void *) r;
}

static inline std::string descriptorC2J(void* value)
{
    std::stringstream ss;
    ss << (long long int) value;
    return ss.str();
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_version(
    JNIEnv* env,
    jobject /* this */) {
    std::string r = "wpn-android v.1.0";
    return env->NewStringUTF(r.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_openEnv
(
    JNIEnv* env,
    jobject thisObject,
    jstring fileName
)
{
    std::string proto_path(env->GetStringUTFChars(fileName, NULL));
    std::string fn(env->GetStringUTFChars(fileName, NULL));
    void *retenv = openEnv(fn);
    return env->NewStringUTF(descriptorC2J(retenv).c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_closeEnv
(
    JNIEnv* env,
    jobject thisObject,
    jstring descriptor
)
{
    closeEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT void JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_saveEnv
(
    JNIEnv* env,
    jobject thisObject,
    jstring descriptor
)
{
    saveEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_env2json
(
    JNIEnv* env,
    jobject thisObject,
    jstring descriptor
)
{
    std::string r = env2json(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
    return env->NewStringUTF(r.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_openRegistryClientEnv
        (
                JNIEnv* env,
                jobject thisObject,
                jstring descriptor
        )
{
    void *r = openRegistryClientEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
    return env->NewStringUTF(descriptorC2J(r).c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_closeRegistryClientEnv
        (
                JNIEnv* env,
                jobject thisObject,
                jstring descriptor
        )
{
    closeRegistryClientEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_validateRegistration
        (
                JNIEnv* env,
                jobject thisObject,
                jstring descriptor
        )
{
    return validateRegistration(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}
