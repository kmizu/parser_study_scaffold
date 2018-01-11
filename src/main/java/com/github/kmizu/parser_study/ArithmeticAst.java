package com.github.kmizu.parser_study;

public class ArithmeticAst {
    public interface Visitor<C, R> {
        R visitBinaryExpression(BinaryExpression expression, C context);
        R visitUnaryExpression(UnaryExpression expression, C context);
        R visitIntegerLiteral(IntegerLiteral expression, C context);
    }

    public static abstract class Expression {
        public abstract <C, R> R accept(Visitor<C, R> visitor, C context);
    }

    public static class UnaryExpression extends Expression {
        public final UnaryOperator operator;
        public final Expression body;

        public UnaryExpression(UnaryOperator operator, Expression body) {
            this.operator = operator;
            this.body = body;
        }

        @Override
        public <C, R> R accept(Visitor<C, R> visitor, C context) {
            return visitor.visitUnaryExpression(this, context);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof UnaryExpression)) return false;
            UnaryExpression e = (UnaryExpression)obj;
            return    this.operator == e.operator
                   &&  this.body.equals(e.body);
        }
    }

    public static class BinaryExpression  extends Expression {
        public final Operator operator;
        public final Expression lhs, rhs;

        public BinaryExpression(Operator operator, Expression lhs, Expression rhs) {
            this.operator = operator;
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override  public <C, R> R accept(Visitor<C, R> visitor, C context) {
            return visitor.visitBinaryExpression(this, context);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof BinaryExpression)) return false;
            BinaryExpression e = (BinaryExpression)obj;
            return    this.operator == e.operator
                    && this.lhs.equals(e.lhs)
                    && this.rhs.equals(e.rhs);
        }
    }

    public static class IntegerLiteral extends Expression {
        public final int value;

        public IntegerLiteral(int value) {
            this.value = value;
        }

        @Override  public <C, R> R accept(Visitor<C, R> visitor, C context) {
            return visitor.visitIntegerLiteral(this, context);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof IntegerLiteral)) return false;
            IntegerLiteral e = (IntegerLiteral)obj;
            return this.value == e.value;
        }
    }

    public static class Evaluator implements Visitor<Void, Integer> {
        @Override
        public Integer visitBinaryExpression(BinaryExpression expression, Void context) {
            switch (expression.operator) {
                case ADD:
                    return   expression.lhs.accept(this, null)
                            + expression.rhs.accept(this, null);
                case SUBTRACT:
                    return   expression.lhs.accept(this, null)
                            - expression.rhs.accept(this, null);
                case MULTIPLY:
                    return   expression.lhs.accept(this, null)
                            * expression.rhs.accept(this, null);
                case DIVIDE:
                    return   expression.lhs.accept(this, null)
                            / expression.rhs.accept(this, null);
                default:
                    throw new RuntimeException("cannot reach here");
            }
        }

        @Override
        public Integer visitUnaryExpression(UnaryExpression expression, Void context) {
            switch (expression.operator) {
                case PLUS:
                    return expression.body.accept(this, null);
                case MINUS:
                    return -expression.body.accept(this, null);
                default:
                    throw new RuntimeException("cannot reach here");
            }
        }

        @Override
        public Integer visitIntegerLiteral(IntegerLiteral expression, Void context) {
            return expression.value;
        }
        public int evaluate(String input) throws Exception {
            ArithmeticAst.Expression e = new ArithmeticParser(ArithmeticLexerCommons.streamOf(input)).line().e;
            return e.accept(this, null);
        }
    }
}
