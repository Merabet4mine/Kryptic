package kryptic.symmetric.utils
import javax.crypto.Cipher
import kryptic.utils.Bytes64
import kryptic.utils.Kryptic
import kryptic.utils.String64
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec


abstract class KSymmetric : Kryptic {

    private var algorithm = ""
    private var mode = ""
    private var padding = ""

    private var key_size = Pair("", listOf(0))
    private var iv_size = 0

    var key : ByteArray? = null ; protected set
    var iv : ByteArray? = null ; protected set


    constructor(algorithm: String, mode:String?=null, padding: String?=null,
                key_size: Pair<String, List<Int>>, iv_size: Int) : super() {

        this.algorithm = algorithm
        this.mode = mode ?: this.mode
        this.padding = padding ?: this.padding
        this.key_size = key_size
        this.iv_size = iv_size
    }


    constructor(algorithm: String,
                mode:String?=null,
                padding: String?=null,
                key_size: Int,
                iv_size: Int)
            : this(algorithm, mode, padding, "==" to listOf(key_size), iv_size)


    /////////////////////////////////////////
    fun encrypt(data:ByteArray?) : ByteArray? {
        if (!keyValidation()) return except(showCondition(), null)
        return try {
            val cipher = cipher(Cipher.ENCRYPT_MODE) ?: return null
            val output = cipher.doFinal(data)
            return Bytes64(output)
        }
        catch (e: Exception){ except(e.message, null)}
    }
    fun encrypt(data:String?): ByteArray?{
        return encrypt(data?.toByteArray() ?: ByteArray(0))
    }
    fun encryptToString(data:String?): String?{
        return String(encrypt(data) ?: ByteArray(0))
    }
    fun encryptToString(data:ByteArray?): String?{
        return String(encrypt(data) ?: ByteArray(0))
    }

    ///////////////////////////////////////
    fun decrypt(data:ByteArray?):ByteArray?{
        if (!keyValidation()) return except(showCondition(), null)
        return try {
            val cipher = cipher(Cipher.DECRYPT_MODE) ?: return null
            val output = Bytes64(data, false)
            return cipher.doFinal(output)
        }
        catch (e: Exception){ except( e.message,null) }
    }
    fun decrypt(data:String?): ByteArray?{
        return decrypt(data?.toByteArray())
    }
    fun decryptToString(data:String?): String?{
        return String(decrypt(data) ?: ByteArray(0))
    }
    fun decryptToString(data:ByteArray?): String?{
        return String(decrypt(data) ?: ByteArray(0))
    }

    ///////////////////////////////////////
    private fun cipher(opmode:Int): Cipher? {
        return try {
            val key = SecretKeySpec(key,algorithm)
            val transformation = algorithm + if (mode.isNotEmpty()) "/$mode/$padding" else ""
            val cipher = Cipher.getInstance(transformation)
            if (mode.isEmpty() || mode == "ECB") cipher.init(opmode, key)
            else cipher.init(opmode,key, IvParameterSpec(iv))
            return cipher
        }
        catch (e: Exception){ except(e.message,null)}
    }
    private fun keyValidation() : Boolean {
        val op = key_size.first
        val value = key_size.second[0]
        if (key == null) return false
        return when(op){
            "==" -> key!!.size == value
            "!=" -> key!!.size != value
            "<=" -> key!!.size <= value
            ">=" -> key!!.size >= value
            ">"  -> key!!.size >  value
            "<"  -> key!!.size <  value
            "in" -> key!!.size in  key_size.second
            "!in"-> key!!.size !in key_size.second
            else -> false
        }
    }
    private fun showCondition(): String {
        val msg = "The key size should be ${key_size.first} "
        return if (key_size.second.size > 5 &&
                key_size.second == (key_size.second.first()..key_size.second.last()).toList())
            msg + "${key_size.second.first()} to ${key_size.second.last()} byte."
        else if (key_size.second.size == 1) msg + key_size.second[0] + " byte."
        else msg + key_size.second + " byte."
    }

}