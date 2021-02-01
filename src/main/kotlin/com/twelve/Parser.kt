package com.twelve

import java.util.*
import kotlin.collections.ArrayList

class Parser {

    private val stack: Stack<Int> = Stack()
    private val predictTable: PredictTable = PredictTable()

    private val variablesType = TreeMap<String, Int>()
    private val types = arrayOf(Tag.INT, Tag.FLOAT, Tag.CHAR)
    private val errors = ArrayList<String>()

    init {
        stack.push(Tag.END)
        stack.push(NonTerminal.S)
    }

    fun parse(): Boolean {
        val table = SymbolTable.getInstance()
        var ctoken = table.currentToken
        var lastVariableType = 0
        var isFirstNonTerminal = true

        while (stack.isNotEmpty()) {

            /**
             * process: check if stacks top element is the same as the input
             * then:
             * pop the stack & move to next element in input
             * else:
             * pop the stack, check the predict table & if there is a rule push the items to the stack and in case of no rules it's a syntax error.
             */
            println("1" + NonTerminal.intToNonTerminal(stack.peek()))
            
            if (stack.peek() == Tag.END && ctoken.tag == Tag.END) {
                errors.forEach(::println)
                return errors.size == 0
            } else if (isTerminal(stack.peek()) || stack.peek() == Tag.END) {


                if (stack.peek() == ctoken.tag) {

                    if (types.contains(ctoken.tag))// compiler is defining a variable
                        lastVariableType = ctoken.tag

                    /** checking duplicated variable or undefined **/
                    if (ctoken.tag == Tag.ID) {
                        if (lastVariableType == 0) {
                            if (!variablesType.containsKey(ctoken.lexeme))
                                errors.add("Error : Undefined variable \" ${ctoken.lexeme} \" in line ${ctoken.lineIndex}")
                        } else {
                            if (!variablesType.containsKey(ctoken.lexeme))
                                variablesType[ctoken.lexeme] = lastVariableType
                            else
                                errors.add("Error : Duplicate variable \"${ctoken.lexeme}\" in line ${ctoken.lineIndex}")
                            lastVariableType = 0
                        }
                    }


                    stack.pop()
                    table.proceed()
                    ctoken = table.currentToken

                } else {
                    errors.add("Error : Missing ${Tag.intToTerminal(stack.peek())} in line ${ctoken.lineIndex}")
                    stack.pop()
                }
            } else {
                /**
                 * top of the stack is not a terminal.
                 */
                /**
                 * table check is an array of terminals & non-terminals. if it's empty, there is an error.
                 */
                val tableCheck = predictTable.get(stack.peek(), ctoken.tag)
                println(NonTerminal.intToNonTerminal(stack.peek()))
                println(Tag.intToTerminal(ctoken.tag))
                if (tableCheck.isEmpty()) {
                    println()
                    println("production was empty:")
                    println("${NonTerminal.intToNonTerminal(stack.peek())} ${Tag.intToTerminal(ctoken.tag)} in line ${ctoken.lineIndex}")
                    return false
                }
                if (tableCheck[PredictTable.TYPE] == PredictTable.SYNCH) {
                    var e: String
                    if (isFirstNonTerminal && stack.peek() == NonTerminal.S) {
                        isFirstNonTerminal = false

                        e = "Error : Can not start with ${Tag.intToTerminal(ctoken.tag)}\"${ctoken.lexeme}\" " +
                                "in line ${ctoken.lineIndex}"

                        table.proceed()
                        ctoken = table.currentToken
                    } else {
                        val expectation =
                            if (isTerminal(stack.peek()))
                                Tag.intToTerminal(stack.peek())
                            else
                                NonTerminal.intToNonTerminal(stack.peek())

                        e =
                            "Error : Looking for $expectation , Found ${Tag.intToTerminal(ctoken.tag)} in line ${ctoken.lineIndex}"
                        stack.pop()
                    }
                    errors.add(e)
                } else {
                    stack.pop()
                    for (i in tableCheck.reversedArray()) {
                        if (i != Tag.LANDA) {
                            stack.push(i)
                        }
                    }
                }
            }
        }
        errors.forEach(::println)
        return errors.size == 0
    }


    companion object {
        fun isTerminal(tag: Int): Boolean {
            return tag in 1000..1035 || tag == Tag.CHARACTER
        }

    }


}