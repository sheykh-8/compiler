package com.twelve;

public class Number extends Token{
	public int value;
	public Number(int value) {
		super(Tag.NUM);
		this.value = value;
	}

}
