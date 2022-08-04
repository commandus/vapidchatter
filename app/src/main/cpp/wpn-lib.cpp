/**
 * Important notes
 *
 * Terms
 *
 * WPN subscription is an set of public keys, etc to establish "WPN connection".
 * WPN environment is a set of WPN client configuration, settings and subscriptions.
 * WPN environment serialized as JSON string and stored in "wpn.json" file.
 */
#include <jni.h>
#include <string>
#include <sstream>
#include "vapid-api.h"
#include "mcs/mcsclient.h"

/**
 * Helper function.
 * Java WPN environment descriptor is a hex string
 * @param value Java WPN environment  descriptor
 * @return pointer to the WPN environment 
 */
static inline void* descriptorJ2C(const std::string &value)
{
    long long int r;
    std::istringstream(value) >> std::hex >> r;
    return (void *) r;
}

/**
 * Helper function.
 * WPN environment descriptor is pointer to the WPN environment 
 * @param value  pointer to the WPN environment 
 * @return Java WPN environment  descriptor
 */
static inline std::string descriptorC2J(void* value)
{
    std::stringstream ss;
    ss << std::hex << (long long int) value;
    return ss.str();
}

/**
 * Return WPN environment version string.
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * tv.setText(wpnAndroid.version());
 */
extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_version(
    JNIEnv* env,
    jobject /* this */
)
{
    std::string r = "wpn-android v.1.0";
    return env->NewStringUTF(r.c_str());
}

/**
 * Check is provided parameter is valid public key string
 * @param vapidPublicKey public key string to verify
 * @return true if success
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.checkVapidPublicKey(vapidPublicKey);
 */
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

/**
 * Check is provided parameter is valid private key string
 * @param vapidPrivateKey private key string to verify
 * @return true if success
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.checkVapidPrivateKey(vapidPrivateKey);
 */
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

/**
 * Check is provided parameter is valid AuthSecret string
 * @param vapidAuthSecret AuthSecret string to verify
 * @return true if success
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.checkVapidAuthSecret(AuthSecret);
 */
extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_checkVapidAuthSecret
(
    JNIEnv* env,
    jobject  __unused thisObject,
    jstring vapidAuthSecret
)
{
    std::string v(env->GetStringUTFChars(vapidAuthSecret, NULL));
    return static_cast<jboolean> (isAuthSecretValid(v));
}

/**
 * Check is provided parameter is valid VapidToken string
 * @param vapidToken VapidToken string to verify
 * @return true if success
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.checkVapidToken(vapidToken);
 */
extern "C" JNIEXPORT jboolean JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_checkVapidToken
(
    JNIEnv* env,
    jobject  __unused thisObject,
    jstring vapidToken
)
{
    std::string v(env->GetStringUTFChars(vapidToken, NULL));
    return static_cast<jboolean> (isTokenValid(v));
}

/**
 * Open config file in JSON format, read  and start WPN environment .
 * After user modify subscriptions ot made any changes, call
 * wpnAndroid.saveEnv() to store changes in the file.
 * @param fileName file name
 * @return WPN environment  Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.openEnv(fileName);
 */
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

/**
 * Stop WPN environment 
 * @param descriptor WPN environment  Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.closeEnv(descriptor);
 */
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

/**
 * Save WPN environment  config in associated JSON file.
 * You need call wpnAndroid.saveEnv(descriptor) to
 * keep changes in the JSON file so after re-start client
 * can read subscriptions from the file.
 * @param descriptor WPN environment  Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.saveEnv(descriptor);
 */
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

/**
 * Save WPN environment config in specified JSON file.
 * You need call wpnAndroid.saveEnv(descriptor, newFileName) to
 * save JSON file so client can have more than one identifiers.
 * @param descriptor WPN environment  Java descriptor
 * @param filename new file name
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.saveEnv(descriptor, newFileName);
 */
extern "C" JNIEXPORT void JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_saveEnvAs
(
    JNIEnv* env,
    jobject __unused thisObject,
    jstring descriptor,
    jstring fileName
)
{
    saveEnvAs(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)), env->GetStringUTFChars(fileName, NULL));
}

/**
 * Return last WPN environment  error code.
 * 0- no error.
 * @param descriptor WPN environment  Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * int code = wpnAndroid.envErrorCode(descriptor);
 */
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

/**
 * Set WPN environment  config from the JSON string
 * @param descriptor WPN environment  Java descriptor
 * @param json JSON string
 * @reurn 0
 * @see wpnAndroid.env3json(descriptor)
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * int code = wpnAndroid.setConfigJson(descriptor, json);
 */
