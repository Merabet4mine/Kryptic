package kryptic.signature
import kryptic.signature.utils.KSignature

class MD : KSignature {

    private constructor(algo:String, key:String) : super(algo, key)

    object _2 {
        val RSA get() = MD("MD2withRSA", "RSA")
    }
    object _5 {
        val RSA get() = MD("MD5withRSA", "RSA")
    }
    object SHA {
        val RSA get() = MD("MD5andSHA1withRSA", "RSA")
    }

}