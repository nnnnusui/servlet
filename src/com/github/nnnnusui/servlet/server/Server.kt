package com.github.nnnnusui.servlet.server

import java.io.IOException
import java.net.ServerSocket
import kotlin.concurrent.thread

class Server(val PORT: Int) {
    private val alive = true

    fun start() {
        val server = ServerSocket(PORT)
        while(alive) {
            try {
                println("クライアントからの接続を待ちます。")
                val socket = server.accept()
                val worker = WorkerThread(socket)
                thread {
                    worker.run()
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            } finally {
                println("通信を終了しました。")
            }
        }
    }
}

fun main(args: Array<String>) {
    Server(8080).start()
}