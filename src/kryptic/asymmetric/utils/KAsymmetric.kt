package kryptic.asymmetric.utils
import kryptic.generator.KPair
import javax.crypto.Cipher
import kryptic.utils.Bytes64
import kryptic.utils.Kryptic
import kryptic.utils.String64
import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec


abstract class KAsymmetric : Kryptic {

    private var algorithm = ""
    private var mode = ""
    private var padding = ""

    var iv : ByteArray? = null ; protected set

    var publicKey : PublicKey? = null ; protected set
    var privateKey : PrivateKey? = null ; protected set


    constructor(algorithm: String, mode:String?=null, padding: String?=null) : super() {
        this.algorithm = algorithm
        this.mode = mode ?: this.mode
        this.padding = padding ?: this.padding
    }


    /////////////////////////////////////////
    fun encrypt(data:ByteArray?) : ByteArray? {
        val cipher = cipher(Cipher.ENCRYPT_MODE) ?: return null
        val output = cipher.doFinal(data)
        return Bytes64(output)
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
        val cipher = cipher(Cipher.DECRYPT_MODE) ?: return null
        val output = Bytes64(data, false)
        return cipher.doFinal(output)
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
    fun keys(keyPair: KeyPair): KAsymmetric {
        this.publicKey = keyPair.public
        this.privateKey = keyPair.private
        return this
    }

    fun publicKey(publicKey: String): KAsymmetric {
        return publicKey(KPair(algorithm)?.publicKey(publicKey))
    }
    fun publicKey(publicKey: ByteArray): KAsymmetric {
        return publicKey(KPair(algorithm)?.publicKey(publicKey))
    }
    fun publicKey(publicKey: PublicKey?): KAsymmetric {
        this.publicKey = publicKey
        return this
    }

    fun privateKey(privateKey: String): KAsymmetric {
        return privateKey(KPair(algorithm)?.privateKey(privateKey))
    }
    fun privateKey(privateKey: ByteArray): KAsymmetric {
        return privateKey(KPair(algorithm)?.privateKey(privateKey))
    }
    fun privateKey(privateKey: PrivateKey?): KAsymmetric {
        this.privateKey = privateKey
        return this
    }



    ///////////////////////////////////////
    private fun cipher(opmode:Int): Cipher? {
        return try {
            if (opmode == Cipher.ENCRYPT_MODE && publicKey == null)
                return except("Public Key is null!", null)
            else if (opmode == Cipher.DECRYPT_MODE && privateKey == null)
                return except("Private Key is null!", null)

            val transformation = algorithm + if (mode.isNotEmpty()) "/$mode/$padding" else ""
            val cipher = Cipher.getInstance(transformation)
            val key = if (opmode == Cipher.ENCRYPT_MODE) publicKey else privateKey
            if (mode.isEmpty() || mode == "ECB") cipher.init(opmode, key)
            else cipher.init(opmode,key, IvParameterSpec(iv))
            return cipher
        }
        catch (e: Exception){ except(e.message,null)}
    }

}