package com.hoho.upbitapihelper.util

import java.nio.charset.Charset

internal object FileUtil {

    /**
     * Finds a resource with the given name.
     * And reads the entire content as a String the specified charset (default is UTF-8).
     *
     * @param fileName
     * @param charset default is UTF-8
     */
    @JvmOverloads
    fun readResource(fileName: String, charset: Charset = Charsets.UTF_8): String =
        javaClass.classLoader.getResource(fileName)!!.readText(charset)
}