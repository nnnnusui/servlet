package com.github.nnnnusui.servlet

import java.nio.file.Files
import java.nio.file.Path

class FileClassLoader: ClassLoader(){
    fun load(path: Path): Class<*> {
        val data = Files.readAllBytes(path)
        return defineClass(null, data, 0, data.size)
    }
}