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
   : '['
        (v=jvalue {elements.add($v.value);} (',' v=jvalue {elements.add($v.value);})*)?
     ']' {$value = new JsonAst.JArray(elements);}
   ;

jobject returns [JObject value]
   @init {
     List<JPair<String, JValue>> fields = new ArrayList<JPair<String, JValue>>();
   }
   : '{'
        (
          p=pair {fields.add($p.value);} (',' p=pair {fields.add($p.value);})*
        )?
     '}' {$value = new JsonAst.JObject(fields);}
   ;

pair returns [JPair value]
  : k=jstring ':' v=jvalue {$value = new JPair<String, JValue>($k.value.value, $v.value);}
  ;

jstring returns [JString value]
   : s=STRING {$value = new JString($s.getText().substring(1, $s.getText().length() - 1));}
   ;

jnull returns [JNull value]
   : v=NULL {$value = JNull.instance;}
   ;

jnumber returns [JNumber value]
   : n=NUMBER {$value = new JNumber(Integer.parseInt($n.getText()));}
   ;

jboolean returns [JBoolean value]
   : TRUE  {$value = new JsonAst.JBoolean(true);}
   | FALSE {$value = new JsonAst.JBoolean(false);}
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