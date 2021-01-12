package com.twelve

class PredictTable {


    fun get (topStack: Int, input: Int): Array<Int> {
        /**
         * when S is on top of stack
         */
        if (topStack == NonTerminal.S) {
            when (input) {
                Tag.PRINT, Tag.SCAN, Tag.IF, Tag.WHILE, Tag.ID, Tag.INT, Tag.FLOAT, Tag.CHAR -> {
                    return arrayOf(NonTerminal.START)
                }
                Tag.END, Tag.CLOSE_BRACES -> {
                    return arrayOf(Tag.LANDA)
                }
            }
        }

        /**
         * when START is on top of stack
         */
        if (topStack == NonTerminal.START) {
            when (input) {
                Tag.PRINT -> {
                    return arrayOf(Tag.PRINT, Tag.OPEN_PARANTHESES, Tag.STRING, NonTerminal.SERIES_EXPRESSION, Tag.CLOSE_PARANTHESES, Tag.TERMINATOR, NonTerminal.S)
                }
                Tag.SCAN -> {
                    return arrayOf(Tag.SCAN, Tag.OPEN_PARANTHESES, Tag.STRING, NonTerminal.SERIES_ID, Tag.CLOSE_PARANTHESES, Tag.TERMINATOR, NonTerminal.S)
                }
                Tag.IF -> {
                    return arrayOf(Tag.IF, Tag.OPEN_CURLY_BRACES, NonTerminal.BOOLEANEXPRESSION, Tag.CLOSE_CURLY_BRACES, Tag.OPEN_BRACES, NonTerminal.S, Tag.CLOSE_BRACES, NonTerminal.S)
                }
                Tag.WHILE -> {
                    return arrayOf(Tag.WHILE, Tag.OPEN_CURLY_BRACES, NonTerminal.BOOLEANEXPRESSION, Tag.CLOSE_CURLY_BRACES, Tag.OPEN_BRACES, NonTerminal.S, Tag.CLOSE_BRACES, NonTerminal.S)
                }
                Tag.ID -> {
                    return arrayOf(Tag.ID, NonTerminal.ID_OP, NonTerminal.S)
                }
                Tag.INT, Tag.FLOAT -> {
                    return arrayOf(NonTerminal.TYPE, Tag.ID, Tag.ASSIGN, NonTerminal.EXPRESSION, Tag.TERMINATOR, NonTerminal.S)
                }
                Tag.CHAR -> {
                    return arrayOf(Tag.CHAR, Tag.ID, Tag.ASSIGN, Tag.CHARACTER, Tag.TERMINATOR, NonTerminal.S)
                }
            }
        }
        /**
         * when ID_OP is on top of stack
         */
        if (topStack == NonTerminal.ID_OP) {
            when (input) {
                Tag.ASSIGN -> {
                    return arrayOf(Tag.ASSIGN, NonTerminal.EXPRESSION, Tag.TERMINATOR)
                }
                Tag.INC, Tag.DEC -> {
                    return arrayOf(NonTerminal.SINGULAROP, Tag.TERMINATOR)
                }
            }
        }

        /**
         * when Series_Expression is on top of stack
         */
        if (topStack == NonTerminal.SERIES_EXPRESSION) {
            when (input) {
                Tag.COMA -> {
                    return arrayOf(Tag.COMA, NonTerminal.EXPRESSION, NonTerminal.SERIES_EXPRESSION_EPS)
                }
                Tag.CLOSE_PARANTHESES -> {
                    return arrayOf(Tag.LANDA)
                }
            }
        }
        /**
         * when series_expression_eps is on top of stack
         */
        if (topStack == NonTerminal.SERIES_EXPRESSION_EPS) {
            when (input) {
                Tag.COMA -> {
                    return arrayOf(NonTerminal.SERIES_EXPRESSION)
                }
                Tag.CLOSE_PARANTHESES -> {
                    return arrayOf(Tag.LANDA)
                }
                Tag.END -> {
                    return arrayOf(Tag.LANDA)
                }
            }
        }

        /**
         * when series_id is on top of stack
         */
        if (topStack == NonTerminal.SERIES_ID) {
            when (input) {
                Tag.COMA -> {
                    return arrayOf(Tag.COMA, Tag.ID, NonTerminal.SERIES_EXPRESSION_EPS)
                }
            }
        }

        /**
         * when series_id_eps is on top of stack
         */
        if (topStack == NonTerminal.SERIES_ID_EPS) {
            when (input) {
                Tag.COMA -> {
                    return arrayOf(NonTerminal.SERIES_ID)
                }
                Tag.CLOSE_PARANTHESES -> {
                    return arrayOf(Tag.LANDA)
                }
                Tag.END -> {
                    return arrayOf(Tag.LANDA)
                }
            }
        }

        /**
         * when singularop is on top of stack
         */
        if (topStack == NonTerminal.SINGULAROP) {
            when(input) {
                Tag.INC, Tag.DEC -> {
                    return arrayOf(input)
                }
            }
        }

        /**
         * when type is on top of stack
         */
        if (topStack == NonTerminal.TYPE) {
            when (input) {
                Tag.INT, Tag.FLOAT -> {
                    return arrayOf(input)
                }
            }
        }

        /**
         * when booleanexpression is on top of stack
         */

        if (topStack == NonTerminal.BOOLEANEXPRESSION) {
            when (input) {
                Tag.ID, Tag.NUM, Tag.OPEN_PARANTHESES -> {
                    return arrayOf(NonTerminal.EXPRESSION, NonTerminal.OPB, NonTerminal.EXPRESSION)
                }
            }
        }

        /**
         * when identifier is on top of stack
         */
        if (topStack == NonTerminal.IDENTIFIER) {
            when (input) {
                Tag.ID, Tag.NUM, Tag.CHAR -> {
                    return arrayOf(input)
                }
            }
        }

        /**
         * when opb is on top of stack
         */
        if (topStack == NonTerminal.OPB) {
            when (input)  {
                Tag.SMALL, Tag.SMALL_EQUAL, Tag.EQUAL, Tag.BIG_EQUAL, Tag.BIG -> {
                    return arrayOf(input)
                }
            }
        }

        /**
         * when expression is on top of stack
         */
        if (topStack == NonTerminal.EXPRESSION) {
            when (input) {
                Tag.ID, Tag.NUM, Tag.OPEN_PARANTHESES -> {
                    return arrayOf(NonTerminal.TERM, NonTerminal.E)
                }
            }
        }

        /**
         * when term is on top of stack
         */
        if (topStack == NonTerminal.TERM) {
            when (input) {
                Tag.ID, Tag.NUM, Tag.OPEN_PARANTHESES -> {
                    return arrayOf(NonTerminal.FACTOR, NonTerminal.T)
                }
            }
        }

        /**
         * when factor is on top of stack
         */
        if (topStack == NonTerminal.FACTOR) {
            when (input) {
                Tag.ID, Tag.NUM  -> {
                    return arrayOf(input)
                }
                Tag.OPEN_PARANTHESES -> {
                    return arrayOf(Tag.OPEN_PARANTHESES, NonTerminal.EXPRESSION, Tag.CLOSE_PARANTHESES)
                }
            }
        }

        /**
         * when e is on top of stack
         */
        if (topStack == NonTerminal.E) {
            when (input) {
                Tag.COMA, Tag.SMALL, Tag.SMALL_EQUAL, Tag.EQUAL, Tag.BIG_EQUAL, Tag.BIG, Tag.CLOSE_PARANTHESES, Tag.CLOSE_CURLY_BRACES, Tag.TERMINATOR, Tag.END -> {
                    return arrayOf(Tag.LANDA)
                }
                Tag.ADD, Tag.SUB -> {
                    return arrayOf(input, NonTerminal.TERM, NonTerminal.E)
                }
            }
        }

        /**
         * when t is on top of stack
         */

        if (topStack == NonTerminal.T) {
            when (input) {
                Tag.COMA, Tag.SMALL, Tag.SMALL_EQUAL, Tag.EQUAL, Tag.BIG_EQUAL, Tag.BIG, Tag.ADD, Tag.SUB, Tag.CLOSE_PARANTHESES, Tag.CLOSE_CURLY_BRACES, Tag.TERMINATOR, Tag.END -> {
                    return arrayOf(Tag.LANDA)
                }
                Tag.MUL, Tag.DIV, Tag.REMAIN -> {
                    return arrayOf(input, NonTerminal.FACTOR, NonTerminal.T)
                }
            }
        }

        return arrayOf()
    }
}