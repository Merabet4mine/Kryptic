package kryptic.hashing
import kryptic.hashing.digest.SHA
import kryptic.hashing.digest.SHA3
import kryptic.utils.Kryptic
import kotlin.math.abs

class Keuna: Kryptic(){

    private val dictionary = ('a'..'z') + ('0'..'9') + ('A'..'Z')

    var iv : String? = "" ; private set
    var input : String = "" ; private set
    var outputSize : Int = 32 ; private set

    operator fun invoke(input:String=this.input, output_size:Int=this.outputSize, iv:String?=this.iv) : Keuna {
        this.input = input
        this.outputSize = output_size
        this.iv = iv
        return this
    }

    val sha :String? get() {
        return if (valid()){
            val sha256 = SHA._256(input) ?: return null
            val sha512 = SHA._512(input) ?: return null
            val sha256h512 = SHA._512(sha256) ?: return null
            merge(sha256 + sha256h512 + sha512)
        }
        else null
    }
    val sha3 :String? get() {
        return if (valid()) {
            val sha256 = SHA3._256(input)  ?: return null
            val sha512 = SHA3._512(input) ?: return null
            val sha256h512 = SHA3._512(sha256) ?: return null
            merge(sha256 + sha256h512 + sha512)
        }
        else null
    }

    private fun valid():Boolean{
        if (outputSize !in 2..128) return except("Keuna : Output size must be between 2 and 128!!",false)
        if (iv == null) return except("Keuna : Your IV is null!!.",false)
        if (iv!!.length < 2) iv = input
        return true
    }
    private fun merge(dataHash:String):String?{
        if (dataHash.length < outputSize * 2) return null
        val dataHashSplited = dataHash.split(dataHash.length / outputSize)
        val hashCodes = dataHashSplited.map {
            abs("$it$iv".hashCode()) % dictionary.size
        }
        var result = ""
        hashCodes.forEach {
            result += dictionary[it]
        }
        return result
    }
    private fun String.split(step:Int): List<String>{
        val res = arrayListOf<String>()
        for (i in 0..length - step step step){
            res.add(substring(i, i + step))
        }
        return res
    }


}