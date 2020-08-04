package kryptic.signature.utils
import kryptic.generator.KPair
import java.security.Signature
import kryptic.utils.Kryptic
import kryptic.utils.String64
import java.security.KeyPair


abstract class KSignature : Kryptic {

    private var sig_algorithm = ""
    private var key_algorithm = ""

    protected constructor(sig_algorithm:String,key_algorithm:String){
        this.sig_algorithm = sig_algorithm
        this.key_algorithm = key_algorithm
    }

    
    fun pairSignKeys(data:ByteArray): Pair<ByteArray, KeyPair>? {
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
    fun pairSignKeys(data:String): Pair<ByteArray, KeyPair>? {
        return pairSignKeys((data.toByteArray()))
    }

    fun tripleSignKeysString(data:ByteArray): Triple<String,String,String>? {
        val pair = pairSignKeys(data) ?: return null
        val signature = String64(pair.first)
        val publicKey = String64(pair.second.public.encoded)
        val privateKey = String64(pair.second.private.encoded)
        return Triple(signature, publicKey, privateKey)
    }
    fun tripleSignKeysString(data:String): Triple<String, String, String>? {
        return tripleSignKeysString(data.toByteArray())
    }


    fun verify(data: ByteArray, signature: ByteArray, public_key: ByteArray): Boolean{
        val sign = Signature.getInstance(sig_algorithm)
        val pubKey = KPair(key_algorithm)?.publicKey(public_key) ?: return false
        sign.initVerify(pubKey)
        sign.update(data)
        return sign.verify(signature)
    }

}
