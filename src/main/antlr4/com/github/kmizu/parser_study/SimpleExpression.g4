grammar SimpleExpression;

@header {
  import static com.github.kmizu.parser_study.SimpleExpressionAst.*;
}

expression returns [SimpleExpressionAst.Expression e]
   : EOF /* fill there and remove EOF */
   ;

number returns [SimpleExpressionAst.NumberExpression value]
   : n=NUMBER {$value = new NumberExpression(Integer.parseInt($n.getText()));}
   ;

NUMBER: ('0' | [1-9] [0-9]*); // no leading zeros

WS  :   [ \t\n\r]+ -> skip ;
