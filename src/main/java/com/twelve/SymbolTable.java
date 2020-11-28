package com.twelve;

import java.util.Hashtable;


public class SymbolTable {

//    public class Word {
//        private final String type;
//        private final String scope;
//
//        public Word(String type, String scope) {
//            this.type = type;
//            this.scope = scope;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public String getScope() {
//            return scope;
//        }
//    }

    private static SymbolTable INSTANCE;


    private Hashtable<Integer, Token> table; //TODO: consider changing the structure of table

    public SymbolTable() {
        this.table = new Hashtable<>();
    }

    public static SymbolTable getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SymbolTable();
        }

        return INSTANCE;
    }

    public void addSymbol(Integer tag, Token scope) {
        this.table.put(tag, scope);
    }
    

    public Token lookup (Integer tag) {
        return this.table.get(tag);
    }

}
