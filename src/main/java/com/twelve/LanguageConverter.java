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
				input.append(" if"); // if
				index++;
				break;
			case Tag.WHILE:
				input.append(" while"); // while
				index++;
				break;
			case Tag.STRING:
				if (table.get(index - 2).getTag() == Tag.SCAN && table.get(index - 1).getTag() == Tag.OPEN_PARANTHESES
						&& table.get(index + 1).getTag() == Tag.COMA) {
					if (table.get(index).getLexeme().replaceAll("\\s+", "") == "%d"
							|| table.get(index).getLexeme().replaceAll("\\s+", "") == "%c"
							|| table.get(index).getLexeme().replaceAll("\\s+", "") == "%f") {
						input.append(" " + table.get(index).getLexeme());
						input.append(" ,&");
						index += 2;
						break;
					}
				}

				input.append(" " + table.get(index).getLexeme()); // string
				index++;
				break;
			case Tag.ID:
				input.append(" " + table.get(index).getLexeme()); // id
				index++;
				break;
			case Tag.NUM:
				input.append(" " + table.get(index).getLexeme()); // num
				index++;
				break;
			case Tag.COMA:
				input.append(" ,"); // ,
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
				input.append(" %"); // %
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
				input.append(" {"); // {
				index++;
				break;
			case Tag.CLOSE_BRACES:
				input.append(" }"); // }
				index++;
				break;
			case Tag.OPEN_CURLY_BRACES:
				input.append(" ("); // (
				index++;
				break;
			case Tag.CLOSE_CURLY_BRACES:
				input.append(" )"); // )
				index++;
				break;
			case Tag.OPEN_PARANTHESES:
				input.append(" ("); // (
				index++;
				break;
			case Tag.CLOSE_PARANTHESES:
				input.append(" )"); // )
				index++;
				break;
			case Tag.TERMINATOR:
				input.append(" ;"); // ;
				index++;
				break;
			}
		}
	}
}
