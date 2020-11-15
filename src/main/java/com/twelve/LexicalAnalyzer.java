package com.twelve;

import java.util.Hashtable;

public class LexicalAnalyzer {
	char characterWatch;
	Hashtable<String, Word> words;
	
	public LexicalAnalyzer() {
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}
	
	public void reserve(Word w) {
		words.put(w.str, w);
	}
	
//	public void scan(StringBuilder str) {
//		int i = 0;
//		
//		while (i < str.length()) {
//			characterWatch = str.charAt(i);
//			if (characterWatch == ' ' || characterWatch == '\t') {
//				str.deleteCharAt(i);
//			}
//			i++;
//		}
//		System.out.println(str);
//	}
	
}
