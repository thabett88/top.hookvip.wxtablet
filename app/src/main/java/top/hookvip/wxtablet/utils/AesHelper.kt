package top.hookvip.wxtablet.utils

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AesHelper(key: String, iv: String) {
    private val mSecretKeySpec = SecretKeySpec(key.encodeToByteArray(), "AES/CBC/PKCS7Padding");
    private val mCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
    private val mIvParameterSpec = IvParameterSpec(iv.encodeToByteArray());

    fun encrypt(data: ByteArray): ByteArray {
        mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec, mIvParameterSpec)
        return mCipher.doFinal(data)
    }

    fun decrypt(data: ByteArray): ByteArray {
        mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, mIvParameterSpec)
        return mCipher.doFinal(data)
    }
}