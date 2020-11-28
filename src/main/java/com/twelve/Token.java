package com.twelve;

public class Token {
	public Integer tag;
	public String scope;
	public int number;
	
	public Token(Integer tag, String scope) {
		this.tag = tag;
		this.scope = new String(scope);
	}
	
	public Token(Integer tag, int number) {
		this.tag = tag;
		this.number = number;
	}
}
