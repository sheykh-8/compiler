package com.twelve;

import java.util.ArrayList;

public class ReservedKeywords {
    //	private Hashtable<Integer, Token> reservedTokens;
    private ArrayList<Token> reservedTokens;
    private int index = 0;

    public ReservedKeywords() {
        reservedTokens = new ArrayList<Token>();
        int lineIndex = -1;
        reserve(new Token(Tag.TRUE, "true", lineIndex));
        reserve(new Token(Tag.FALSE, "false", lineIndex));
        reserve(new Token(Tag.IF, "agar", lineIndex));
        reserve(new Token(Tag.ELSE, "else", lineIndex));
        reserve(new Token(Tag.WHILE, "ta", lineIndex));
        reserve(new Token(Tag.BIG, "&B", lineIndex));
        reserve(new Token(Tag.SMALL, "&K", lineIndex));
        reserve(new Token(Tag.EQUAL, "&MM", lineIndex));
        reserve(new Token(Tag.ASSIGN, "=", lineIndex));
        reserve(new Token(Tag.BIG_EQUAL, "&BM", lineIndex));
        reserve(new Token(Tag.SMALL_EQUAL, "&KM", lineIndex));
        reserve(new Token(Tag.INT, "Sahih", lineIndex));
        reserve(new Token(Tag.FLOAT, "Ashari", lineIndex));
        reserve(new Token(Tag.CHAR, "Harf", lineIndex));
        reserve(new Token(Tag.ADD, "Jam", lineIndex));
        reserve(new Token(Tag.INC, "YekiBala", lineIndex));
        reserve(new Token(Tag.SUB, "Kam", lineIndex));
        reserve(new Token(Tag.DEC, "YekiPain", lineIndex));
        reserve(new Token(Tag.MUL, "Zarb", lineIndex));
        reserve(new Token(Tag.DIV, "Tagsim", lineIndex));
        reserve(new Token(Tag.REMAIN, "BagiMonde", lineIndex));
        reserve(new Token(Tag.OPEN_PARANTHESES, "(", lineIndex));
        reserve(new Token(Tag.CLOSE_PARANTHESES, ")", lineIndex));
        reserve(new Token(Tag.OPEN_BRACES, "[", lineIndex));
        reserve(new Token(Tag.CLOSE_BRACES, "]", lineIndex));
        reserve(new Token(Tag.TERMINATOR, "^", lineIndex));
        reserve(new Token(Tag.QUOTATIONS, "\"", lineIndex));
        reserve(new Token(Tag.COMA, ",", lineIndex));
        reserve(new Token(Tag.SCAN, "Begir", lineIndex));
        reserve(new Token(Tag.PRINT, "Benevis", lineIndex));

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

    public boolean isKeyword(String scope) {
        while (reservedTokens.get(index) != null) {
            if (scope.equals(reservedTokens.get(index).getLexeme())) {
                return true;
            }
        }
        return false;
    }
}
