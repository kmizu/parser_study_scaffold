grammar Digit;

digit returns [int value]
   : n=DIGIT {$value = 0;/* fill there and remove = 0;*/} EOF
   ;
