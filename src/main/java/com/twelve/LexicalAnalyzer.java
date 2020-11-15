package com.twelve;

public class LexicalAnalyzer {
	char characterWatch;
	
	public void scan(StringBuilder str) {
		int i = 0;
		
		while (i < str.length()) {
			characterWatch = str.charAt(i);
			if (characterWatch == ' ') {
				str.deleteCharAt(i);
			}
			i++;
		}
		System.out.println(str);
	}
	
}
