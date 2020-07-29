package kryptic.generator
import kryptic.utils.Kryptic
import kryptic.utils.String64
import javax.crypto.SecretKey
import javax.crypto.KeyGenerator
import java.security.SecureRandom


class KGene : Kryptic {

    private var algorithm = ""
    private var keySize : Int? = null

    private constructor(algorithm:String) {
        this.algorithm = algorithm
    }

    companion object {

        operator fun invoke(algorithm:String) : Kryptic? {
            return when(algorithm.toUpperCase()){
                "AES"                      -> AES
                "ARCFOUR"                  -> ARCFOUR
                "BLOWFISH"                 -> Blowfish
                "CHACHA20"                 -> ChaCha20
                "DES"                      -> DES
                "DESEDE"                   -> DESede
                "TDES"                     -> TDES
                "HMACMD5"                  -> HmacMD5
                "HMACSHA1"                 -> HmacSHA1
                "HMACSHA224"               -> HmacSHA224
                "HMACSHA256"               -> HmacSHA256
                "HMACSHA384"               -> HmacSHA384
                "HMACSHA512"               -> HmacSHA512
                "RC2"                      -> RC2
                "SUNTLS12PRF"              -> SunTls12Prf
                "SUNTLSKEYMATERIAL"        -> SunTlsKeyMaterial
                "SUNTLSMASTERSECRET"       -> SunTlsMasterSecret
                "SUNTLSPRF"                -> SunTlsPrf
                "SUNTLSRSAPREMASTERSECRET" -> SunTlsRsaPremasterSecret
                else                       -> KGene(algorithm)
            }
        }

        val AES                      get() = KGene("AES")
        val ARCFOUR                  get() = KGene("ARCFOUR")
        val Blowfish                 get() = KGene("Blowfish")
        val ChaCha20                 get() = KGene("ChaCha20")
        val DES                      get() = KGene("DES")
        val DESede                   get() = KGene("DESede")
        val TDES                     get() = KGene("DESede")
        val HmacMD5                  get() = KGene("HmacMD5")
        val HmacSHA1                 get() = KGene("HmacSHA1")
        val HmacSHA224               get() = KGene("HmacSHA224")
        val HmacSHA256               get() = KGene("HmacSHA256")
        val HmacSHA384               get() = KGene("HmacSHA384")
        val HmacSHA512               get() = KGene("HmacSHA512")
        val RC2                      get() = KGene("RC2")
        val SunTls12Prf              get() = KGene("SunTls12Prf")
        val SunTlsKeyMaterial        get() = KGene("SunTlsKeyMaterial")
        val SunTlsMasterSecret       get() = KGene("SunTlsMasterSecret")
        val SunTlsPrf                get() = KGene("SunTlsPrf")
        val SunTlsRsaPremasterSecret get() = KGene("SunTlsRsaPremasterSecret")


    }

    fun secretKey(): SecretKey? {
        return try {
            val keyGen = KeyGenerator.getInstance(algorithm)
            val secRandom = SecureRandom()
            keyGen!!.init(secRandom)
            if (keySize != null) keyGen.init(keySize!!)
            return keyGen.generateKey()
        }
        catch (e:Exception){ except(e.message, null) }
    }

    fun byteArray(): ByteArray? {
        return secretKey()?.encoded
    }

    fun string(): String {
        return String64(byteArray())
    }

    operator fun invoke(key_size:Int): KGene {
        this.keySize = key_size * 8
        return this
    }

}