grammar Integer;

integer returns [int value]
   : n=INTEGER {$value = Integer.parseInt($n.getText());}
   ;

INTEGER : ' ' ;
fragment Zero : ' ';
fragment DigitFirst : ' ';
fragment DigitRest: ' ';
