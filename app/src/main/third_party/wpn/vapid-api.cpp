#include "vapid-api.h"

#include "wp-storage-file.h"
#include "wp-registry.h"
#include "wp-subscribe.h"
#include "endpoint.h"

void *openEnv(const std::string &filename) {
  ConfigFile *wpnConfig = new ConfigFile(filename);
  return wpnConfig;
}

void closeEnv(void *env) {
  if (env) {
    ConfigFile *wpnConfig = (ConfigFile *) env;
    delete wpnConfig;
  }
}

void saveEnv(void *env) {
  if (env) {
      ConfigFile *wpnConfig = (ConfigFile *) env;
      wpnConfig->save();
  }
}

void saveEnvAs(void *env, const std::string &fileName) {
  if (env) {
      ConfigFile *wpnConfig = (ConfigFile *) env;
      wpnConfig->fileName = fileName;
      wpnConfig->save();
  }
}

void setConfigJson(
    void *env,
    const std::string &json
) {
    if (env) {
        std::stringstream jss(json);
        ConfigFile *wpnConfig = (ConfigFile *) env;
        wpnConfig->load(jss, true);
        wpnConfig->save();
    }
}

int envErrorCode(void *env) {
    if (env) {
        ConfigFile *wpnConfig = (ConfigFile *) env;
        return wpnConfig->errorCode;
    } else {
        return -1;
    }
}

std::string envErrorDescription(void *env) {
    if (env) {
        ConfigFile *wpnConfig = (ConfigFile *) env;
        return wpnConfig->errorDescription;
    } else {
        return "";
    }
}

std::string env2json(void *env) {
  if (env) {
    ConfigFile *wpnConfig = (ConfigFile *) env;
    return wpnConfig->toJsonString();
  } else
    return "";
}

void *openRegistryClientEnv(void *env) {
  if (!env)
    return NULL;
  RegistryClient *registryClient = new RegistryClient((ConfigFile *) env);

  if (registryClient->errorCode) {
    delete registryClient;
    return NULL;
  } else {
    return registryClient;
  }
}

void closeRegistryClientEnv(void *regclienv) {
  if (regclienv) {
    RegistryClient *registryClient = (RegistryClient *) regclienv;
    delete registryClient;
  }
}

int regErrorCode(void *reg) {
    if (reg) {
        RegistryClient *r = (RegistryClient *) reg;
        return r->errorCode;
    } else {
        return -1;
    }
}

std::string regErrorDescription(void *reg) {
    if (reg) {
        RegistryClient *r = (RegistryClient *) reg;
        return r->errorDescription;
    } else {
        return "";
    }
}

bool validateRegistration(void *regclienv) {
  if (regclienv) {
    RegistryClient *registryClient = (RegistryClient *) regclienv;
    return registryClient->validate();
  }
    return false;
}

bool isPrivateKeyValid(const std::string &value) {
  return WpnKeys::isPrivateKeyValid(value);
}

bool isPublicKeyValid(const std::string &value) {
    return WpnKeys::isPublicKeyValid(value);
}

bool isAuthSecretValid(const std::string &value) {
    return WpnKeys::isAuthSecretValid(value);
}

bool isTokenValid(const std::string &value) {
    return Subscription::isTokenValid(value);
}


bool subscribe2VapidPublicKey(
    std::string &retval,
    void *env,
    const std::string &key,
    const std::string &authSecret
)
{
    if (!env)
        return NULL;
    if (!WpnKeys::isPublicKeyValid(key))
        return NULL;
    if (!WpnKeys::isAuthSecretValid(authSecret))
        return NULL;

    ConfigFile *config = (ConfigFile *) env;

    // Make subscription
    std::string headers;
    std::string token;
    std::string pushset;

    uint64_t android_id = config->androidCredentials->getAndroidId();
    uint64_t security_number = config->androidCredentials->getSecurityToken();
    std::string appId = config->androidCredentials->getAppId();

    int r = subscribe(&retval, &headers, token, pushset,
                      toString(android_id), toString(security_number), appId, key, 0
    );
    Subscription subscription;
    if ((r >= 200) && (r < 300)) {
      subscription.setSubscribeMode(SUBSCRIBE_FORCE_VAPID);
      // subscription.setName(name);
      subscription.setPushSet(pushset);
      WpnKeys w(0, 0, "", key, authSecret);
      subscription.setWpnKeys(w);
      subscription.setEndpoint(endpoint(token, true, 0));

      while (config->subscriptions->rmByPublicKey(subscription.getWpnKeys().getPublicKey())) {
      }
      config->subscriptions->list.push_back(subscription);
      config->save();
        retval = subscription.toJsonString();
      return true;
    }
    return false;
}
