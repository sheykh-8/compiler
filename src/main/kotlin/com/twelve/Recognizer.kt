package com.twelve

import java.io.Reader

class Recognizer constructor(private val reader: Reader) {

    private val errors = ArrayList<String>()

    init {
        extractTokens()
    }

    var lineIndex = 1
    // There's A problem in lineIndex , it's less than actual line index

    private fun extractTokens() {
        var state = 0
        var i = reader.read()
        var sb = ""
        var stringState = false
        var charState = false

        while (i != -1) {
            val ch = i.toChar()
            if (ch == '\n') lineIndex++
            i = reader.read()


            if (!charState && !stringState && ch.isWhitespace()) {
                //the last token is consumed by the dfa. check it's state, insert the token in symbol table and reset the state to 0
                if (state == 68) {
                    sb += '0'
                    checkToken(69, sb)
                    errors.add("Error : Invalid Number in line $lineIndex")
                    state = 0
                    sb = ""
                    continue
                }

                checkToken(state, sb)
                sb = ""
                state = 0
                continue
            }
            val type = checkSingleLetterTokens(ch)
            if (!charState && !stringState && type > -1) {

                if (state == 68) {
                    state = 0
                    sb = ""
                    errors.add("Error : Invalid Number in line $lineIndex")
                    continue
                }

                checkToken(state, sb)
                saveToken(type, ch.toString())
                sb = ""
                state = 0
                continue

            }

            sb += ch



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
                            stringState = true
                        }
                        ch == '\'' -> {
                            state = 100
                            charState = true
                        }
                        ch == 'a' -> {
                            state = 27
                        }
                        ch == 't' -> {
                            state = 31
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
                        ch == 'Y' -> {
                            state = 71
                        }
                        ch.isDigit() -> {
                            state = 67
                        }
                        ch.isLetter() -> {
                            state = 70
                        }
                        else -> {
                            state = 0
                            sb = ""
                            errors.add("Error : Invalid Character in line $lineIndex")
                        }
                    }
                }
                1 -> {
                    when (ch) {
                        'B' -> {
                            state = 2
                        }
                        'K' -> {
                            state = 4
                        }
                        'M' -> {
                            state = 6
                        }
                        else -> {
                            state = 0
                            sb = ""
                            errors.add("Error : Invalid Comparator in line $lineIndex")

                        }
                    }
                }
                2 -> {
                    if (ch == 'M') {
                        state = 3
                    } else {
                        checkToken(state, sb.substring(0, sb.length - 1))
                        val statePair = handleMissingSpace(ch)
                        sb = statePair.first
                        state = statePair.second

                    }
                }
                4 -> {
                    if (ch == 'M') {
                        state = 5
                    } else {
                        checkToken(state, sb.substring(0, sb.length - 1))
                        val statePair = handleMissingSpace(ch)
                        sb = statePair.first
                        state = statePair.second
                    }
                }
                6 -> {
                    if (ch == 'M') {
                        state = 7
                    } else {
                        checkToken(state, sb.substring(0, sb.length - 1))
                        val statePair = handleMissingSpace(ch)
                        sb = statePair.first
                        state = statePair.second

                    }
                }
                8 -> {
                    state = reservedOrId(ch, 's', 9)
                }
                9 -> {
                    state = reservedOrId(ch, 'h', 10)
                }
                10 -> {
                    state = reservedOrId(ch, 'a', 11)
                }
                11 -> {
                    state = reservedOrId(ch, 'r', 12)
                }
                12 -> {
                    state = reservedOrId(ch, 'i', 13)
                }
                13, 14, 20, 26, 30, 32, 39, 42, 50, 53, 56, 60, 66, 70, 78, 82 -> {
                    if (ch.isLetterOrDigit() || ch == '_') {
                        state = 14
                    } else {
                        state = 0
                        sb = ""
                        errors.add("Error : \'$ch\' couldn't be part of a method or variable name in line $lineIndex")
                    }
                }
                16 -> {
                    state = reservedOrId(ch, 'a', 17)
                }
                17 -> {
                    state = reservedOrId(ch, 'h', 18)
                }
                18 -> {
                    state = reservedOrId(ch, 'i', 19)
                }
                19 -> {
                    state = reservedOrId(ch, 'h', 20)
                }
                21 -> {
                    if (ch == '\"') {
                        saveToken(Tag.STRING, sb)
                        sb = ""
                        state = 0
                        stringState = false
                    } else {
                        state = 21
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
                    when {
                        ch == 'e' -> {
                            state = 34
                        }
                        ch == 'a' -> {
                            state = 43
                        }
                        ch.isLetterOrDigit() || ch == '_' -> {
                            state = 14
                        }
                        else -> {
                            state = 0
                            sb = ""
                            errors.add("Error : \'$ch\' couldn't be part of a method or variable name in line $lineIndex")
                        }
                    }
                }
                34 -> {
                    when {
                        ch == 'n' -> {
                            state = 35
                        }
                        ch == 'g' -> {
                            state = 40
                        }
                        ch.isLetterOrDigit() || ch == '_' -> {
                            state = 14
                        }
                        else -> {
                            state = 0
                            sb = ""
                            errors.add("Error : \'$ch\' couldn't be part of a method or variable name in line $lineIndex")
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
                    when {
                        ch.isDigit() -> {
                            state = 67
                        }
                        ch == '.' -> {
                            state = 68
                        }
                        else -> {
                            val statePair = handleMissingSpace(ch)
                            sb = statePair.first
                            state = statePair.second

                        }
                    }
                }
                68, 69 -> {
                    if (ch.isDigit()) {
                        state = 69
                    } else {
                        state = 0
                        sb = ""
                        errors.add("Error : Invalid Number in line $lineIndex")
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
                            0
                        }
                    }
                }
                75 -> {
                    state = reservedOrId(ch, 'a', 76)
                }
                76 -> {
                    state = reservedOrId(ch, 'l', 77)
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
                100 -> {
                    when (ch) {
                        '\'' -> {
                            state = 0
                            charState = false
                            sb = ""
                            errors.add("Error : Character can't be empty , line : $lineIndex")
                        }
                        else -> state = 101
                    }
                }
                101 -> {
                    sb = ""
                    saveToken(Tag.CHARACTER, sb)
                    if (ch == '\'') {
                        state = 0
                    } else {
                        errors.add("Error : Missing \' in line $lineIndex")
                        state = nextStateBySingleChar(ch)
                        sb += ch
                    }

                    charState = false
                }


            }

        }
        if (state == 21)
            errors.add("Error : Missing \" and ^ in the last line")
        errors.forEach(::println)
    }

