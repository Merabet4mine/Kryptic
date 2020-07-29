package kryptic.hashing.mac
import kryptic.hashing.utils.HashWithKey

class HMac : HashWithKey {

    private constructor(algorithm:String) : super(algorithm)

    companion object {
        val MD5 = HMac("HmacMD5")
        val SHA1 = HMac("HmacSHA1")
        val SHA224 = HMac("HmacSHA224")
        val SHA256 = HMac("HmacSHA256")
        val SHA512 = HMac("HmacSHA512")
        val SHA512d224 = HMac("HmacSHA512/224")
        val SHA512d256 = HMac("HmacSHA512/256")
    }


}