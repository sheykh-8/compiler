package com.twelve;

import java.util.ArrayList;

public class ErrorHandler {

    private static ErrorHandler instance;

    private ArrayList<Error> errors;


    private ErrorHandler () {
        this.errors = new ArrayList<>();
    }

    public static ErrorHandler getInstance() {
        if(instance == null) instance = new ErrorHandler();

        return instance;
    }

    public void addError(Error e) {
        this.errors.add(e);
    }

    public boolean hasError () {
        return this.errors.size() != 0;
    }

    @Override
    public String toString() {
        return "ErrorHandler{" +
                "errors=" + errors +
                '}';
    }
}