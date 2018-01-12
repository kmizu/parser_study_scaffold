package com.github.kmizu.parser_study;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class DigitParserTest {
    static int parse(String input) throws Exception {
        return  new DigitParser(
                Commons.streamOf(new DigitLexer(
                        new ANTLRInputStream(new StringReader(input))
                ))
        ).digit().value;
    }
    @Test
    public void test0() throws Exception {
        assertEquals(0, parse("0"));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(1, parse("1"));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(2, parse("2"));
    }

    @Test
    public void test3() throws Exception {
        assertEquals(3, parse("3"));
    }

    @Test
    public void test4() throws Exception {
        assertEquals(4, parse("4"));
    }

    @Test
    public void test5() throws Exception {
        assertEquals(5, parse("5"));
    }

    @Test
    public void test6() throws Exception {
        assertEquals(6, parse("6"));
    }

    @Test
    public void test7() throws Exception {
        assertEquals(7, parse("7"));
    }

    @Test
    public void test8() throws Exception {
        assertEquals(8, parse("8"));
    }

    @Test
    public void test9() throws Exception {
        assertEquals(9, parse("9"));
    }
}
