package kryptic.symmetric
import kryptic.symmetric.utils.KSymmetric

class AES : KSymmetric {

    private constructor() : super("AES", "", "", 16, 0)

    companion object {

        operator fun invoke() : AES {
            return AES()
        }
        operator fun invoke(key16Char: String) : AES{
            return AES().key(key16Char)
        }
        operator fun invoke(key16Byte: ByteArray) : AES{
            return AES().key(key16Byte)
        }

    }

    fun key(key16Char: String) : AES{
        return key(key16Char.toByteArray())
    }
    fun key(key16Byte: ByteArray?) : AES{
        key = key16Byte
        return this
    }

}

class AES128 : KSymmetric {

    private constructor(mode: String,padding: String, iv_size: Int) : super("AES", mode, padding, 16, iv_size)

    companion object {

        operator fun invoke() = ECB
        val ECB get() = AES128("ECB", "PKCS1Padding", 0)
        val CBC get() = AES128("CBC", "PKCS5Padding", 16)
        val CFB get() = AES128("CFB", "PKCS5Padding", 16)
        val OFB get() = AES128("OFB", "PKCS5Padding", 16)

    }

    fun key(key16Char: String) : AES128 {
        return key(key16Char.toByteArray())
    }
    fun key(key16Byte: ByteArray) : AES128 {
        key = key16Byte
        return this
    }

    fun iv(iv16Char: String) : AES128 {
        return iv(iv16Char.toByteArray())
    }
    fun iv(iv16Byte: ByteArray) : AES128 {
        iv = iv16Byte
        return this
    }

}

class AES192 : KSymmetric {

    private constructor(mode: String,padding: String, iv_size: Int) : super("AES", mode, padding, 24, iv_size)

    companion object {

        operator fun invoke() = ECB
        val ECB get() = AES192("ECB", "PKCS5Padding", 0)
        val CBC get() = AES192("CBC", "PKCS5Padding", 16)
        val CFB get() = AES192("CFB", "PKCS5Padding", 16)
        val OFB get() = AES192("OFB", "PKCS5Padding", 16)

    }


    fun key(key24Char: String) : AES192 {
        return key(key24Char.toByteArray())
    }
    fun key(key24Byte: ByteArray) : AES192 {
        key = key24Byte
        return this
    }

    fun iv(iv16Char: String) : AES192 {
        return iv(iv16Char.toByteArray())
    }
    fun iv(iv16Byte: ByteArray) : AES192 {
        iv = iv16Byte
        return this
    }
}

class AES256 : KSymmetric {

    private constructor(mode: String,padding: String, iv_size: Int) : super("AES", mode, padding, 32, iv_size)

    companion object {

        operator fun invoke() = ECB
        val ECB get() = AES256("ECB", "PKCS5Padding", 0)
        val CBC get() = AES256("CBC", "PKCS5Padding", 16)
        val CFB get() = AES256("CFB", "PKCS5Padding", 16)
        val OFB get() = AES256("OFB", "PKCS5Padding", 16)

    }


    fun key(key32Char: String) : AES256 {
        return key(key32Char.toByteArray())
    }
    fun key(key32Byte: ByteArray) : AES256 {
        key = key32Byte
        return this
    }

    fun iv(iv16Char: String) : AES256 {
        return iv(iv16Char.toByteArray())
    }
    fun iv(iv16Byte: ByteArray) : AES256 {
        iv = iv16Byte
        return this
    }

}


class X() : KSymmetric("AES", "CBC", "PKCS7", 16, 0){
    init {
        key = "1234567890123456".toByteArray()
        iv = "1234567890123456".toByteArray()
    }
}

fun main(args: Array<String>) {

    X().encrypt("amine")

}