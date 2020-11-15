package com.twelve;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class LexicalAnalyzer {


    private static final String braces = "{[]}";

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
