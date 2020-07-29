package kryptic.signature
import kryptic.signature.utils.KSignature

class NONE : KSignature {

    private constructor(algo:String, key:String) : super(algo, key)

    companion object {
        val DSA get() = NONE("NONEwithDSA", "DSA") // Data must be exactly 20 bytes long
        val RSA get() = NONE("NONEwithRSA", "RSA")
    }
}