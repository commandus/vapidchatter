/*
 * Copyright (c) 2019 Andrei Ivanov andrei.i.ivanov@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

#ifndef VAPID_H
#define VAPID_H 1

#include <string>

std::string base64UrlEncode(
	const void *data,
	size_t size
);

/**
 * @return 0- success
 */
int generateKeys(
	std::string &retPrivateKey,
	std::string &retPublicKey,
	std::string &retAuthSecret
);

int encryptEC (
	std::string &retVal,
	const std::string &val,
	const std::string &publicKey,
	const std::string &authSecret
);

int decryptEC(
	std::string &retVal,
	const std::string &val,
	const std::string &privateKey,
	const std::string &authSecret
);

#endif
