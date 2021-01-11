package com.twelve;

public class Token {
    public int tag; // Works as the token type
    public String lexeme; // Scope for characters
    public int lineIndex;

    // Program uses this constructor to save the token's tag and lexeme
    public Token(int tag, String lexeme, int lineIndex) {
        this.tag = tag;
        this.lexeme = lexeme;
        this.lineIndex = lineIndex;
    }

    public int getTag() {
        return tag;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return this.tag + " " + this.lexeme;
    }
}
