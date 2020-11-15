package com.twelve;

public class Number extends Token{
	public int value;
	public Number(String termianl, int value) {
		super(termianl);
		this.value = value;
	}

}
