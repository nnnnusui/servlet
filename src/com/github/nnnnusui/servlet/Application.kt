package com.github.nnnnusui.servlet

import com.github.nnnnusui.servlet.server.Request
import com.github.nnnnusui.servlet.server.Response

abstract class Application{
    abstract fun servlet(request: Request, response: Response): Response
}