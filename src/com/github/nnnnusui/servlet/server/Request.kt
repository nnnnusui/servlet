package com.github.nnnnusui.servlet.server

import com.github.nnnnusui.servlet.server.enums.Method
import java.net.URLDecoder
import java.nio.file.Path

class Request(
  val method:      Method?
 ,val rawPath:     String = ""
 ,val httpVersion: String = ""
 ,val header:      HashMap<String, String> = HashMap<String, String>()
 ,val rawBody:     String = ""
){
    var charSequence = "UTF-8"

    val path = rawPath.substringBefore("?")
    val parameterMap = getParamMap(rawPath)
    val body get() = URLDecoder.decode(rawBody, charSequence).replace("\n".toRegex(),"")


    fun getParameter(key: String): String {
        val param =  parameterMap.getOrDefault(key, "")
        return URLDecoder.decode(param)
    }

    init { println("Request:: method_$method, path_$rawPath, ver_$httpVersion") }
    override fun toString(): String
            = "Request:: method_$method, path_$path, ver_$httpVersion"




    private fun getParamMap(path: String): HashMap<String, String> {
        println(path.substringAfter("?"))
        val paramList = path.substringAfter("?").split("&")
        val map = HashMap<String, String>(paramList.count())
        paramList.forEach {
            if (it.contains('=')) {
                val (key, value) = it.split("=")
                map.put(key, value)
            }
        }
        return map
    }
}