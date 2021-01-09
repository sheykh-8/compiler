package com.twelve

class Parser {

    public fun parse () {
        val table = SymbolTable.getInstance()

        var ctoken = table.currentToken

        while (ctoken.lexeme != "$") {
            println(ctoken)
            table.proceed()
            ctoken = table.currentToken
        }
    }
}