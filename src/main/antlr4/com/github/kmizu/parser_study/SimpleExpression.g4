grammar SimpleExpression;

@header {
  import static com.github.kmizu.parser_study.SimpleExpressionAst.*;
}

expression returns [SimpleExpressionAst.Expression e]
   : l=number '+' r=number {$e = new BinaryExpression(Operator.ADD, $l.value, $r.value);}
   | l=number '-' r=number {$e = new BinaryExpression(Operator.SUBTRACT, $l.value, $r.value);}
   | l=number '*' r=number {$e = new BinaryExpression(Operator.MULTIPLY, $l.value, $r.value);}
   | l=number '/' r=number {$e = new BinaryExpression(Operator.DIVIDE, $l.value, $r.value);}
   | v=number {$e = $v.value;}
   ;

number returns [SimpleExpressionAst.NumberExpression value]
   : n=NUMBER {$value = new NumberExpression(Integer.parseInt($n.getText()));}
   ;

NUMBER: ('0' | [1-9] [0-9]*); // no leading zeros

WS  :   [ \t\n\r]+ -> skip ;
