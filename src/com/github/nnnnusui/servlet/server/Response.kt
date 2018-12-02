package com.github.nnnnusui.servlet.server

import com.github.nnnnusui.servlet.server.enums.Status
import java.io.OutputStream
import java.nio.charset.StandardCharsets
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class Response(
         val status:      Status?   = null
        ,val contentType: String    = ""
        ,val body:        ByteArray = "".toByteArray()
  ){
    val contentLength = body.size
    var charSequence = ""
    private val rfc1123Formatter = DateTimeFormatter.RFC_1123_DATE_TIME

    init { println("Response:: status: $status, contentType: $contentType") }

    fun send(out: OutputStream) {
        if(status == null) return

        val now = OffsetDateTime.now(ZoneOffset.UTC)
        val responce = """
          |HTTP/1.1 ${status.statusCode}
          |Date: ${rfc1123Formatter.format(now)}
          |Server: HttpServer
          |Content-Type: ${contentType}
          |Content-Length: ${contentLength.toString()}
          |Connection: Close
          |
          |
          """.trimMargin() ;println(responce)
        out.write(responce.toByteArray(StandardCharsets.UTF_8))
        out.write(body)
    }
}
