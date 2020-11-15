package com.twelve;


//TODO: add syntax and compiler error.
public class Error {
    private final int code;
    private final String message;
    private final int line;
    private final String Suggestion;

    public Error(int code, String message, int line, String suggestion) {
        this.code = code;
        this.message = message;
        this.line = line;
        Suggestion = suggestion;
    }


    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", line=" + line +
                ", Suggestion='" + Suggestion + '\'' +
                '}';
    }
}
