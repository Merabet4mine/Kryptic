package kryptic.hashing.digest
import kryptic.hashing.utils.HashWithoutKey

class SHA : HashWithoutKey {

    private constructor(algorithm:String) : super(algorithm)

    companion object {

        operator fun invoke() = SHA("SHA")
        operator fun invoke(data: String) = SHA()(data)
        operator fun invoke(data: ByteArray) = SHA()(data)

        val _224 = SHA("SHA-224")
        val _256 = SHA("SHA-256")
        val _384 = SHA("SHA-384")
        val _512 = SHA("SHA-512")
        val _512d224 = SHA("SHA-512/224")
        val _512d256 = SHA("SHA-512/256")
    }

    operator fun invoke(data: String) = this(data.toByteArray())
    operator fun invoke(data: ByteArray) = hashing(data)


}