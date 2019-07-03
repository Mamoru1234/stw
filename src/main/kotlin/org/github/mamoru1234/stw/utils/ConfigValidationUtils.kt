package org.github.mamoru1234.stw.utils

import org.github.mamoru1234.stw.ext.convertToFile

fun validateDir(value: String): Boolean {
    val file = value.convertToFile()
    return file.exists() && file.isDirectory
}

fun validateFile(value: String): Boolean {
    val file = value.convertToFile()
    return file.exists() && file.isFile
}

fun nonEmpty(value: String): Boolean {
    return value.isNotEmpty()
}
