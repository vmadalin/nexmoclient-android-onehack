/*
 * Copyright (C) 2020 Nexmo, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nexmo.helper.jwt

import android.util.Base64
import com.google.gson.Gson
import com.nexmo.helper.jwt.extensions.toMap
import com.nexmo.helper.jwt.models.Acl
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Date
import java.util.UUID

private const val JWT_EXPIRATION_MILLISECONDS = 60 * 60 * 1000
private const val JWT_ISSUER = "Max NPE app"
private const val PRIVATE_KEY_CONTENT_REGEX = "-{3,}\\n([\\s\\S]*?)\\n-{3,}"
private const val PRIVATE_KEY_ALGORITHM = "RSA"

class JWTHelper {

    fun generateToken(privateKey: String, applicationId: String, username: String? = null): String {
        val currentTime = Date()
        val expiration = Date(currentTime.time + JWT_EXPIRATION_MILLISECONDS)

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setId(UUID.randomUUID().toString())
            .setSubject(username)
            .setExpiration(expiration)
            .setIssuedAt(currentTime)
            .setIssuer(JWT_ISSUER)
            .signWith(
                generatePrivateKey(privateKey, PRIVATE_KEY_ALGORITHM),
                SignatureAlgorithm.RS256
            )
            .addClaims(
                mutableMapOf(
                    "application_id" to applicationId,
                    "acl" to Gson().toMap(Acl())
                )
            )
            .compact()
    }

    private fun generatePrivateKey(privateKey: String, algorithm: String): PrivateKey {
        try {
            val base64PrivateKey = Regex(PRIVATE_KEY_CONTENT_REGEX).find(privateKey)?.let {
                it.groupValues[1]
            } ?: run {
                privateKey
            }
            val encodedKey: ByteArray = Base64.decode(base64PrivateKey, Base64.DEFAULT)
            val keySpec = PKCS8EncodedKeySpec(encodedKey)
            return KeyFactory
                .getInstance(algorithm)
                .generatePrivate(keySpec)
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalStateException("Failed to resolve KeyFactory for algorithm $algorithm", e)
        } catch (e: InvalidKeySpecException) {
            throw IllegalStateException("Invalid private key for algorithm $algorithm", e)
        }
    }
}
