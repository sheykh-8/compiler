package com.twelve;

public class Word extends Token{
	public final String str;
	public Word(String tag,String str) {
		super(tag);
		this.str = new String(str);
	}

}
