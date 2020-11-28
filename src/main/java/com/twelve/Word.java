package com.twelve;

public class Word extends Token {

    public final String str;
    
    public Word(Integer tag, String str) {
        super(tag);
        this.str = new String(str);
    }
}
