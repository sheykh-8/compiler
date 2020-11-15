package com.twelve;

public class Word extends Token{
	public String str;
	public Word(String str) {
		super(Tag.ID);
		this.str = str;
	}

}
