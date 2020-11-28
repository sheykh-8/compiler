package com.twelve;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class LexicalAnalyzer {

	private Hashtable<Integer, Token> reservedTokens;
	
    private static final String braces = "{[]}";
    
    public LexicalAnalyzer() {
    	reservedTokens = new Hashtable<Integer, Token>();
    	
    	reserve(Tag.TRUE, new Token(Tag.TRUE, "true"));
    	reserve(Tag.FALSE, new Token(Tag.FALSE, "false"));
    	reserve(Tag.IF, new Token(Tag.IF, "agar"));
    	reserve(Tag.ELSE, new Token(Tag.ELSE, "else"));
    	reserve(Tag.WHILE, new Token(Tag.WHILE, "ta"));
    	reserve(Tag.BIG, new Token(Tag.BIG, "&B"));
    	reserve(Tag.SMALL, new Token(Tag.SMALL, "&K"));
    	reserve(Tag.EQUAL, new Token(Tag.EQUAL, "&MM"));
    	reserve(Tag.ASSIGN, new Token(Tag.ASSIGN, "="));
    	reserve(Tag.BIG_EQUAL, new Token(Tag.BIG_EQUAL, "&BM"));
    	reserve(Tag.SMALL_EQUAL, new Token(Tag.SMALL_EQUAL, "&KM"));
    	reserve(Tag.INT, new Token(Tag.INT, "Sahih"));
    	reserve(Tag.FLOAT, new Token(Tag.FLOAT, "Ashari"));
    	reserve(Tag.CHAR, new Token(Tag.CHAR, "Harf"));
    	reserve(Tag.ADD, new Token(Tag.ADD, "Jam"));
    	reserve(Tag.INC, new Token(Tag.INC, "YekiBala"));
    	reserve(Tag.SUB, new Token(Tag.SUB, "Kam"));
    	reserve(Tag.DEC, new Token(Tag.DEC, "YekiPain"));
    	reserve(Tag.MUL, new Token(Tag.MUL, "Zarb"));
    	reserve(Tag.DIV, new Token(Tag.DIV, "Tagsim"));
    	reserve(Tag.REMAIN, new Token(Tag.REMAIN, "BagiMonde"));
    }
    
    public void reserve(Integer tag, Token t) {
    	reservedTokens.put(tag, t);
    }
    
    public static ArrayList<String> scan(String line) {

        StringBuilder stringBuilder = new StringBuilder();

        cursor:
        for (int i = 0; i < line.length(); i++) {

            for (int j = 0; j < braces.length(); j++)
                if (line.charAt(i) == braces.charAt(j)) {
                    stringBuilder.append(' ');
                    stringBuilder.append(line.charAt(i));
                    stringBuilder.append(' ');
                    break cursor;
                }
            stringBuilder.append(line.charAt(i));
        }


        ArrayList<String> tokens = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(stringBuilder.toString());

        while (tokenizer.hasMoreTokens())
            tokens.add(tokenizer.nextToken());


        return tokens;
    }


}
