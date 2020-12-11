package com.twelve

import java.io.BufferedReader
import java.io.Reader

class Recognizer constructor(private val reader: BufferedReader) {

    init {
        extractTokens()
    }


    private fun extractTokens() {
        var state = 0
        var i = reader.read()

        while (i != -1) {
            val ch = i.toChar()
            i = reader.read()
            when (state) {
                0 -> {
                    when {
                        ch == 'A' -> {
                            state = 8
                        }
                        ch == 'H' -> {
                            state = 23
                        }
                        ch == 'S' -> {
                            state = 16
                        }
                        ch == '&' -> {
                            state = 1
                        }
                        ch == '\"' -> {
                            state = 21
                        }
                        ch == 'a' -> {
                            state = 27
                        }
                        ch == 't' -> {
                            state = 37
                        }
                        ch == 'B' -> {
                            state = 33
                        }
                        ch == 'J' -> {
                            state = 51
                        }
                        ch == 'K' -> {
                            state = 54
                        }
                        ch == 'Z' -> {
                            state = 57
                        }
                        ch == 'T' -> {
                            state = 67
                        }
                        ch.isDigit() -> {
                            state = 67
                        }
                        ch.isLetter() -> {
                            state = 70
                        }
                        ch == 'Y' -> {
                            state = 71
                        }
                    }
                }
            }

        }
    }
}