package com.github.kmizu.parser_study;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class HelloParserTest {
    static String hello(String input) throws Exception {
        return  new HelloParser(
                Commons.streamOf(new HelloLexer(
                        new ANTLRInputStream(new StringReader(input))
                ))
        ).hello().value;
    }
    @Test
    public void testHello() throws Exception {
        assertEquals("Hello, World!", hello("Hello, World!"));
    }
}
