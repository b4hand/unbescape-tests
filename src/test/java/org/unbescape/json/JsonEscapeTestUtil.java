/*
 * =============================================================================
 * 
 *   Copyright (c) 2014, The UNBESCAPE team (http://www.unbescape.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.unbescape.json;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Assert;

/**
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @since 1.0
 *
 */
public class JsonEscapeTestUtil {







    public static void testEscapeSECUHexa1(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA,
                JsonEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }


    public static void testEscapeUHexa1(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.UHEXA,
                JsonEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }




    public static void testEscapeSECUHexa2(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA,
                JsonEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }


    public static void testEscapeUHexa2(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.UHEXA,
                JsonEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }




    public static void testEscapeSECUHexa3(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA,
                JsonEscapeLevel.LEVEL_3_ALL_NON_ALPHANUMERIC);
    }


    public static void testEscapeUHexa3(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.UHEXA,
                JsonEscapeLevel.LEVEL_3_ALL_NON_ALPHANUMERIC);
    }




    public static void testEscapeSECUHexa4(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA,
                JsonEscapeLevel.LEVEL_4_ALL_CHARACTERS);
    }


    public static void testEscapeUHexa4(final String text, final String expected)
            throws IOException {
        testEscape(text, expected, JsonEscapeType.UHEXA,
                JsonEscapeLevel.LEVEL_4_ALL_CHARACTERS);
    }







    public static void testEscape(final String text, final String expected,
                                   final JsonEscapeType type, final JsonEscapeLevel level)
                                   throws IOException {

        final String resultStr = JsonEscapeUtil.escape(text, type, level);
        Assert.assertEquals(expected, resultStr);

        final char[] textCharArray = (text == null? null : text.toCharArray());
        StringWriter stringWriter = new StringWriter();
        JsonEscapeUtil.escape(textCharArray, 0, (textCharArray == null ? 0 : textCharArray.length), stringWriter, type, level);
        if (textCharArray == null) {
            Assert.assertEquals("", stringWriter.toString());
        } else {
            Assert.assertEquals(expected,stringWriter.toString());
        }

        if (textCharArray == null) {
            return;
        }

        final char[] fill = "AAAAAAAAAA".toCharArray();
        for (int i = 0; i < 10; i++) {
            final char[] array = new char[fill.length + textCharArray.length];
            if (i > 0) {
                System.arraycopy(fill,0,array,0,i);
            }
            System.arraycopy(textCharArray,0,array,i,textCharArray.length);
            if ((i + 1) < 10) {
                System.arraycopy(fill,i,array,i + textCharArray.length,(10 - (i+1)));
            }

            stringWriter = new StringWriter();
            JsonEscapeUtil.escape(array, i, textCharArray.length, stringWriter, type, level);
            if (textCharArray == null) {
                Assert.assertEquals("", stringWriter.toString());
            } else {
                Assert.assertEquals(expected,stringWriter.toString());
            }

        }

    }






    public static void testUnescape(final String text, final String expected)
                                    throws IOException {

        final String resultStr = JsonEscape.unescapeJson(text);
        Assert.assertEquals(expected, resultStr);

        final char[] textCharArray = (text == null? null : text.toCharArray());
        StringWriter stringWriter = new StringWriter();
        JsonEscape.unescapeJson(textCharArray, 0, (textCharArray == null ? 0 : textCharArray.length), stringWriter);
        if (textCharArray == null) {
            Assert.assertEquals("", stringWriter.toString());
        } else {
            Assert.assertEquals(expected,stringWriter.toString());
        }

        if (textCharArray == null) {
            return;
        }

        final char[] fill = "AAAAAAAAAA".toCharArray();
        for (int i = 0; i < 10; i++) {
            final char[] array = new char[fill.length + textCharArray.length];
            if (i > 0) {
                System.arraycopy(fill,0,array,0,i);
            }
            System.arraycopy(textCharArray,0,array,i,textCharArray.length);
            if ((i + 1) < 10) {
                System.arraycopy(fill,i,array,i + textCharArray.length,(10 - (i+1)));
            }

            stringWriter = new StringWriter();
            JsonEscape.unescapeJson(array, i, textCharArray.length, stringWriter);
            if (textCharArray == null) {
                Assert.assertEquals("", stringWriter.toString());
            } else {
                Assert.assertEquals(expected,stringWriter.toString());
            }

        }

    }





    private JsonEscapeTestUtil() {
        super();
    }


}