extern "C" JNIEXPORT jint JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_setConfigJson
(
    JNIEnv* env,
    jobject __unused thisObject,
    jstring descriptor,
    jstring json
)
{
    std::string v(env->GetStringUTFChars(json, NULL));
    setConfigJson(descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)), v);
    return 0;
}

/**
 * Get last WPN environment  error description in EN locale.
 * Return empty string if no error occurred.
 * @param descriptor WPN environment  Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * String errDesc = wpnAndroid.envErrorDescription(descriptor);
 */
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

/**
 * Get WPN environment  config as JSON string.
 * @param descriptor WPN environment  Java descriptor
 * @see wpnAndroid.setConfigJson(descriptor)
 * @return WPN environment  config as JSON string
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * String errDesc = wpnAndroid.env3json(descriptor);
 */
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

/**
 * Open registry WPN environment  config as JSON string.
 * @param descriptor WPN environment  Java descriptor
 * @return WPN environment  descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * String registryDescriptor = wpnAndroid.openRegistryClientEnv(descriptor);
 */
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

/**
 * Close registry WPN environment  config as JSON string.
 * @param descriptor WPN environment  Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * wpnAndroid.closeRegistryClientEnv(registryDescriptor);
 */
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

/**
 * Return last WPN registry error code.
 * 0- no error.
 * @param descriptor WPN registry Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * int code = wpnAndroid.regErrorCode(regDescriptor);
 */
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

/**
 * Get last WPN registry error description in EN locale.
 * Return empty string if no error occurred.
 * @param descriptor WPN registry Java descriptor
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * String errDesc = wpnAndroid.regErrorDescription(registryDescriptor);
 */
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

/**
 * Validate does client registered in the WPN registry
 * Return true if it does.
 * @param descriptor WPN registry Java descriptor
 * @return true- client is registered
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * boolean regisered = wpnAndroid.validateRegistration(registryDescriptor);
 */
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

/**
 * Subscribe to another client using public key and authSecret.
 * Saves WPN environment if subscription success.
 * Return not NULL if subscribes successfully
 * @param descriptor WPN registry Java descriptor
 * @param key public key to subscribe to
 * @param authSecret secret string
 * @return subscription in JSON format if success, NULL if subscribe failed
 * Usage:
 * import com.commandus.vapidchatter.wpn.*;
 * boolean regisered = wpnAndroid.subscribe2VapidPublicKeyJson(registryDescriptor);
 */
extern "C" JNIEXPORT jstring JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_subscribe2VapidPublicKeyJson
(
    JNIEnv* env,
    jobject __unused thisObject,
    jstring descriptor,
    jstring key,
    jstring authSecret
)
{
    std::string jk(env->GetStringUTFChars(key, NULL));
    std::string jAuthSecret(env->GetStringUTFChars(authSecret, NULL));
    std::string r;
    if (!subscribe2VapidPublicKey(r, descriptorJ2C(env->GetStringUTFChars(descriptor, NULL)), jk, jAuthSecret)) {
        return NULL;
    }
    return env->NewStringUTF(r.c_str());
}

static void internalOnNotify(
    void *env,
    const char *persistent_id,
    const char *from,				///< e.g. BDOU99-h67HcA6JeFXHbSNMu7e2yNNu3RzoMj8TM4W88jITfq7ZmPvIM1Iv-4_l2LxQcYwhqby2xGpWwzjfAnG4
    const char *appName,
    const char *appId,
    int64_t sent,
    const NotifyMessageC *request
) {

}

/**
 * Start client
 * @param descriptor WPN registry Java descriptor
 */
extern "C" JNIEXPORT void * JNICALL
Java_com_commandus_vapidchatter_wpn_wpnAndroid_startClient
(
    JNIEnv* env,
    jobject __unused thisObject,
    jstring descriptor
)
{
    if (!env)
        return nullptr;
    ConfigFile *config = (ConfigFile *) descriptorJ2C(env->GetStringUTFChars(descriptor, NULL));
    MCSClient *client = new MCSClient(
            config->subscriptions,
            config->wpnKeys->getPrivateKey(),
            config->wpnKeys->getAuthSecret(),
            config->wpnKeys->id,
            config->wpnKeys->secret,
            internalOnNotify, client, nullptr, nullptr, 0
    );
    int r = client->connect();
    if (r) {
        delete client;
        return  nullptr;
    }
    return client;
}
