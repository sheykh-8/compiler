package com.twelve;

public class Token {
	public Integer tag; // Works as the terminal
	public String scope; // Scope for characters
	public int number; // Scope for numbers
	
	public Token(Integer tag, String scope) {
		this.tag = tag;
		this.scope = new String(scope);
	}
	
	public Token(Integer tag, int number) {
		this.tag = tag;
		this.number = number;
	}
}
