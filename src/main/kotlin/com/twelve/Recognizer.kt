package com.twelve

import java.io.BufferedReader
import java.io.Reader

class Recognizer constructor(private val reader: Reader) {

    init {
        extractTokens()
    }


    private fun extractTokens() {
        var state = 0
        var i = reader.read()

        while (i != -1) {
            val ch = i.toChar()

            if (ch.isWhitespace()) {
                //the last token is consumed by the dfa. check it's state, insert the token in symbol table and reset the state to 0
                checkToken(state)

                state = 0
            }


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
                        else -> {
                            state = -1
                        }
                    }
                }
                1 -> {
                    state = when (ch) {
                        'B' -> {
                            2
                        }
                        'K' -> {
                            4
                        }
                        'M' -> {
                            6
                        }
                        else -> {
                            -1
                        }
                    }
                }
                2 -> {
                    state = if (ch == 'M') {
                        3
                    } else {
                        -1
                    }
                }
                4 -> {
                    state = if (ch == 'M') {
                        5
                    } else {
                        -1
                    }
                }
                6 -> {
                    state = if (ch == 'M') {
                        7
                    } else {
                        -1
                    }
                }
                8 -> {
                    state = reservedOrId(ch, 's', 8)
                }
                9 -> {
                    state = reservedOrId(ch, 'h', 9)
                }
                10 -> {
                    state = reservedOrId(ch, 'a', 10)
                }
                11 -> {
                    state = reservedOrId(ch, 'r', 11)
                }
                12 -> {
                    state = reservedOrId(ch, 'i', 12)
                }
                13, 14, 20, 26, 30, 32, 39, 42, 50, 53, 56, 60, 66, 70, 78, 82 -> {
                    state = if (ch.isLetterOrDigit() || ch == '_') {
                        14
                    } else {
                        -1
                    }
                }
                16 -> {
                    state = reservedOrId(ch, 'a', 16)
                }
                17 -> {
                    state = reservedOrId(ch, 'h', 17)
                }
                18 -> {
                    state = reservedOrId(ch, 'i', 18)
                }
                19 -> {
                    state = reservedOrId(ch, 'h', 19)
                }
                21 -> {
                    state = if (ch == '\"') {
                        22
                    } else {
                        21
                    }
                }
                23 -> {
                    state = reservedOrId(ch, 'a', 24)
                }
                24 -> {
                    state = reservedOrId(ch, 'r', 25)
                }
                25 -> {
                    state = reservedOrId(ch, 'f', 26)
                }
                27 -> {
                    state = reservedOrId(ch, 'g', 28)
                }
                28 -> {
                    state = reservedOrId(ch, 'a', 29)
                }
                29 -> {
                    state = reservedOrId(ch, 'r', 30)
                }
                31 -> {
                    state = reservedOrId(ch, 'a', 32)
                }
                33 -> {
                    state = when {
                        ch == 'e' -> {
                            34
                        }
                        ch == 'a' -> {
                            43
                        }
                        ch.isLetterOrDigit() || ch == '_' -> {
                            14
                        }
                        else -> {
                            -1
                        }
                    }
                }
                34 -> {
                    state = when {
                        ch == 'n' -> {
                            35
                        }
                        ch == 'g' -> {
                            40
                        }
                        ch.isLetterOrDigit() || ch == '_' -> {
                            14
                        }
                        else -> {
                            -1
                        }
                    }
                }
                35 -> {
                    state = reservedOrId(ch, 'e', 36)
                }
                36 -> {
                    state = reservedOrId(ch, 'v', 37)
                }
                37 -> {
                    state = reservedOrId(ch, 'i', 38)
                }
                38 -> {
                    state = reservedOrId(ch, 's', 39)
                }
                40 -> {
                    state = reservedOrId(ch, 'i', 41)
                }
                41 -> {
                    state = reservedOrId(ch, 'r', 42)
                }
                43 -> {
                    state = reservedOrId(ch, 'g', 44)
                }
                44 -> {
                    state = reservedOrId(ch, 'i', 45)
                }
                45 -> {
                    state = reservedOrId(ch, 'm', 46)
                }
                46 -> {
                    state = reservedOrId(ch, 'o', 47)
                }
                47 -> {
                    state = reservedOrId(ch, 'n', 48)
                }
                48 -> {
                    state = reservedOrId(ch, 'd', 49)
                }
                49 -> {
                    state = reservedOrId(ch, 'e', 50)
                }
                51 -> {
                    state = reservedOrId(ch, 'a', 52)
                }
                52 -> {
                    state = reservedOrId(ch, 'm', 53)
                }
                54 -> {
                    state = reservedOrId(ch, 'a', 55)
                }
                55 -> {
                    state = reservedOrId(ch, 'm', 56)
                }
                57 -> {
                    state = reservedOrId(ch, 'a', 58)
                }
                58 -> {
                    state = reservedOrId(ch, 'r', 59)
                }
                59 -> {
                    state = reservedOrId(ch, 'b', 60)
                }
                61 -> {
                    state = reservedOrId(ch, 'a', 62)
                }
                62 -> {
                    state = reservedOrId(ch, 'g', 63)
                }
                63 -> {
                    state = reservedOrId(ch, 's', 64)
                }
                64 -> {
                    state = reservedOrId(ch, 'i', 65)
                }
                65 -> {
                    state = reservedOrId(ch, 'm', 66)
                }
                67 -> {
                    state = when {
                        ch.isDigit() -> {
                            67
                        }
                        ch == '.' -> {
                            68
                        }
                        else -> {
                            -1
                        }
                    }
                }
                68, 69 -> {
                    state = if (ch.isDigit()) {
                        69
                    } else {
                        -1
                    }
                }
                71 -> {
                    state = reservedOrId(ch, 'e', 72)
                }
                72 -> {
                    state = reservedOrId(ch, 'k', 73)
                }
                73 -> {
                    state = reservedOrId(ch, 'i', 74)
                }
                74 -> {
                    state = when (ch) {
                        'B' -> {
                            75
                        }
                        'P' -> {
                            79
                        }
                        else -> {
                            -1
                        }
                    }
                }
                75 -> {
                    state = reservedOrId(ch, 'a', 76)
                }
                76 -> {
                    state = reservedOrId(ch, 'e', 77)
                }
                77 -> {
                    state = reservedOrId(ch, 'a', 78)
                }
                79 -> {
                    state = reservedOrId(ch, 'a', 80)
                }
                80 -> {
                    state = reservedOrId(ch, 'i', 81)
                }
                81 -> {
                    state = reservedOrId(ch, 'n', 82)
                }

            }

        }
    }

    private fun reservedOrId(input: Char, check: Char, next: Int): Int {
        if (input == check) {
            return next
        } else if (input.isLetterOrDigit() || input == '_') {
            return 14
        }
        return -1
    }

    private fun checkToken(state: Int) {
        //TODO: finish this function. recognize the tokens based on the value of state and insert the value in the symbol table

        val table = SymbolTable.getInstance()
//        table.addSymbol()
    }
}