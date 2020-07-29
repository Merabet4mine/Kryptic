package kryptic.symmetric
import kryptic.symmetric.utils.KSymmetric

class Blowfish : KSymmetric {

    private constructor(mode:String, padding:String, iv_size:Int = 8)
            : super("Blowfish", mode, padding, "in" to (1..56).toList(), iv_size)

    companion object {

        operator fun invoke() = ECB
        val ECB get() = Blowfish("ECB", "PKCS5Padding", 0)
        val CBC get() = Blowfish("CBC", "PKCS5Padding")
        val CFB get() = Blowfish("CFB", "PKCS5Padding")
        val OFB get() = Blowfish("OFB", "PKCS5Padding")
    }


    fun key(keyFrom1To56Char: String): Blowfish {
        return key(keyFrom1To56Char.toByteArray())
    }
    fun key(keyFrom1To56Byte: ByteArray): Blowfish {
        key = keyFrom1To56Byte
        return this
    }

    fun iv(iv8Char: String): Blowfish {
        return iv(iv8Char.toByteArray())
    }
    fun iv(iv8Byte: ByteArray): Blowfish {
        iv = iv8Byte
        return this
    }

}