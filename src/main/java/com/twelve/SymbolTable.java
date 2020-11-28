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


    private Hashtable<String, Word> table; //TODO: consider changing the structure of table

    public SymbolTable() {
        this.table = new Hashtable<>();
    }

    public static SymbolTable getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SymbolTable();
        }

        return INSTANCE;
    }

    public void addSymbol(String name, Word symbol) {
        this.table.put(name, symbol);
    }


    public Word lookup (String name) {
        return this.table.get(name);
    }

}
