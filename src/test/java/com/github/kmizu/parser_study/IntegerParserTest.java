package com.github.kmizu.parser_study;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class IntegerParserTest {
    static int parse(String input) throws Exception {
        return  new IntegerParser(
                Commons.streamOf(new IntegerLexer(
                        new ANTLRInputStream(new StringReader(input))
                ))
        ).integer().value;
    }
    @Test
    public void testZero() throws Exception {
        assertEquals(0, parse("0"));
    }

    @Test
    public void testOne() throws Exception {
        assertEquals(1, parse("1"));
    }

    @Test
    public void testTwo() throws Exception {
        assertEquals(2, parse("2"));
    }

    @Test
    public void testMany() throws Exception {
        assertEquals(Integer.MAX_VALUE, parse("2147483647"));
    }
}
