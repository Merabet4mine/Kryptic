package kryptic.hashing.utils

import java.math.BigInteger
import kryptic.utils.Kryptic
import java.security.MessageDigest

abstract class HashWithoutKey : Kryptic {

    private val algorithm : String

    constructor(algorithm : String) {
        this.algorithm = algorithm
    }

    protected fun hashing(input: ByteArray) : String? {
        return try {
            val messageDigest = MessageDigest.getInstance(algorithm)
            val digest = messageDigest.digest(input)
            val signum = BigInteger(1, digest)
            var hash = signum.toString(16)
            while (hash.length < 32) hash = "0$hash"
            return hash
        }
        catch (e:Exception){
            except(e.message, null)
        }
    }

}