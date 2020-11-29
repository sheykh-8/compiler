package com.twelve;

import java.util.ArrayList;

public class ReservedKeywords {
//	private Hashtable<Integer, Token> reservedTokens;
	private ArrayList<Token> reservedTokens;
	private Token tempToken;

	public ReservedKeywords() {
		reservedTokens = new ArrayList<Token>();

		reserve(new Token(Tag.TRUE, "true"));
		reserve(new Token(Tag.FALSE, "false"));
		reserve(new Token(Tag.IF, "agar"));
		reserve(new Token(Tag.ELSE, "else"));
		reserve(new Token(Tag.WHILE, "ta"));
		reserve(new Token(Tag.BIG, "&B"));
		reserve(new Token(Tag.SMALL, "&K"));
		reserve(new Token(Tag.EQUAL, "&MM"));
		reserve(new Token(Tag.ASSIGN, "="));
		reserve(new Token(Tag.BIG_EQUAL, "&BM"));
		reserve(new Token(Tag.SMALL_EQUAL, "&KM"));
		reserve(new Token(Tag.INT, "Sahih"));
		reserve(new Token(Tag.FLOAT, "Ashari"));
		reserve(new Token(Tag.CHAR, "Harf"));
		reserve(new Token(Tag.ADD, "Jam"));
		reserve(new Token(Tag.INC, "YekiBala"));
		reserve(new Token(Tag.SUB, "Kam"));
		reserve(new Token(Tag.DEC, "YekiPain"));
		reserve(new Token(Tag.MUL, "Zarb"));
		reserve(new Token(Tag.DIV, "Tagsim"));
		reserve(new Token(Tag.REMAIN, "BagiMonde"));

//    	reserve(Tag.TRUE, new Token(Tag.TRUE, "true"));
//    	reserve(Tag.FALSE, new Token(Tag.FALSE, "false"));
//    	reserve(Tag.IF, new Token(Tag.IF, "agar"));
//    	reserve(Tag.ELSE, new Token(Tag.ELSE, "else"));
//    	reserve(Tag.WHILE, new Token(Tag.WHILE, "ta"));
//    	reserve(Tag.BIG, new Token(Tag.BIG, "&B"));
//    	reserve(Tag.SMALL, new Token(Tag.SMALL, "&K"));
//    	reserve(Tag.EQUAL, new Token(Tag.EQUAL, "&MM"));
//    	reserve(Tag.ASSIGN, new Token(Tag.ASSIGN, "="));
//    	reserve(Tag.BIG_EQUAL, new Token(Tag.BIG_EQUAL, "&BM"));
//    	reserve(Tag.SMALL_EQUAL, new Token(Tag.SMALL_EQUAL, "&KM"));
//    	reserve(Tag.INT, new Token(Tag.INT, "Sahih"));
//    	reserve(Tag.FLOAT, new Token(Tag.FLOAT, "Ashari"));
//    	reserve(Tag.CHAR, new Token(Tag.CHAR, "Harf"));
//    	reserve(Tag.ADD, new Token(Tag.ADD, "Jam"));
//    	reserve(Tag.INC, new Token(Tag.INC, "YekiBala"));
//    	reserve(Tag.SUB, new Token(Tag.SUB, "Kam"));
//    	reserve(Tag.DEC, new Token(Tag.DEC, "YekiPain"));
//    	reserve(Tag.MUL, new Token(Tag.MUL, "Zarb"));
//    	reserve(Tag.DIV, new Token(Tag.DIV, "Tagsim"));
//    	reserve(Tag.REMAIN, new Token(Tag.REMAIN, "BagiMonde"));
	}

	public void reserve(Token t) {
		reservedTokens.add(t);
	}
	
	public void lookup(String scope) {
		if (tempToken.getScope() != null) {
			
		}
	}
}
