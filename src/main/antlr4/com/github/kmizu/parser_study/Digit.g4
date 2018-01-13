grammar Digit;

digit returns [int value]
   : n=DIGIT {$value = Integer.parseInt($n.getText());} EOF
   ;

DIGIT : ' '
      ;