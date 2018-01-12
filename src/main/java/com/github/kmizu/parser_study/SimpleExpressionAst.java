package com.github.kmizu.parser_study;

import org.antlr.v4.runtime.ANTLRInputStream;

import java.io.StringReader;

public class SimpleExpressionAst {
    public static abstract class Expression {
        public abstract <R> R accept(Visitor<R> visitor);
    }

    public interface Visitor<R> {
        R visitBinaryExpression(BinaryExpression expression);
        R visitNumberExpression(NumberExpression expression);
    }

    public enum Operator {
        ADD("+"), SUBTRACT("+"), MULTIPLY("*"), DIVIDE("/");
        public final String op;
        Operator(String op) {
            this.op = op;
        }
    }

    public static class BinaryExpression extends Expression {
        public final Operator op;
        public final NumberExpression lhs, rhs;

        public BinaryExpression(Operator op, NumberExpression lhs, NumberExpression rhs) {
            this.op = op;
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpression(this);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof BinaryExpression)) return false;
            BinaryExpression that = (BinaryExpression)obj;
            if(op != that.op) return false;
            return lhs.equals(that.lhs) && rhs.equals(that.rhs);
        }
    }

    public static class NumberExpression extends Expression {
        public final int value;
        public NumberExpression(int value) {
            this.value = value;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitNumberExpression(this);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof NumberExpression)) return false;
            NumberExpression that = (NumberExpression) obj;
            return value != that.value;
        }
    }

    public static class Evaluator implements Visitor<Integer> {
        @Override
        public Integer visitBinaryExpression(BinaryExpression expression) {
            switch (expression.op) {
                case ADD:
                    return   expression.lhs.accept(this)
                            + expression.rhs.accept(this);
                case SUBTRACT:
                    return   expression.lhs.accept(this)
                            - expression.rhs.accept(this);
                case MULTIPLY:
                    return   expression.lhs.accept(this)
                            * expression.rhs.accept(this);
                case DIVIDE:
                    return   expression.lhs.accept(this)
                            / expression.rhs.accept(this);
                default:
                    throw new RuntimeException("cannot reach here");
            }
        }

        @Override
        public Integer visitNumberExpression(NumberExpression expression) {
            return expression.value;
        }

        public int evaluate(String input) throws Exception {
            Expression e = new SimpleExpressionParser(
                    Commons.streamOf(new SimpleExpressionLexer(
                            new ANTLRInputStream(new StringReader(input))
                    ))
            ).expression().e;
            return e.accept(this);
        }
    }
}
