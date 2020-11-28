package com.twelve;

import java.util.Hashtable;


public class SymbolTable {

    public class Symbol {
        private final String type;
        private final String scope;

        public Symbol(String type, String scope) {
            this.type = type;
            this.scope = scope;
        }

        public String getType() {
            return type;
        }

        public String getScope() {
            return scope;
        }
    }

    private static SymbolTable INSTANCE;


    private Hashtable<String, Symbol> table; //TODO: consider changing the structure of table

    public SymbolTable() {
        this.table = new Hashtable<>();
    }

    public static SymbolTable getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SymbolTable();
        }

        return INSTANCE;
    }

    public void addSymbol(String name, Symbol symbol) {
        this.table.put(name, symbol);
    }


    public Symbol lookup (String name) {
        return this.table.get(name);
    }

}
