package com.twelve;

public class Token {
	public Integer tag; // Works as the terminal
	public String scope; // Scope for characters
	public int number; // Scope for numbers
	
	// Program uses this constructor if the token is characters
	public Token(Integer tag, String scope) {
		this.tag = tag;
		this.scope = new String(scope);
	}
	
	// Program uses this constructor if the token is number
	public Token(Integer tag, int number) {
		this.tag = tag;
		this.number = number;
	}

	public Integer getTag() {
		return tag;
	}

	public String getScope() {
		return scope;
	}
}
