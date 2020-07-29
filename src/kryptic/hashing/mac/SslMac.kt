package kryptic.hashing.mac
import kryptic.hashing.utils.HashWithKey

class SslMac : HashWithKey {


    private constructor(algorithm:String) : super(algorithm)

    companion object {
        val MD5 = SslMac("SslMacMD5")
        val SHA1 = SslMac("SslMacSHA1")
    }

}
