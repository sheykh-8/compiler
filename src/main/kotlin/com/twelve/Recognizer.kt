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
        var readNext = true
        var ch = ' '//temp init

        while (i != -1) {
            if (readNext) {
                ch = i.toChar()
                if (ch == '\n') lineIndex++
                i = reader.read()
            } else
                readNext = true

            //println("state = $state")

            if (!charState && !stringState && ch.isWhitespace()) {
                //the last token is consumed by the dfa. check it's state, insert the token in symbol table and reset the state to 0
                if (state == 68) {
                    handle68State(sb)//insert temp valid Float
                    state = 0
                    sb = ""
                    continue
                } else if (arrayOf(2, 4, 6).contains(state)) {
                    checkToken(state, sb.substring(0, sb.length - 1))
                    sb = ""
                    state = 0
                    readNext = false
                }

                checkToken(state, sb)
                sb = ""
                state = 0
                continue
            }
            val type = checkSingleLetterTokens(ch)
            if (!charState && !stringState && type > -1) {

                when {
                    state == 68 -> {
                        handle68State(sb)//insert temp valid Float

                    }
                    state == 69 -> {
                        checkToken(state, sb)
                    }
                    arrayOf(2, 4, 6).contains(state) -> {
                        checkToken(state, sb.substring(0, sb.length - 1))
                        handleMissingSpace()
                    }
                    else -> checkToken(state, sb)
                }
                saveToken(type, ch.toString())
                state = 0
                sb = ""
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
                            state = 61
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
                            println(ch)
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
                            checkToken(2, sb + 'B')//insert temp valid Comprator
                            readNext = false
                            errors.add("Error : Invalid Comparator in line $lineIndex")

                        }
                    }
                }
                2 -> {
                    if (ch == 'M') {
                        state = 3
                    } else {
                        checkToken(state, sb + 'M')
                        handleMissingSpace()
                        sb = ""
                        state = 0
                        readNext = false
                    }
                }
                3 -> {
                    checkToken(state, sb.substring(0, sb.length - 1))
                    handleMissingSpace()
                    sb = ""
                    state = 0
                    readNext = false
                }
                4 -> {
                    if (ch == 'M') {
                        state = 5
                    } else {
                        checkToken(state, sb + 'M')
                        handleMissingSpace()
                        sb = ""
                        state = 0
                        readNext = false
                    }
                }
                5 -> {
                    checkToken(state, sb.substring(0, sb.length - 1))
                    handleMissingSpace()
                    sb = ""
                    state = 0
                    readNext = false
                }
                6 -> {
                    if (ch == 'M') {
                        state = 7
                    } else {
                        checkToken(state, sb + 'M')
                        errors.add("Error : Missing \'M\' in line $lineIndex")
                        sb = ""
                        state = 0
                        readNext = false
                    }
                }
                7 -> {
                    checkToken(state, sb.substring(0, sb.length - 1))
                    handleMissingSpace()
                    sb = ""
                    state = 0
                    readNext = false
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
                        checkToken(state, sb)// insert temp valid
                        state = 0
                        sb = ""
                        readNext = false
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
                            checkToken(state, sb)//insert temp token
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
                            checkToken(state, sb)//insert temp token
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
                            checkToken(state, sb)
                            handleMissingSpace()
                            sb = ""
                            state = 0
                            readNext = false

                        }
                    }
                }
                68 -> {
                    if (ch.isDigit()) {
                        state = 69
                    } else {
                        handle68State(sb)
                        sb = ""
                        state = 0
                        readNext = false
                    }
                }
                69 -> {
                    if (ch.isDigit()) {
                        state = 69
                    } else {
                        checkToken(state, sb.substring(0, sb.length - 1))//insert temp valid Float
                        handleMissingSpace()
                        state = 0
                        sb = ""
                        readNext = false
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
                            saveToken(Tag.CHARACTER, sb)
                            state = 0
                            charState = false
                            sb = ""
                            errors.add("Error : Character can't be empty , line : $lineIndex")
                        }
                        else -> state = 101
                    }
                }
                101 -> {

                    saveToken(Tag.CHARACTER, sb)
                    sb = ""
                    state = 0
                    if (ch == '\'') {
                        state = 0
                    } else {
                        errors.add("Error : Missing \' in line $lineIndex")
                        readNext = false
                    }

                    charState = false
                }


            }

        }
        if (state == 21)
            errors.add("Error : Missing \" and ^ in the last line")
        errors.forEach(::println)
    }

    // tokens like 0. or 416541. or 12.
    private fun handle68State(sb: String) {
        checkToken(69, sb + '0')
        errors.add("Error : Invalid Number in line $lineIndex")
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
                state = 61
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

    private fun handleMissingSpace() {
        errors.add("Error : Missing space in line $lineIndex")
    }

}