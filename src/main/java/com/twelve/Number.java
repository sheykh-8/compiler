package com.twelve;

// Class is obsolete for now
public class Number extends Token{
	public final int value;
	public Number(int value) {
		super(Tag.NUM);
		this.value = value;
	}

}
