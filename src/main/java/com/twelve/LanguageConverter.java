package com.twelve;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LanguageConverter {

    private static final String C_PREFIX =
            "#include <stdio.h>\n" +
                    "\n" +
                    "int main(){\n";
    private static final String C_POSTFIX = "return 0;}";


    public LanguageConverter(String path) throws IOException {
        new File(path).mkdir();

        SymbolTable t = SymbolTable.getInstance();
        ArrayList<Token> table = t.getList();
        StringBuilder input = new StringBuilder();

        input.append(C_PREFIX);
        int index = 0;
        boolean isScanf = false;

        while (index < table.size()) {
            switch (table.get(index).tag) {
                case Tag.INT:
                    input.append(" int "); // int
                    break;
                case Tag.FLOAT:
                    input.append(" float "); // float
                    break;
                case Tag.CHAR:
                    input.append(" char "); // char
                    break;
                case Tag.PRINT:
                    input.append(" printf"); // printf
                    break;
                case Tag.SCAN:
                    input.append(" scanf"); // scanf
                    isScanf = true;
                    break;
                case Tag.IF:
                    input.append(" if"); // if
                    break;
                case Tag.WHILE:
                    input.append(" while"); // while
                    break;

                case Tag.STRING:
                case Tag.NUM:
                    input.append(" ").append(table.get(index).getLexeme()).append(" ");
                    break;
                case Tag.ID:
                    if (isScanf)
                        input.append("&");
                    input.append(table.get(index).getLexeme()).append(" ");
                    break;

                // num
                case Tag.COMA:
                    input.append(" , "); // ,

                    break;
                case Tag.BIG:
                    input.append(" >"); // >

                    break;
                case Tag.BIG_EQUAL:
                    input.append(" >="); // >=

                    break;
                case Tag.SMALL:
                    input.append(" <"); // <

                    break;
                case Tag.SMALL_EQUAL:
                    input.append(" <="); // <=

                    break;
                case Tag.EQUAL:
                    input.append(" =="); // ==

                    break;
                case Tag.ADD:
                    input.append(" +"); // +

                    break;
                case Tag.SUB:
                    input.append(" -"); // -

                    break;
                case Tag.MUL:
                    input.append(" *"); // *

                    break;
                case Tag.DIV:
                    input.append(" /"); // /

                    break;
                case Tag.REMAIN:
                    input.append(" %"); // %

                    break;
                case Tag.INC:
                    input.append("++"); // ++

                    break;
                case Tag.DEC:
                    input.append("--"); // --

                    break;
                case Tag.ASSIGN:
                    input.append("="); // =

                    break;
                case Tag.OPEN_BRACES:
                    input.append("{\n"); // {

                    break;
                case Tag.CLOSE_BRACES:
                    input.append("\n}"); // }

                    break;
                case Tag.OPEN_CURLY_BRACES:
                    input.append("("); // (

                    break;
                case Tag.CLOSE_CURLY_BRACES:
                    input.append(")"); // )

                    break;
                case Tag.OPEN_PARANTHESES:
                    input.append("("); // (

                    break;
                case Tag.CLOSE_PARANTHESES:
                    input.append(")"); // )

                    break;
                case Tag.TERMINATOR:
                    input.append(";\n"); // ;
                    isScanf = false;
                    break;
            }
            index++;
        }
        input.append(C_POSTFIX);

        FileWriter fileWriter = new FileWriter(path + "/tmp.c");
        fileWriter.write(input.toString());
        fileWriter.flush();
        fileWriter.close();

        compileAndRun(path);
    }

    private void compileAndRun(String path) throws IOException {
        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd " + path + " & gcc tmp.c & a.exe\"");
    }
}
