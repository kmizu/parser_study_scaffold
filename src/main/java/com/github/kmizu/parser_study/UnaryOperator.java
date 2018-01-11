package com.github.kmizu.parser_study;

public enum UnaryOperator {
    PLUS("+"), MINUS("-");
    public final String op;
    UnaryOperator(String op) {
        this.op = op;
    }
}
