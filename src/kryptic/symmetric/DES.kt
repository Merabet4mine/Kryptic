package kryptic.symmetric
import kryptic.symmetric.utils.KSymmetric


class DES : KSymmetric {

    private constructor(mode:String, padding:String, iv_size:Int) : super("DES", mode, padding, 8, iv_size)

    companion object {

        operator fun invoke() = ECB
        val ECB get() = DES("ECB", "PKCS5Padding", 0)
        val CBC get() = DES("CBC", "PKCS5Padding", 8)
        val CFB get() = DES("CFB", "PKCS5Padding", 8)
        val OFB get() = DES("OFB", "PKCS5Padding", 8)

    }

    fun key(key8Char: String) : DES {
        return key(key8Char.toByteArray())
    }
    fun key(key8Byte: ByteArray) : DES {
        key = key8Byte
        return this
    }

    fun iv(iv8Char: String) : DES {
        return iv(iv8Char.toByteArray())
    }
    fun iv(iv8Byte: ByteArray) : DES {
        iv = iv8Byte
        return this
    }

}

class TDES : KSymmetric {

    private constructor(mode:String, padding:String, iv_size: Int) : super("DESEDE", mode, padding, 24, iv_size)

    companion object {

        operator fun invoke() = ECB
        val ECB get() = TDES("ECB", "PKCS5Padding", 0)
        val CBC get() = TDES("CBC", "PKCS5Padding", 8)
        val CFB get() = TDES("CFB", "PKCS5Padding", 8)
        val OFB get() = TDES("OFB", "PKCS5Padding", 8)

    }

    fun key(key24Char: String) : TDES {
        return key(key24Char.toByteArray())
    }
    fun key(key24Byte: ByteArray) : TDES {
        key = key24Byte
        return this
    }

    fun iv(iv8Char: String) : TDES {
        return iv(iv8Char.toByteArray())
    }
    fun iv(iv8Byte: ByteArray) : TDES {
        iv = iv8Byte
        return this
    }

}