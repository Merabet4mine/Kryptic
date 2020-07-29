package kryptic.hashing.digest
import kryptic.hashing.utils.HashWithoutKey

class SHA3 : HashWithoutKey {

    private constructor(algorithm:String) : super(algorithm)
    companion object {
        val _224 = SHA3("SHA3-224")
        val _256 = SHA3("SHA3-256")
        val _384 = SHA3("SHA3-384")
        val _512 = SHA3("SHA3-512")
    }
    operator fun invoke(data: String) = this(data.toByteArray())
    operator fun invoke(data: ByteArray) = hashing(data)
}