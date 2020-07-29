package kryptic.signature
import kryptic.signature.utils.KSignature

class SHA : KSignature {

    private constructor(algo:String, key:String) : super(algo, key)

    companion object {
        val RSA get() = SHA("SHA1withRSA", "RSA")
    }

    object _256 {
        val DSA get() = SHA("SHA256withDSA", "DSA")
        val RSA get() = SHA("SHA256withRSA", "RSA")
    }

    object _384 {
        val RSA get() = SHA("SHA384withRSA", "RSA")
    }

    object _512 {
        val RSA get() = SHA("SHA512withRSA", "RSA")
    }

    object _512d224 {
        val RSA get() = SHA("SHA512/224withRSA", "RSA")
    }

    object _512d256 {
        val RSA get() = SHA("SHA512/256withRSA", "RSA")
    }

}