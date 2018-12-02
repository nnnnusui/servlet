package com.github.nnnnusui.servlet.server.enums

enum class Status(val statusCode: String) {
    OK("200 OK")
   ,MOVED_PERMANENTLY("301 Moved Permanently")
   ,BAD_REQUEST("400 BadRequest")
   ,FORBIDDEN("403 Forbidden")
   ,NOT_FOUND("404 NotFound")
   ,INTERNAL_SERVER_ERROR("500 InternalServerError")
    ;
}
