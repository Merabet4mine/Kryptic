package kryptic.generator
import kryptic.utils.Bytes64
import kryptic.utils.Kryptic
import kryptic.utils.String64
import java.security.KeyPair
import java.security.PublicKey
import java.security.PrivateKey
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.spec.X509EncodedKeySpec
import java.security.spec.PKCS8EncodedKeySpec

class KPair : Kryptic {

    private var algorithm = ""
    var keySize : Int?=null; private set

    private constructor(algorithm:String) {
        this.algorithm = algorithm
    }

    companion object {

        operator fun invoke(algorithm:String) : KPair? {
            return when(algorithm.toUpperCase()){
                "DSA"           -> DSA
                "DIFFIEHELLMAN" -> DiffieHellman
                "EC"            -> EC
                "RSA"           -> RSA
                "RSASSA"        -> RSASSA
                "RSASSA-PSS"    -> RSASSA
                "X25519"        -> X25519
                "X448"          -> X448
                "XDH"           -> XDH
                else            -> KPair(algorithm)
            }
        }

        val DSA           get() = KPair("DSA")
        val DiffieHellman get() = KPair("DiffieHellman")
        val EC            get() = KPair("EC")
        val RSA           get() = KPair("RSA")
        val RSASSA        get() = KPair("RSASSA")
        val X25519        get() = KPair("X25519")
        val X448          get() = KPair("X448")
        val XDH           get() = KPair("XDH")

    }

    operator fun invoke(key_size:Int): KPair {
        this.keySize = key_size
        return this
    }

    fun generate() : KeyPair? {
        return try {
            val keyPairGenerator = KeyPairGenerator.getInstance(algorithm)
            if (keySize != null) keyPairGenerator.initialize(keySize!!)
            val keyPair = keyPairGenerator.generateKeyPair()
            keyPair
        }
        catch (e:Exception){ except(e.message, null) }
    }

    fun generatePairByteArray() : Pair<ByteArray, ByteArray>? {
        val keyPair : KeyPair = generate() ?: return null
        return Pair(keyPair.public.encoded, keyPair.private.encoded)
    }

    fun generatePairString() : Pair<String, String>? {
        val pair = generatePairByteArray() ?: return null
        val public = String64(pair.first)
        val private = String64(pair.second)
        return Pair(public, private)
    }

    fun publicKey(public_key:String): PublicKey? {
        return this.publicKey(Bytes64(public_key, false))
    }

    fun publicKey(public_key:ByteArray): PublicKey? {
        val keySpec = X509EncodedKeySpec(public_key)
        val keyFactory = KeyFactory.getInstance(algorithm)
        return keyFactory.generatePublic(keySpec)
    }

    fun privateKey(private_key:String): PrivateKey? {
        return privateKey(Bytes64(private_key, false))
    }

    fun privateKey(private_key:ByteArray): PrivateKey? {
        val keySpec = PKCS8EncodedKeySpec(private_key)
        val keyFactory = KeyFactory.getInstance(algorithm)
        return keyFactory.generatePrivate(keySpec)
    }

}