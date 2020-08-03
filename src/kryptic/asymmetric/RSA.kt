package kryptic.asymmetric

import kryptic.asymmetric.utils.KAsymmetric
import kryptic.generator.KPair
import kryptic.utils.String64
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


fun main() {


    val keyPair = KPair.RSA.pairString()
    val data = "Amine--"

    println(keyPair!!.first)
    println(keyPair.second)

    val rsa = RSA()// .key(keyPair)

    val e = rsa.publicKey(keyPair.first).encryptToString(data)
    println((e!!))
    val d = rsa.privateKey(keyPair.second).decryptToString(e)
    println((d!!))
}