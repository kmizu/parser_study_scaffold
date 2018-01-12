grammar Hello;

hello returns [String value]
  : 'Hello, World!' {$value = "Hello, World!";}
  ;