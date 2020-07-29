package kryptic.symmetric
import kryptic.symmetric.utils.KSymmetric

class Arcfour : KSymmetric {

    constructor() : super("ARCFOUR", key_size = "in" to (5..128).toList(), iv_size = 0)
    constructor(keyFrom5To128Char: String) : this(keyFrom5To128Char.toByteArray())
    constructor(keyFrom5To128Byte: ByteArray) : this() {
        key(keyFrom5To128Byte)
    }

    fun key(keyFrom5To128Char: String) : Arcfour {
        return key(keyFrom5To128Char.toByteArray())
    }
    fun key(keyFrom5To128Byte: ByteArray) : Arcfour {
        key = keyFrom5To128Byte
        return this
    }

}