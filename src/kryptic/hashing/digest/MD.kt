package kryptic.hashing.digest
import kryptic.hashing.utils.HashWithoutKey

class MD : HashWithoutKey {

    private constructor(algorithm:String) : super(algorithm)

    companion object {
        val _2 = MD("MD2")
        val _5 = MD("MD5")
    }

    operator fun invoke(data: String) = this(data.toByteArray())
    operator fun invoke(data: ByteArray) = hashing(data)

}