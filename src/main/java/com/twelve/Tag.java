package com.twelve;

import java.lang.reflect.Field;

public class Tag {
    public final static int NUM = 1000, ID = 1001, TRUE = 1002, FALSE = 1003, IF = 1004, ELSE = 1005, WHILE = 1006,
            BIG = 1007, SMALL = 1008, EQUAL = 1009, ASSIGN = 1010, BIG_EQUAL = 1011, SMALL_EQUAL = 1012, INT = 1013,
            FLOAT = 1014, CHAR = 1015, ADD = 1016, INC = 1017, SUB = 1018, DEC = 1019, MUL = 1020, DIV = 1021,
            REMAIN = 1022, OPEN_PARANTHESES = 1023, CLOSE_PARANTHESES = 1024, OPEN_BRACES = 1025, CLOSE_BRACES = 1026,
            TERMINATOR = 1027, QUOTATIONS = 1028, COMA = 1029, SCAN = 1030, PRINT = 1031, STRING = 1032,
            OPEN_CURLY_BRACES = 1033, CLOSE_CURLY_BRACES = 1034, END = 1035, LANDA = 1036, CHARACTER = 1037;

    public static String intToTerminal(int terminal) throws IllegalAccessException {
        Field[] fields = Tag.class.getDeclaredFields();
        for (Field field : fields)
            if (terminal == field.getInt(null))
                return field.getName();


        return "";
    }
}
