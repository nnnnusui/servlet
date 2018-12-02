package com.github.nnnnusui.servlet.server

import java.net.Socket

class WorkerThread(
    val socket: Socket
  ){
    fun run() {
        val input  = socket.inputStream
        val output = socket.outputStream
        val parser = RequestParser(input.bufferedReader())
        val handler = RequestHandler()

        val request  = parser.parse()

//        val webApp = WebApplication.searchWebApplication(appDir)

        val response = handler.handle(request)
        response.charSequence = request.charSequence
        response.send(output)

        socket.close()
    }
}
