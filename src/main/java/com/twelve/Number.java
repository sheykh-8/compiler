package com.twelve;

public class Number extends Token{
	public final int value;
	public Number(int value) {
		super(Tag.NUM);
		this.value = value;
	}

}
