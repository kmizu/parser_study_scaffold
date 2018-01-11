grammar Json;

jvalue returns [JsonAst.JValue value]
   : EOF
   ;

WS  :   [ \t\n\r]+ -> skip ;
