package kryptic.symmetric
import kryptic.symmetric.utils.KSymmetric

class RC2 : KSymmetric {

    constructor() : super("RC2", key_size = "in" to (5..128).toList(), iv_size = 0)
    constructor(keyFrom5To128Char: String) : this(keyFrom5To128Char.toByteArray()) {
        key(keyFrom5To128Char)
    }
    constructor(keyFrom5To128Byte: ByteArray) : this() {
        key(keyFrom5To128Byte)
    }

    fun key(keyFrom5To128Char: String) : RC2 {
        return key(keyFrom5To128Char.toByteArray())
    }
    fun key(keyFrom5To128Byte: ByteArray) : RC2 {
        key = keyFrom5To128Byte
        return this
    }

}