package com.github.kmizu.parser_study;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static com.github.kmizu.parser_study.JsonAst.*;


@RunWith(JUnit4.class)
public class JsonParserTest {
    static JsonAst.JValue parse(String input) throws Exception {
        return JsonAst.parse(input);
    }

    @Test
    public void testObject() throws Exception {
        assertEquals(jobject(), parse("{}"));
        assertEquals(jobject(p("x", jnumber(1))), parse("{ \"x\": 1 }"));
        assertEquals(
                jobject(p("x", jnumber(1)), p("y", jnumber(2))),
                parse("{ \"x\": 1, \"y\": 2}")
        );
    }

    @Test
    public void testArray() throws Exception {
        assertEquals(jarray(), parse("[]"));
        assertEquals(jarray(jnumber(1)), parse("[1]"));
        assertEquals(jarray(jnumber(1), jnumber(2)), parse("[1, 2]"));
    }

    @Test
    public void testBoolean() throws Exception {
        assertEquals(jboolean(true), parse("true"));
        assertEquals(jboolean(false), parse("false"));
    }

    @Test
    public void testString() throws Exception {
        assertEquals(jstring(""), parse("\"\""));
        assertEquals(jstring("hello"), parse("\"hello\""));
    }

    @Test
    public void testNumber() throws Exception {
        assertEquals(jnumber(0), parse("0"));
        assertEquals(jnumber(1), parse("1"));
        assertEquals(jnumber(2), parse("2"));
        assertEquals(jnumber(10), parse("10"));
        assertEquals(jnumber(100), parse("100"));
        assertEquals(jnumber(101), parse("101"));
    }

    @Test
    public void testNull() throws Exception {
        assertEquals(jnull, parse("null"));
    }
}
