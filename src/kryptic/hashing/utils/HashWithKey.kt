package kryptic.hashing.utils

import javax.crypto.Mac
import kryptic.utils.Kryptic
import kryptic.utils.String64
import javax.crypto.spec.SecretKeySpec

abstract class HashWithKey : Kryptic {

    private val algorithme : String
    var key : ByteArray? = null ; private set

    protected constructor(algorithme:String) {
        this.algorithme = algorithme
    }

    /////////////////////////////////////

    fun bytes(data: String) = bytes(data.toByteArray())
    fun bytes(data: ByteArray) : ByteArray? {
        return try {
            val mac = Mac.getInstance(algorithme)
            val key = SecretKeySpec(key, algorithme)
            mac.init(key)
            mac.doFinal(data)
        }
        catch (e:Exception){
            except(e.message, null)
        }
    }

    fun string(data: String) = string(data.toByteArray())
    fun string(data: ByteArray) = String64(bytes(data))


    operator fun invoke(key: String) = this(key.toByteArray())
    operator fun invoke(key: ByteArray) : HashWithKey {
        this.key = key
        return this
    }

}