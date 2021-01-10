package com.twelve;

import java.util.ArrayList;

public class SymbolTable {

	private static SymbolTable INSTANCE;

	private final ArrayList<Token> table; // TODO: consider changing the structure of table

	private int inputIndex;

	private SymbolTable() {
		this.table = new ArrayList<>();
		inputIndex = 0;
	}

	public static SymbolTable getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SymbolTable();
		}

		return INSTANCE;
	}

	public void addSymbol(Token token) {
		System.out.println(token);
		table.removeIf(t -> t.lexeme.equals("$"));
		this.table.add(token);
		table.add(new Token(Tag.END, "$"));
	}

	public ArrayList<Token> getList() {
		return this.table;
	}


	/**
	 * this method is supposed to return the token that inputIndex is pointing at. for parsing purposes
	 * @return Token
	 */
	public Token getCurrentToken () {
		return this.table.get(inputIndex);
	}

	/**
	 * proceed the inputIndex to point at the next Token
	 * @return latest index
	 */
	public int proceed () {
		return ++inputIndex;
	}

	public int getTableSize () {
		return this.table.size();
	}

	public void print() {
		table.forEach(System.out::println);
	}

}
