package kryptic.signature.utils
import kryptic.generator.KPair
import java.security.Signature
import kryptic.utils.Kryptic
import kryptic.utils.String64
import java.security.KeyPair


abstract class KSignature : Kryptic {

    private var sig_algorithm = ""
    private var key_algorithm = ""

    constructor(sig_algorithm:String,key_algorithm:String){
        this.sig_algorithm = sig_algorithm
        this.key_algorithm = key_algorithm
    }

    fun sign(data:ByteArray) : Pair<ByteArray, KeyPair>? {
        return try {
            val signature = Signature.getInstance(sig_algorithm)
            val keyPair = KPair(key_algorithm)?.pairKey() ?: return null
            signature.initSign(keyPair.private)
            signature.update(data)
            val digitalSignature = signature.sign()
            Pair(digitalSignature, keyPair)
        }
        catch (e:Exception) { except(e.message, null) }
    }

    fun sign(data:String) : Pair<ByteArray, KeyPair>? {
        return sign((data.toByteArray()))
    }

    fun signToString(data:ByteArray) : Triple<String, String, String>? {
        val pair = sign(data) ?: return null
        val signature = String64(pair.first)
        val publicKey = String64(pair.second.public.encoded)
        val privateKey = String64(pair.second.private.encoded)
        return Triple(signature, publicKey, privateKey)
    }

    fun signToString(data:String) : Triple<String, String, String>? {
        return signToString(data.toByteArray())
    }


    ////////////////////////////////////////////////

    /*
    fun verify(input :String, public_key :String, charset: Charset = Charsets.UTF_8): Boolean {
        return verify(input.toByteArray(charset), public_key.toByteArray(charset))
    }

    fun verify(input :ByteArray, public_key :String, charset: Charset = Charsets.UTF_8): Boolean {
        return verify(input, public_key.toByteArray(charset))
    }

    fun verify(input :String, public_key :ByteArray, charset: Charset = Charsets.UTF_8): Boolean {
        return verify(input.toByteArray(charset), public_key)
    }
    */

    fun verify(data: ByteArray, signature: ByteArray, public_key: ByteArray): Boolean{
        val sign = Signature.getInstance(sig_algorithm)
        val pubKey = KPair(key_algorithm)?.publicKey(public_key) ?: return false
        sign.initVerify(pubKey)
        sign.update(data)
        return sign.verify(signature)
    }

}
