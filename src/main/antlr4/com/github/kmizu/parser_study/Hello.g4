grammar Hello;

hello returns [String value]
  : 'Hello, World!' {$value = /* fill them and remove "" */ "";}
  ;