package com.twelve;

import java.lang.reflect.Field;

public class NonTerminal {
    public final static Integer START = 1,
            ID_OP = 2,
            SERIES_EXPRESSION = 3,
            SERIES_EXPRESSION_EPS = 4,
            SERIES_ID = 5,
            SERIES_ID_EPS = 6,
            SINGULAROP = 7,
            TYPE = 8,
            BOOLEANEXPRESSION = 9,
            IDENTIFIER = 10,
            OPB = 11,
            EXPRESSION = 12,
            TERM = 13,
            FACTOR = 14,
            E = 15,
            T = 16,
            S = 17;

    public static String intToNonTerminal(int nonTerminal) throws IllegalAccessException {
        Field[] fields = NonTerminal.class.getDeclaredFields();
        for (Field field : fields)
            if (nonTerminal == field.getInt(null))
                return field.getName();

        return "";
    }
}
