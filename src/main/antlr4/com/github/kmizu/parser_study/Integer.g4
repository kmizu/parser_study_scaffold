grammar Integer;

integer returns [int value]
   : n=INTEGER {$value = Integer.parseInt($n.getText());}
   ;

INTEGER : Zero | DigitFirst (DigitRest)*;
fragment Zero : '0';
fragment DigitFirst : '1' | '2' | '3'| '4' | '5' | '6'
           | '7' | '8' | '9';
fragment DigitRest: '0' | '1' | '2' | '3'| '4' | '5' | '6'
           | '7' | '8' | '9';