grammar Digit;

digit returns [int value]
   : n=DIGIT {$value = Integer.parseInt($n.getText());} EOF
   ;

DIGIT : '0' | '1' | '2' | '3' | '4' | '5'
      | '6' | '7' | '8' | '9'
      ;
