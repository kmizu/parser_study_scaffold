grammar Arithmetic;

line returns [ArithmeticAst.Expression e]
   : EOF
   ;

WS  :   [ \t\n\r]+ -> skip ;
