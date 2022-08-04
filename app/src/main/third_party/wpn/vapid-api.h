#include <string>

void *openEnv(const std::string &filename);

void closeEnv(void *env);

void saveEnv(void *env);

void saveEnvAs(void *env, const std::string &fileName);

int envErrorCode(void *env);

std::string envErrorDescription(void *env);

std::string env2json(void *env);

void setConfigJson(void *env, const std::string &json);

void *openRegistryClientEnv(void *env);

void closeRegistryClientEnv(void *regclienv);

int regErrorCode(void *reg);

std::string regErrorDescription(void *reg);

bool validateRegistration(void *regclienv);

bool isPrivateKeyValid(const std::string &value);

bool isPublicKeyValid(const std::string &value);

bool isAuthSecretValid(const std::string &value);

bool isTokenValid(const std::string &value);

bool subscribe2VapidPublicKey(
    std::string &retval,
    void *env,
    const std::string &key,
    const std::string &authSecret
);

