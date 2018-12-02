package com.github.nnnnusui.servlet.server

import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Path
import java.util.*

class MimeDetector(configFileName: String) {
    var prop: Properties
    init {
        val inst = FileInputStream(configFileName)
        val p    = Properties()
        try {
            p.load(inst)
        } catch(e: IOException) {
            println("Failed to load mime config")
        }
        this.prop = p
    }
    fun getMime(path: Path): String {
        val fileName = path.getFileName().toString()
        val ext      = fileName.substring(fileName.indexOf(".") + 1)
        return prop.getProperty(ext, "")
    }
}
