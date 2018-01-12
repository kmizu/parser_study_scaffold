package com.github.kmizu.parser_study;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

public class Commons {
    public static CommonTokenStream streamOf(Lexer lexer) {
        return new CommonTokenStream(lexer);
    }
}
