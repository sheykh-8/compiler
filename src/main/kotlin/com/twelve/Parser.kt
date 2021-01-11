package com.twelve

import java.util.*

class Parser {

    private val stack: Stack<Int> = Stack()
    private val predictTable: PredictTable = PredictTable()

    init {
        stack.push(Tag.END)
        stack.push(NonTerminal.S)
    }

    fun parse (): Boolean {
        val table = SymbolTable.getInstance()

        var ctoken = table.currentToken

        while (true) {
            /**
             * process: check if stacks top element is the same as the input
             * then:
             * pop the stack & move to next element in input
             * else:
             * pop the stack, check the predict table & if there is a rule push the items to the stack and in case of no rules it's a syntax error.
             */
            if (stack.peek() == Tag.END && ctoken.tag == Tag.END) {
                return true
            }
            else if (isTerminal(stack.peek()) || stack.peek() == Tag.END) {
                if (stack.peek() == ctoken.tag) {
                    stack.pop()
                    print(ctoken.lexeme + " ")
                    table.proceed()
                    ctoken = table.currentToken
                } else {
                    println()
                    println("${ctoken.lineIndex} ${ctoken.lexeme}")
                    return false
                }
            } else {
                /**
                 * top of the stack is not a terminal.
                 */
                /**
                 * table check is an array of terminals & non-terminals. if it's empty, there is an error.
                 */
                val tableCheck = predictTable.get(stack.peek(), ctoken.tag)
                if (tableCheck.isEmpty()) {
                    println()
                    println("49 ${ctoken.lexeme}")
                    return false
                }
                stack.pop()
                for (i in tableCheck.reversedArray()) {
                    if (i != Tag.LANDA) {
                        stack.push(i)
                    }
                }
            }
        }
    }

    private fun isTerminal(tag: Int): Boolean {
        return tag in 1000..1035
    }
}