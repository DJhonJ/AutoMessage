package com.whatsmessage.framework.common

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Encryption {
    companion object {
        fun encrypt(textToEncrypt: String): String {
            return textToEncrypt
        }
    }
}