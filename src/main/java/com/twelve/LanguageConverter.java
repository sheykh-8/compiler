package com.twelve;

import java.util.ArrayList;

public class LanguageConverter {
	SymbolTable t;
	ArrayList<Token> table;
	StringBuilder input;
	
	public LanguageConverter() {
		String strTmp = System.getProperty("java.io.tmpdir");
		t = SymbolTable.getInstance();
		table = t.getList();
		input = new StringBuilder();
		int index = 0;
		
		while (index < t.getTableSize()) {
			switch (table.get(index).tag) {
			
			case Tag.INT:
				input.append(" int"); // int
				index++;
				break;
			case Tag.FLOAT:
				input.append(" float"); // float
				index++;
				break;
			case Tag.CHAR:
				input.append(" char"); // char
				index++;
				break;
			case Tag.PRINT:
				input.append(" printf"); // printf
				index++;
				break;
			case Tag.SCAN:
				input.append(" scanf"); // scanf
				index++;
				break;
			case Tag.IF:
				input.append(" if");
				index++;
				break;
			case Tag.WHILE:
				input.append(" while");
				index++;
				break;
			case Tag.BIG:
				input.append(" >"); // >
				index++;
				break;
			case Tag.BIG_EQUAL:
				input.append(" >="); // >=
				index++;
				break;
			case Tag.SMALL:
				input.append(" <"); // <
				index++;
				break;
			case Tag.SMALL_EQUAL:
				input.append(" <="); // <=
				index++;
				break;
			case Tag.EQUAL:
				input.append(" =="); // ==
				index++;
				break;
			case Tag.ADD:
				input.append(" +"); // +
				index++;
				break;
			case Tag.SUB:
				input.append(" -"); // -
				index++;
				break;
			case Tag.MUL:
				input.append(" *"); // *
				index++;
				break;
			case Tag.DIV:
				input.append(" /"); // /
				index++;
				break;
			case Tag.REMAIN:
				input.append(" %");
				index++;
				break;
			case Tag.INC:
				input.append(" ++"); // ++
				index++;
				break;
			case Tag.DEC:
				input.append(" --"); // --
				index++;
				break;
			case Tag.ASSIGN:
				input.append(" ="); // =
				index++;
				break;
			case Tag.OPEN_BRACES:
				input.append(" [");
				index++;
				break;
			case Tag.CLOSE_BRACES:
				input.append(" ]");
				index++;
				break;
			}
		}
	}
}
