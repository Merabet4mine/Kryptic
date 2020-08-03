package kryptic.asymmetric

import kryptic.asymmetric.utils.KAsymmetric
import java.security.KeyPair

class RSA : KAsymmetric {

    private constructor(mode:String, padding:String) : super("RSA", mode, padding)
    companion object {

        operator fun invoke() = PKCS1
        operator fun invoke(keyPair: KeyPair) = PKCS1.keys(keyPair)

        val PKCS1  get() = RSA("ECB", "PKCS1Padding")
        val SHA1   get() = RSA("ECB", "OAEPWithSHA-1AndMGF1Padding")
        val SHA256 get() = RSA("ECB", "OAEPWithSHA-256AndMGF1Padding")

    }


}
