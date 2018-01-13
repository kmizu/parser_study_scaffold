# 構文解析ハンズオンVol.2 

[構文解析ハンズオン Vol.2](https://connpass.com/event/69373/)向けのレポジトリです。このリポジトリは、
あくまで、雛形を提供するものであり、 `src/main/antlr4/` 以下の `.g4` ファイルを正しく修正することで
初めてテストを通ります。各テストは次のようにして実行することができます。 `$` はプロンプトなので、そこを
覗いてコピー＆ペーストしてベースディレクトリで実行してください。

- Hello

```
$ mvn test -Dtest=HelloParserTest
```

- 一桁整数

```
$ mvn test -Dtest=DigitParserTest
```

- 非負整数

```
$ mvn test -Dtest=IntegerParserTest
```

- 単純な算術式

```
$ mvn test -Dtest=SimpleExpressionParserTest
```

- 算術式

```
$ mvn test -Dtest=ArithmeticParserTest
```

- JSON

```
$ mvn test -Dtest=JsonParserTest
```

なお、全てのテストを一度に実行したい場合は、

```
$ mvn test
```

とだけ打ち込んでください。誤りの修正があれば随時反映します。
