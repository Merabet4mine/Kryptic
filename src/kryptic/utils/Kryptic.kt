package kryptic.utils

abstract class Kryptic {


    protected fun <T> Any.except(message:String?, result:T):T {
        var text:String
        val name = javaClass.simpleName ?: "Unknown"
        val pack = javaClass.`package`.name ?: "Unknown"
        text  = "Class name : $name\n"
        text += "Class package : $pack\n"
        if (message != null && message.isNotEmpty())
            text += "Exception : $message"
        System.err.println(text + "\n")
        return result
    }

}