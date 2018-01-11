package com.github.kmizu.parser_study;

public enum Operator {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");
    public final String op;
    Operator(String op) {
        this.op = op;
    }
}