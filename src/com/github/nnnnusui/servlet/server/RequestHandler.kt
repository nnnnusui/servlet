package com.github.nnnnusui.servlet.server

import com.github.nnnnusui.servlet.Application
import com.github.nnnnusui.servlet.FileClassLoader
import com.github.nnnnusui.servlet.server.enums.Status
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class RequestHandler {
    val RESOURCE_DIR = "resources"
    val ROOT_DIR = "$RESOURCE_DIR/public"
    val BAD_REQUEST_HTML_PATH = Paths.get(ROOT_DIR, "400.html")
    val FORBIDDEN_HTML_PATH   = Paths.get(ROOT_DIR,"403.html")
    val NOT_FOUND_HTML_PATH   = Paths.get(ROOT_DIR, "404.html")
    val mimeDetector = MimeDetector("$RESOURCE_DIR/mimes.properties")
    val HTML_MIME    = "text/html;charset=utf8"

    fun handle(request: Request): Response {
        return getResponse(request)
    }

    private fun getResponse(request: Request): Response {
        if (request.method == null)
            return Response(Status.BAD_REQUEST, HTML_MIME, Files.readAllBytes(BAD_REQUEST_HTML_PATH))

        val resourcePath = Paths.get(ROOT_DIR, request.path).normalize()
        if (!resourcePath.startsWith("$ROOT_DIR/")) // ディレクトリトラバーサルちゃん対策
            return Response(Status.FORBIDDEN, HTML_MIME, Files.readAllBytes(FORBIDDEN_HTML_PATH))

        val mime = mimeDetector.getMime(resourcePath)
        if (mime.isEmpty()) {
            val classFilePath = Paths.get("$resourcePath.class")
            if (Files.isRegularFile(classFilePath)) {
                val servletClass = FileClassLoader().load(classFilePath).newInstance() as Application
                println(servletClass)
                return servletClass.servlet(request, Response(Status.OK, HTML_MIME))
            } else if (Files.isDirectory(resourcePath)) {
                val indexFilePath = resourcePath.resolve("index.html")
                if (Files.isRegularFile(indexFilePath))
                    return Response(Status.OK, HTML_MIME, Files.readAllBytes(indexFilePath))
            }
        } else if (Files.isRegularFile(resourcePath)) {
            return Response(Status.OK, mime, Files.readAllBytes(resourcePath))
        }

        return Response(Status.NOT_FOUND, HTML_MIME, Files.readAllBytes(NOT_FOUND_HTML_PATH))
    }
}