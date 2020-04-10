#include <jni.h>
#include <string>
#include <sstream>
#include "vapid-api.h"

static inline void* descriptorJ2C(const std::string &value)
{
    long long int r;
    std::istringstream(value) >> std::hex >> r;
    return (void *) r;
}

static inline std::string descriptorC2J(void* value)
{
    std::stringstream ss;
    ss << std::hex << (long long int) value;
    return ss.str();
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_version(
    JNIEnv* env,
    jobject /* this */) {
    std::string r = "wpn-android v.1.0";
    return env->NewStringUTF(r.c_str());
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_checkVapidPublicKey
        (
                JNIEnv* env,
                jobject  __unused thisObject,
                jstring vapidPublicKey
        )
{
    std::string v(env->GetStringUTFChars(vapidPublicKey, NULL));
    return static_cast<jboolean> (isPublicKeyValid(v));
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_checkVapidPrivateKey
        (
                JNIEnv* env,
                jobject  __unused thisObject,
                jstring vapidPrivateKey
        )
{
    std::string v(env->GetStringUTFChars(vapidPrivateKey, NULL));
    return static_cast<jboolean> (isPrivateKeyValid(v));
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_checkVapidAuthSecretKey
        (
                JNIEnv* env,
                jobject  __unused thisObject,
                jstring vapidAuthSecret
        )
{
    std::string v(env->GetStringUTFChars(vapidAuthSecret, NULL));
    return static_cast<jboolean> (isAuthSecretValid(v));
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_openEnv
(
    JNIEnv* env,
    jobject  __unused thisObject,
    jstring fileName
)
{
    std::string fn(env->GetStringUTFChars(fileName, NULL));
    void *retenv = openEnv(fn);
    return env->NewStringUTF(descriptorC2J(retenv).c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_closeEnv
(
    JNIEnv* env,
    jobject __unused thisObject,
    jstring descriptor
)
{
    closeEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT void JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_saveEnv
(
    JNIEnv* env,
    jobject __unused thisObject,
    jstring descriptor
)
{
    saveEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT jint JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_envErrorCode
(
        JNIEnv* env,
        jobject __unused thisObject,
        jstring descriptor
)
{
    return envErrorCode(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_envErrorDescription
(
        JNIEnv* env,
        jobject __unused thisObject,
        jstring descriptor
)
{
    std::string r = envErrorDescription(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
    return env->NewStringUTF(r.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_env2json
(
    JNIEnv* env,
    jobject __unused thisObject,
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
        jobject __unused thisObject,
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
        jobject __unused thisObject,
        jstring descriptor
)
{
    closeRegistryClientEnv(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT jint JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_regErrorCode
(
        JNIEnv* env,
        jobject __unused thisObject,
        jstring descriptor
)
{
    return regErrorCode(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_regErrorDescription
(
        JNIEnv* env,
        jobject __unused thisObject,
        jstring descriptor
)
{
    std::string r = regErrorDescription(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)));
    return env->NewStringUTF(r.c_str());
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_validateRegistration
(
        JNIEnv* env,
        jobject __unused thisObject,
        jstring descriptor
)
{
    return static_cast<jboolean>(validateRegistration(
            descriptorJ2C(env->GetStringUTFChars(descriptor, NULL))));
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_subscribe2VapidPublicKeyJson
(
        JNIEnv* env,
        jobject __unused thisObject,
        jstring descriptor,
        jstring key
)
{
    std::string jk(env->GetStringUTFChars(key, NULL));
    std::string r;
    if (!subscribe2VapidPublicKey(r,
            descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)), jk)) {
        return NULL;
    }
    return env->NewStringUTF(r.c_str());
}
