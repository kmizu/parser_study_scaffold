grammar Digit;

digit returns [int value]
   : n=DIGIT {$value = 0;/* fill them and remove = 0;*/} EOF
   ;
