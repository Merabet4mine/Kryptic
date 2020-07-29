package kryptic.hashing
import kryptic.utils.Kryptic

class Fletcher : Kryptic() {

    operator fun invoke(input: String) : String{
        return this(input.toByteArray())
    }
    operator fun invoke(input: ByteArray) : String{
        return try {
            var sum1 = 0
            var sum2 = 0
            for (d in input) {
                sum1 = (sum1 + d) % 255
                sum2 = (sum2 + sum1) % 255
            }
            (sum2 shl 8 or sum1).toString()
        }
        catch (e:Exception){ except("Fletcher : ${e.message}", "") }
    }

}