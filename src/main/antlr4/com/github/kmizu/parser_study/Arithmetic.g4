grammar Arithmetic;

@header {
  import static com.github.kmizu.parser_study.ArithmeticAst.*;
}

expression returns [Expression e]
   : additive {$e = null;}
   ;

additive returns [Expression e]
    : fake /* fill them and remove EOF */
    ;

multitive returns [Expression e]
    : fake /* fill them and remove EOF */
    ;

primary returns [Expression e]
    : fake /* fill them and remove fake */
    ;

number returns [int value]
   : n=NUMBER {$value = Integer.parseInt($n.getText());}
   ;

fake returns [Object nothing]
   : EOF
   ;

NUMBER: ('0' | [1-9] [0-9]*);
PLUS:  '+';
MINUS: '-';
STAR:  '*';
SLASH: '/';
LP:    '(';
RP:    ')';

WS  :   [ \t\n\r]+ -> skip ;
