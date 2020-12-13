package com.twelve;

public class Token {
	public Integer tag; // Works as the token type
	public String lexeme; // Scope for characters
	
	// Program uses this constructor to save the token's tag and lexeme
	public Token(Integer tag, String lexeme) {
		this.tag = tag;
		this.lexeme = new String(lexeme);
	}

	public Integer getTag() {
		return tag;
	}

	public String getLexeme() {
		return lexeme;
	}
}
