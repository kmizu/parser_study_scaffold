grammar Json;

@header {
  import static com.github.kmizu.parser_study.JsonAst.*;
}

jvalue returns [JValue value]
   : v1=jstring  {$value = $v1.value;}
   | v2=jnumber  {$value = $v2.value;}
   | v3=jobject  {$value = $v3.value;}
   | v4=jarray   {$value = $v4.value;}
   | v5=jboolean {$value = $v5.value;}
   | v6=jnull    {$value = $v6.value;}
   ;

jarray returns [JArray value]
   @init {
     List<JValue> elements = new ArrayList<JsonAst.JValue>();
   }
   : EOF /* fill them and remove EOF */
   ;

jobject returns [JObject value]
   @init {
     List<JPair<String, JValue>> fields = new ArrayList<JPair<String, JValue>>();
   }
   : EOF /* fill them and remove EOF */
   ;

pair returns [JPair value]
   : EOF /* fill them and remove EOF */
   ;

jstring returns [JString value]
   : EOF /* fill them and remove EOF */
   ;

jnull returns [JNull value]
   : EOF /* fill them and remove EOF */
   ;

jnumber returns [JNumber value]
   : EOF
   ;

jboolean returns [JBoolean value]
   : EOF
   ;

NUMBER
   : ('-')? ('0' | DIGIT19 DIGIT*)
   ;

NULL
   : 'null'
   ;

TRUE
   : 'true'
   ;

FALSE
   : 'false'
   ;

STRING
   : '"' (ESC | ~ ["\\])* '"'
   ;

LBRACKET
   : '['
   ;

COMMA
   : ','
   ;

RBRACKET
   : ']'
   ;

LBRACE
   : '{'
   ;

RBRACE
   : '}'
   ;

LP
   : '('
   ;

RP
   :')'
   ;

COLON
   : ':'
   ;

WS  :   [ \t\n\r]+ -> skip ;

fragment ESC : '\\' (["\\/bfnrt]);
fragment DIGIT: [0-9];
fragment DIGIT19: [1-9];