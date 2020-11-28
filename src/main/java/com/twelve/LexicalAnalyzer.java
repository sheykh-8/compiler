package com.twelve;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class LexicalAnalyzer {

	private Hashtable<Integer, Word> reservedTokens;
	
    private static final String braces = "{[]}";
    
    public LexicalAnalyzer() {
    	reservedTokens = new Hashtable<Integer, Word>();
    	
    	reserve(Tag.TRUE, new Word(Tag.TRUE, "true"));
    	reserve(Tag.FALSE, new Word(Tag.FALSE, "false"));
    	reserve(Tag.IF, new Word(Tag.IF, "agar"));
    	reserve(Tag.ELSE, new Word(Tag.ELSE, "else"));
    	reserve(Tag.WHILE, new Word(Tag.WHILE, "ta"));
    	reserve(Tag.BIG, new Word(Tag.BIG, "&B"));
    	reserve(Tag.SMALL, new Word(Tag.SMALL, "&K"));
    	reserve(Tag.EQUAL, new Word(Tag.EQUAL, "&MM"));
    	reserve(Tag.ASSIGN, new Word(Tag.ASSIGN, "="));
    	reserve(Tag.BIG_EQUAL, new Word(Tag.BIG_EQUAL, "&BM"));
    	reserve(Tag.SMALL_EQUAL, new Word(Tag.SMALL_EQUAL, "&KM"));
    }
    
    public void reserve(Integer tag, Word t) {
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
