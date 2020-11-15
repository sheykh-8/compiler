package com.twelve;

public class Word extends Token{
	public String str;
	public Word(String terminal, String str) {
		super(terminal);
		this.str = str;
	}

}
