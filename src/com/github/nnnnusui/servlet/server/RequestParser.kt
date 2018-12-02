package com.github.nnnnusui.servlet.server

import com.github.nnnnusui.servlet.server.enums.Method
import java.io.BufferedReader
import java.net.URLDecoder

class RequestParser(val br: BufferedReader) {

    fun parse(): Request {
        val requestLine = br.readLine() ?: ""
        val splitList = requestLine.split(" ".toRegex())
        if (splitList.size != 3) return Request(null)

        val method = toMethodOrNull(splitList[0]) ?: return Request(null)
        val targetPath = splitList[1]
        val httpVersion = splitList[2]

        val requestHeader = getRequestHeader()
        val requestBody   = getRequestBody((requestHeader.get("Content-Length") ?: "0").toInt())

        return Request(method, targetPath, httpVersion, requestHeader, requestBody)
    }

    private fun toMethodOrNull(string: String): Method?
            =   try {
                    Method.valueOf(string)
                } catch (e: IllegalArgumentException) {
                    null
                }

    private fun getRequestHeader(): HashMap<String, String> {
        val map = HashMap<String, String>()
        do {
            val line = br.readLine()
            if(line.contains(":")){
                val name = line.substringBefore(":")
                val text = line.substringAfter(":")
                map.put(name, text)
            }
        } while(!line.isEmpty())
        return map
    }

    private fun getRequestBody(contentLength: Int): String {
        return (0 until contentLength).map { br.read().toChar() }.joinToString()
        val rawBody = buildString {
            for(i in 0 until contentLength)
                append(br.read().toChar())
        }
//        return URLDecoder.decode(rawBody, "UTF-8").replace("\n".toRegex(),"")
    }
}