    private fun reservedOrId(input: Char, check: Char, next: Int): Int {
        if (input == check) {
            return next
        } else if (input.isLetterOrDigit() || input == '_') {
            return 14
        }
        return -1
    }

    private fun checkSingleLetterTokens(ch: Char): Int {
        return when (ch) {
            '(' -> {
                Tag.OPEN_PARANTHESES
            }
            ')' -> {
                Tag.CLOSE_PARANTHESES
            }
            '^' -> {
                Tag.TERMINATOR
            }
            '{' -> {
                Tag.OPEN_CURLY_BRACES
            }
            '}' -> {
                Tag.CLOSE_CURLY_BRACES
            }
            '[' -> {
                Tag.OPEN_BRACES
            }
            ']' -> {
                Tag.CLOSE_BRACES
            }
            ',' -> {
                Tag.COMA
            }
            '=' -> Tag.ASSIGN
            else -> {
                -1
            }
        }
    }

    private fun saveToken(type: Int, lexeme: String) {
        val table = SymbolTable.getInstance()
        table.addSymbol(Token(type, lexeme, lineIndex))
    }


    private fun checkToken(state: Int, value: String) {
        val type = when (state) {
            22 -> Tag.STRING
            20 -> Tag.INT
            2 -> Tag.BIG
            3 -> Tag.BIG_EQUAL
            4 -> Tag.SMALL
            5 -> Tag.SMALL_EQUAL
            7 -> Tag.EQUAL
            13 -> Tag.FLOAT
            26 -> Tag.CHAR
            30 -> Tag.IF
            32 -> Tag.WHILE
            39 -> Tag.PRINT
            42 -> Tag.SCAN
            50 -> Tag.REMAIN
            60 -> Tag.MUL
            53 -> Tag.ADD
            56 -> Tag.SUB
            66 -> Tag.DIV
            67, 69 -> Tag.NUM
            78 -> Tag.INC
            82 -> Tag.DEC
            else -> {
                if (state in 8..12 || state == 14 || state in 23..25 || state in 16..19 || state in 27..29 ||
                    state == 31 || state in 33..38 || state in 40..41 || state in 43..49 || state in 70..77 ||
                    state in 79..81 || state in 51..52 || state in 54..55 || state in 57..59 || state in 61..65
                ) {
                    Tag.ID
                } else {
                    -1
                }
            }
        }

        if (type != -1) saveToken(type, value)

    }

    private fun nextStateBySingleChar(ch: Char): Int {
        val state: Int
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
            ch == '\'' -> {
                state = 100
            }
            ch == 'a' -> {
                state = 27
            }
            ch == 't' -> {
                state = 31
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
            ch == 'Y' -> {
                state = 71
            }
            ch.isDigit() -> {
                state = 67
            }
            ch.isLetter() -> {
                state = 70
            }
            else -> {
                state = -1
            }
        }

        return state

    }

    private fun handleMissingSpace(ch: Char): Pair<String, Int> {
        errors.add("Error : Missing space in line $lineIndex")

        val state = nextStateBySingleChar(ch)
        println("state = $state")
        println(ch)
        if (state == -1) {
            errors.add("Error : Invalid Character in line $lineIndex")
            return Pair("", 0)
        } else
            return Pair("" + ch, state)
    }

}