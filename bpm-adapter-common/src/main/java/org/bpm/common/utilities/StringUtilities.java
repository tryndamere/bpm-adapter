package org.bpm.common.utilities;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 字符串工具
 */
public abstract class StringUtilities {

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static boolean isBlank(CharSequence str) {
        return !hasText(str);
    }

    /**
     * <pre>
     * StringUtilities.hasText(null) = false
     * StringUtilities.hasText("") = false
     * StringUtilities.hasText(" ") = false
     * StringUtilities.hasText("12345") = true
     * StringUtilities.hasText(" 12345 ") = true
     * </pre>
     */
    public static boolean hasText(CharSequence str) {
        if (isEmpty(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * StringUtilities.isEmpty(null)      = true
     * StringUtilities.isEmpty("")        = true
     * StringUtilities.isEmpty(" ")       = false
     * StringUtilities.isEmpty("bob")     = false
     * StringUtilities.isEmpty("  bob  ") = false
     * </pre>
     */
    public static boolean isEmpty(CharSequence str) {
        return !hasLength(str);
    }

    /**
     * <pre>
     * StringUtilities.hasLength(null) = false
     * StringUtilities.hasLength("") = false
     * StringUtilities.hasLength(" ") = true
     * StringUtilities.hasLength("Hello") = true
     * </pre>
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    // equal
    /**
     * <pre>
     * StringUtilities.equals(null, null)   = true
     * StringUtilities.equals(null, "abc")  = false
     * StringUtilities.equals("abc", null)  = false
     * StringUtilities.equals("abc", "abc") = true
     * StringUtilities.equals("abc", "ABC") = false
     * </pre>
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * <pre>
     * StringUtilities.equalsIgnoreCase(null, null)   = true
     * StringUtilities.equalsIgnoreCase(null, "abc")  = false
     * StringUtilities.equalsIgnoreCase("abc", null)  = false
     * StringUtilities.equalsIgnoreCase("abc", "abc") = true
     * StringUtilities.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    // trim
    /**
     * <pre>
     * StringUtilities.trim(null)          = null
     * StringUtilities.trim("")            = ""
     * StringUtilities.trim("     ")       = ""
     * StringUtilities.trim("abc")         = "abc"
     * StringUtilities.trim("    abc    ") = "abc"
     * </pre>
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * <pre>
     * StringUtilities.trimToNull(null)          = null
     * StringUtilities.trimToNull("")            = null
     * StringUtilities.trimToNull("     ")       = null
     * StringUtilities.trimToNull("abc")         = "abc"
     * StringUtilities.trimToNull("    abc    ") = "abc"
     * </pre>
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return hasLength(ts) ? ts : null;
    }

    /**
     * <pre>
     * StringUtilities.trimToEmpty(null)          = ""
     * StringUtilities.trimToEmpty("")            = ""
     * StringUtilities.trimToEmpty("     ")       = ""
     * StringUtilities.trimToEmpty("abc")         = "abc"
     * StringUtilities.trimToEmpty("    abc    ") = "abc"
     * </pre>
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        String ts = trim(str);
        return hasLength(ts) ? ts : EMPTY;
    }

    /**
     * <pre>
     * StringUtilities.trimAllWhitespace(null)          = null
     * StringUtilities.trimAllWhitespace("")            = ""
     * StringUtilities.trimAllWhitespace("     ")       = ""
     * StringUtilities.trimAllWhitespace("abc")         = "abc"
     * StringUtilities.trimAllWhitespace("    abc    ") = "abc"
     * StringUtilities.trimAllWhitespace("    ab c    ") = "abc"
     * </pre>
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return sb.toString();
    }

    // Splitting
    /**
     * <pre>
     * StringUtilities.split(null)       = null
     * StringUtilities.split("")         = []
     * StringUtilities.split("abc def")  = ["abc", "def"]
     * StringUtilities.split("abc  def") = ["abc", "def"]
     * StringUtilities.split(" abc ")    = ["abc"]
     * </pre>
     */
    public static String[] split(String str) {
        return split(str, null, -1);
    }

    /**
     * <pre>
     * StringUtilities.split(null, *)         = null
     * StringUtilities.split("", *)           = []
     * StringUtilities.split("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtilities.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringUtilities.split("a:b:c", '.')    = ["a:b:c"]
     * StringUtilities.split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     */
    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    /**
     * <pre>
     * StringUtilities.split(null, *)         = null
     * StringUtilities.split("", *)           = []
     * StringUtilities.split("abc def", null) = ["abc", "def"]
     * StringUtilities.split("abc def", " ")  = ["abc", "def"]
     * StringUtilities.split("abc  def", " ") = ["abc", "def"]
     * StringUtilities.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     */
    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    /**
     * <pre>
     * StringUtilities.split(null, *, *)            = null
     * StringUtilities.split("", *, *)              = []
     * StringUtilities.split("ab de fg", null, 0)   = ["ab", "cd", "ef"]
     * StringUtilities.split("ab   de fg", null, 0) = ["ab", "cd", "ef"]
     * StringUtilities.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
     * StringUtilities.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
     * </pre>
     */
    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    /**
     * <pre>
     * StringUtilities.splitByWholeSeparator(null, *)               = null
     * StringUtilities.splitByWholeSeparator("", *)                 = []
     * StringUtilities.splitByWholeSeparator("ab de fg", null)      = ["ab", "de", "fg"]
     * StringUtilities.splitByWholeSeparator("ab   de fg", null)    = ["ab", "de", "fg"]
     * StringUtilities.splitByWholeSeparator("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
     * StringUtilities.splitByWholeSeparator("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
     * </pre>
     */
    public static String[] splitByWholeSeparator(String str, String separator) {
        return splitByWholeSeparatorWorker(str, separator, -1, false);
    }

    /**
     * <pre>
     * StringUtilities.splitByWholeSeparator(null, *, *)               = null
     * StringUtilities.splitByWholeSeparator("", *, *)                 = []
     * StringUtilities.splitByWholeSeparator("ab de fg", null, 0)      = ["ab", "de", "fg"]
     * StringUtilities.splitByWholeSeparator("ab   de fg", null, 0)    = ["ab", "de", "fg"]
     * StringUtilities.splitByWholeSeparator("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
     * StringUtilities.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
     * StringUtilities.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
     * </pre>
     */
    public static String[] splitByWholeSeparator(String str, String separator, int max) {
        return splitByWholeSeparatorWorker(str, separator, max, false);
    }

    /**
     * <pre>
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens(null, *)               = null
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("", *)                 = []
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab de fg", null)      = ["ab", "de", "fg"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null)    = ["ab", "", "", "de", "fg"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
     * </pre>
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
        return splitByWholeSeparatorWorker(str, separator, -1, true);
    }

    /**
     * <pre>
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens(null, *, *)               = null
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("", *, *)                 = []
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab de fg", null, 0)      = ["ab", "de", "fg"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null, 0)    = ["ab", "", "", "de", "fg"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
     * StringUtilities.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
     * </pre>
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
        return splitByWholeSeparatorWorker(str, separator, max, true);
    }

    private static String[] splitByWholeSeparatorWorker(String str, String separator, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }

        int len = str.length();
        if (len == 0) {
            return ArrayUtilities.EMPTY_STRING_ARRAY;
        }
        if ((separator == null) || (EMPTY.equals(separator))) {
            return splitWorker(str, null, max, preserveAllTokens);
        }
        int separatorLength = separator.length();

        List<String> substrings = new ArrayList<String>();
        int numberOfSubstrings = 0;
        int beg = 0;
        int end = 0;
        while (end < len) {
            end = str.indexOf(separator, beg);

            if (end > -1) {
                if (end > beg) {
                    numberOfSubstrings += 1;

                    if (numberOfSubstrings == max) {
                        end = len;
                        substrings.add(str.substring(beg));
                    } else {
                        substrings.add(str.substring(beg, end));
                        beg = end + separatorLength;
                    }
                } else {
                    if (preserveAllTokens) {
                        numberOfSubstrings += 1;
                        if (numberOfSubstrings == max) {
                            end = len;
                            substrings.add(str.substring(beg));
                        } else {
                            substrings.add(EMPTY);
                        }
                    }
                    beg = end + separatorLength;
                }
            } else {
                // String.substring( beg ) goes from 'beg' to the end of the
                // String.
                substrings.add(str.substring(beg));
                end = len;
            }
        }

        return (String[]) substrings.toArray(new String[substrings.size()]);
    }

    /**
     * <pre>
     * StringUtilities.splitPreserveAllTokens(null)       = null
     * StringUtilities.splitPreserveAllTokens("")         = []
     * StringUtilities.splitPreserveAllTokens("abc def")  = ["abc", "def"]
     * StringUtilities.splitPreserveAllTokens("abc  def") = ["abc", "", "def"]
     * StringUtilities.splitPreserveAllTokens(" abc ")    = ["", "abc", ""]
     * </pre>
     */
    public static String[] splitPreserveAllTokens(String str) {
        return splitWorker(str, null, -1, true);
    }

    /**
     * <pre>
     * StringUtilities.splitPreserveAllTokens(null, *)         = null
     * StringUtilities.splitPreserveAllTokens("", *)           = []
     * StringUtilities.splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtilities.splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
     * StringUtilities.splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
     * StringUtilities.splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
     * StringUtilities.splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
     * StringUtilities.splitPreserveAllTokens("a b c ", ' ')   = ["a", "b", "c", ""]
     * StringUtilities.splitPreserveAllTokens("a b c  ", ' ')   = ["a", "b", "c", "", ""]
     * StringUtilities.splitPreserveAllTokens(" a b c", ' ')   = ["", a", "b", "c"]
     * StringUtilities.splitPreserveAllTokens("  a b c", ' ')  = ["", "", a", "b", "c"]
     * StringUtilities.splitPreserveAllTokens(" a b c ", ' ')  = ["", a", "b", "c", ""]
     * </pre>
     */
    public static String[] splitPreserveAllTokens(String str, char separatorChar) {
        return splitWorker(str, separatorChar, true);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtilities.EMPTY_STRING_ARRAY;
        }
        List<String> list = new ArrayList<String>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * <pre>
     * StringUtilities.splitPreserveAllTokens(null, *)           = null
     * StringUtilities.splitPreserveAllTokens("", *)             = []
     * StringUtilities.splitPreserveAllTokens("abc def", null)   = ["abc", "def"]
     * StringUtilities.splitPreserveAllTokens("abc def", " ")    = ["abc", "def"]
     * StringUtilities.splitPreserveAllTokens("abc  def", " ")   = ["abc", "", def"]
     * StringUtilities.splitPreserveAllTokens("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
     * StringUtilities.splitPreserveAllTokens("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
     * StringUtilities.splitPreserveAllTokens("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
     * StringUtilities.splitPreserveAllTokens("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
     * StringUtilities.splitPreserveAllTokens(":cd:ef", ":")     = ["", cd", "ef"]
     * StringUtilities.splitPreserveAllTokens("::cd:ef", ":")    = ["", "", cd", "ef"]
     * StringUtilities.splitPreserveAllTokens(":cd:ef:", ":")    = ["", cd", "ef", ""]
     * </pre>
     */
    public static String[] splitPreserveAllTokens(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, true);
    }

    /**
     * <pre>
     * StringUtilities.splitPreserveAllTokens(null, *, *)            = null
     * StringUtilities.splitPreserveAllTokens("", *, *)              = []
     * StringUtilities.splitPreserveAllTokens("ab de fg", null, 0)   = ["ab", "cd", "ef"]
     * StringUtilities.splitPreserveAllTokens("ab   de fg", null, 0) = ["ab", "cd", "ef"]
     * StringUtilities.splitPreserveAllTokens("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
     * StringUtilities.splitPreserveAllTokens("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
     * StringUtilities.splitPreserveAllTokens("ab   de fg", null, 2) = ["ab", "  de fg"]
     * StringUtilities.splitPreserveAllTokens("ab   de fg", null, 3) = ["ab", "", " de fg"]
     * StringUtilities.splitPreserveAllTokens("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
     * </pre>
     */
    public static String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, true);
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtilities.EMPTY_STRING_ARRAY;
        }
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * <pre>
     * StringUtilities.splitByCharacterType(null)         = null
     * StringUtilities.splitByCharacterType("")           = []
     * StringUtilities.splitByCharacterType("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtilities.splitByCharacterType("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtilities.splitByCharacterType("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtilities.splitByCharacterType("number5")    = ["number", "5"]
     * StringUtilities.splitByCharacterType("fooBar")     = ["foo", "B", "ar"]
     * StringUtilities.splitByCharacterType("foo200Bar")  = ["foo", "200", "B", "ar"]
     * StringUtilities.splitByCharacterType("ASFRules")   = ["ASFR", "ules"]
     * </pre>
     */
    public static String[] splitByCharacterType(String str) {
        return splitByCharacterType(str, false);
    }

    /**
     * <pre>
     * StringUtilities.splitByCharacterTypeCamelCase(null)         = null
     * StringUtilities.splitByCharacterTypeCamelCase("")           = []
     * StringUtilities.splitByCharacterTypeCamelCase("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtilities.splitByCharacterTypeCamelCase("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtilities.splitByCharacterTypeCamelCase("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtilities.splitByCharacterTypeCamelCase("number5")    = ["number", "5"]
     * StringUtilities.splitByCharacterTypeCamelCase("fooBar")     = ["foo", "Bar"]
     * StringUtilities.splitByCharacterTypeCamelCase("foo200Bar")  = ["foo", "200", "Bar"]
     * StringUtilities.splitByCharacterTypeCamelCase("ASFRules")   = ["ASF", "Rules"]
     * </pre>
     */
    public static String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }

    private static String[] splitByCharacterType(String str, boolean camelCase) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return ArrayUtilities.EMPTY_STRING_ARRAY;
        }
        char[] c = str.toCharArray();
        List<String> list = new ArrayList<String>();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++) {
            int type = Character.getType(c[pos]);
            if (type == currentType) {
                continue;
            }
            if (camelCase && type == Character.LOWERCASE_LETTER && currentType == Character.UPPERCASE_LETTER) {
                int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart) {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            } else {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }
        list.add(new String(c, tokenStart, c.length - tokenStart));
        return list.toArray(new String[list.size()]);
    }

    // Joining
    /**
     * <pre>
     * StringUtilities.join(null)            = null
     * StringUtilities.join([])              = ""
     * StringUtilities.join([null])          = ""
     * StringUtilities.join(["a", "b", "c"]) = "abc"
     * StringUtilities.join([null, "", "a"]) = "a"
     * </pre>
     */
    public static String join(Object[] array) {
        return join(array, null);
    }

    /**
     * <pre>
     * StringUtilities.join(null, *)               = null
     * StringUtilities.join([], *)                 = ""
     * StringUtilities.join([null], *)             = ""
     * StringUtilities.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtilities.join(["a", "b", "c"], null) = "abc"
     * StringUtilities.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }

        return join(array, separator, 0, array.length);
    }

    /**
     * <pre>
     * StringUtilities.join(null, *)               = null
     * StringUtilities.join([], *)                 = ""
     * StringUtilities.join([null], *)             = ""
     * StringUtilities.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtilities.join(["a", "b", "c"], null) = "abc"
     * StringUtilities.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <pre>
     * StringUtilities.join(null, *)                = null
     * StringUtilities.join([], *)                  = ""
     * StringUtilities.join([null], *)              = ""
     * StringUtilities.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtilities.join(["a", "b", "c"], null)  = "abc"
     * StringUtilities.join(["a", "b", "c"], "")    = "abc"
     * StringUtilities.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * <pre>
     * StringUtilities.join(null, *)                = null
     * StringUtilities.join([], *)                  = ""
     * StringUtilities.join([null], *)              = ""
     * StringUtilities.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtilities.join(["a", "b", "c"], null)  = "abc"
     * StringUtilities.join(["a", "b", "c"], "")    = "abc"
     * StringUtilities.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length());
        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(Iterator<?> iterator, char separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(Collection<?> collection, char separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static String join(Collection<?> collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    // Replacing
    /**
     * <pre>
     * StringUtilities.replaceOnce(null, *, *)        = null
     * StringUtilities.replaceOnce("", *, *)          = ""
     * StringUtilities.replaceOnce("any", null, *)    = "any"
     * StringUtilities.replaceOnce("any", *, null)    = "any"
     * StringUtilities.replaceOnce("any", "", *)      = "any"
     * StringUtilities.replaceOnce("aba", "a", null)  = "aba"
     * StringUtilities.replaceOnce("aba", "a", "")    = "ba"
     * StringUtilities.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     */
    public static String replaceOnce(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, 1);
    }

    /**
     * <pre>
     * StringUtilities.replace(null, *, *)        = null
     * StringUtilities.replace("", *, *)          = ""
     * StringUtilities.replace("any", null, *)    = "any"
     * StringUtilities.replace("any", *, null)    = "any"
     * StringUtilities.replace("any", "", *)      = "any"
     * StringUtilities.replace("aba", "a", null)  = "aba"
     * StringUtilities.replace("aba", "a", "")    = "b"
     * StringUtilities.replace("aba", "a", "z")   = "zbz"
     * </pre>
     */
    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    /**
     * <pre>
     * StringUtilities.replace(null, *, *, *)         = null
     * StringUtilities.replace("", *, *, *)           = ""
     * StringUtilities.replace("any", null, *, *)     = "any"
     * StringUtilities.replace("any", *, null, *)     = "any"
     * StringUtilities.replace("any", "", *, *)       = "any"
     * StringUtilities.replace("any", *, *, 0)        = "any"
     * StringUtilities.replace("abaa", "a", null, -1) = "abaa"
     * StringUtilities.replace("abaa", "a", "", -1)   = "b"
     * StringUtilities.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtilities.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtilities.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtilities.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     */
    public static String replace(String text, String searchString, String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * <pre>
     *  StringUtilities.replaceEach(null, *, *)        = null
     *  StringUtilities.replaceEach("", *, *)          = ""
     *  StringUtilities.replaceEach("aba", null, null) = "aba"
     *  StringUtilities.replaceEach("aba", new String[0], null) = "aba"
     *  StringUtilities.replaceEach("aba", null, new String[0]) = "aba"
     *  StringUtilities.replaceEach("aba", new String[]{"a"}, null)  = "aba"
     *  StringUtilities.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
     *  StringUtilities.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
     *  (example of how it does not repeat)
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
     * </pre>
     */
    public static String replaceEach(String text, String[] searchList, String[] replacementList) {
        return replaceEach(text, searchList, replacementList, false, 0);
    }

    /**
     * <pre>
     *  StringUtilities.replaceEach(null, *, *, *) = null
     *  StringUtilities.replaceEach("", *, *, *) = ""
     *  StringUtilities.replaceEach("aba", null, null, *) = "aba"
     *  StringUtilities.replaceEach("aba", new String[0], null, *) = "aba"
     *  StringUtilities.replaceEach("aba", null, new String[0], *) = "aba"
     *  StringUtilities.replaceEach("aba", new String[]{"a"}, null, *) = "aba"
     *  StringUtilities.replaceEach("aba", new String[]{"a"}, new String[]{""}, *) = "b"
     *  StringUtilities.replaceEach("aba", new String[]{null}, new String[]{"a"}, *) = "aba"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}, *) = "wcte"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, false) = "dcte"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, true) = "tcte"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}, true) = IllegalArgumentException
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}, false) = "dcabe"
     * </pre>
     */
    public static String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
        int timeToLive = searchList == null ? 0 : searchList.length;
        return replaceEach(text, searchList, replacementList, true, timeToLive);
    }

    /**
     * <pre>
     *  StringUtilities.replaceEach(null, *, *, *) = null
     *  StringUtilities.replaceEach("", *, *, *) = ""
     *  StringUtilities.replaceEach("aba", null, null, *) = "aba"
     *  StringUtilities.replaceEach("aba", new String[0], null, *) = "aba"
     *  StringUtilities.replaceEach("aba", null, new String[0], *) = "aba"
     *  StringUtilities.replaceEach("aba", new String[]{"a"}, null, *) = "aba"
     *  StringUtilities.replaceEach("aba", new String[]{"a"}, new String[]{""}, *) = "b"
     *  StringUtilities.replaceEach("aba", new String[]{null}, new String[]{"a"}, *) = "aba"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}, *) = "wcte"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, false) = "dcte"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, true) = "tcte"
     *  StringUtilities.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}, *) = IllegalArgumentException
     * </pre>
     */
    private static String replaceEach(String text, String[] searchList, String[] replacementList, boolean repeat,
            int timeToLive) {
        if (isEmpty(text) || ArrayUtilities.isEmpty(searchList) || ArrayUtilities.isEmpty(replacementList)) {
            return text;
        }
        if (timeToLive < 0) {
            throw new IllegalStateException("TimeToLive of " + timeToLive + " is less than 0: " + text);
        }

        int searchLength = searchList.length;
        int replacementLength = replacementList.length;

        if (searchLength != replacementLength) {
            throw new IllegalArgumentException("Search and Replace array lengths don't match: " + searchLength + " vs "
                    + replacementLength);
        }

        boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];

        int textIndex = -1;
        int replaceIndex = -1;
        int tempIndex = -1;

        for (int i = 0; i < searchLength; i++) {
            if (noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length() == 0
                    || replacementList[i] == null) {
                continue;
            }
            tempIndex = text.indexOf(searchList[i]);

            if (tempIndex == -1) {
                noMoreMatchesForReplIndex[i] = true;
            } else {
                if (textIndex == -1 || tempIndex < textIndex) {
                    textIndex = tempIndex;
                    replaceIndex = i;
                }
            }
        }
        if (textIndex == -1) {
            return text;
        }
        int start = 0;
        int increase = 0;
        for (int i = 0; i < searchList.length; i++) {
            if (searchList[i] == null || replacementList[i] == null) {
                continue;
            }
            int greater = replacementList[i].length() - searchList[i].length();
            if (greater > 0) {
                increase += 3 * greater; // assume 3 matches
            }
        }
        increase = Math.min(increase, text.length() / 5);

        StringBuilder buf = new StringBuilder(text.length() + increase);

        while (textIndex != -1) {

            for (int i = start; i < textIndex; i++) {
                buf.append(text.charAt(i));
            }
            buf.append(replacementList[replaceIndex]);

            start = textIndex + searchList[replaceIndex].length();

            textIndex = -1;
            replaceIndex = -1;
            tempIndex = -1;
            for (int i = 0; i < searchLength; i++) {
                if (noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length() == 0
                        || replacementList[i] == null) {
                    continue;
                }
                tempIndex = text.indexOf(searchList[i], start);

                if (tempIndex == -1) {
                    noMoreMatchesForReplIndex[i] = true;
                } else {
                    if (textIndex == -1 || tempIndex < textIndex) {
                        textIndex = tempIndex;
                        replaceIndex = i;
                    }
                }
            }
        }
        int textLength = text.length();
        for (int i = start; i < textLength; i++) {
            buf.append(text.charAt(i));
        }
        String result = buf.toString();
        if (!repeat) {
            return result;
        }

        return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
    }

    /**
     * <pre>
     * StringUtilities.replaceChars(null, *, *)        = null
     * StringUtilities.replaceChars("", *, *)          = ""
     * StringUtilities.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtilities.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }
        return str.replace(searchChar, replaceChar);
    }

    /**
     * <pre>
     * StringUtilities.replaceChars(null, *, *)           = null
     * StringUtilities.replaceChars("", *, *)             = ""
     * StringUtilities.replaceChars("abc", null, *)       = "abc"
     * StringUtilities.replaceChars("abc", "", *)         = "abc"
     * StringUtilities.replaceChars("abc", "b", null)     = "ac"
     * StringUtilities.replaceChars("abc", "b", "")       = "ac"
     * StringUtilities.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtilities.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtilities.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if (isEmpty(str) || isEmpty(searchChars)) {
            return str;
        }
        if (replaceChars == null) {
            replaceChars = EMPTY;
        }
        boolean modified = false;
        int replaceCharsLength = replaceChars.length();
        int strLength = str.length();
        StringBuilder buf = new StringBuilder(strLength);
        for (int i = 0; i < strLength; i++) {
            char ch = str.charAt(i);
            int index = searchChars.indexOf(ch);
            if (index >= 0) {
                modified = true;
                if (index < replaceCharsLength) {
                    buf.append(replaceChars.charAt(index));
                }
            } else {
                buf.append(ch);
            }
        }
        if (modified) {
            return buf.toString();
        }
        return str;
    }

    // startsWith
    /**
     * <pre>
     * StringUtilities.startsWith(null, null)      = true
     * StringUtilities.startsWith(null, "abc")     = false
     * StringUtilities.startsWith("abcdef", null)  = false
     * StringUtilities.startsWith("abcdef", "abc") = true
     * StringUtilities.startsWith("ABCDEF", "abc") = false
     * </pre>
     */
    public static boolean startsWith(String str, String prefix) {
        return startsWith(str, prefix, false);
    }

    /**
     * <pre>
     * StringUtilities.startsWithIgnoreCase(null, null)      = true
     * StringUtilities.startsWithIgnoreCase(null, "abc")     = false
     * StringUtilities.startsWithIgnoreCase("abcdef", null)  = false
     * StringUtilities.startsWithIgnoreCase("abcdef", "abc") = true
     * StringUtilities.startsWithIgnoreCase("ABCDEF", "abc") = true
     * </pre>
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return startsWith(str, prefix, true);
    }

    private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
        if (str == null || prefix == null) {
            return (str == null && prefix == null);
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
    }

    /**
     * <pre>
     * StringUtilities.startsWithAny(null, null)      = false
     * StringUtilities.startsWithAny(null, new String[] {"abc"})  = false
     * StringUtilities.startsWithAny("abcxyz", null)     = false
     * StringUtilities.startsWithAny("abcxyz", new String[] {""}) = false
     * StringUtilities.startsWithAny("abcxyz", new String[] {"abc"}) = true
     * StringUtilities.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
     * </pre>
     */
    public static boolean startsWithAny(String string, String[] searchStrings) {
        if (isEmpty(string) || ArrayUtilities.isEmpty(searchStrings)) {
            return false;
        }
        for (int i = 0; i < searchStrings.length; i++) {
            String searchString = searchStrings[i];
            if (startsWith(string, searchString)) {
                return true;
            }
        }
        return false;
    }

    // endsWith
    /**
     * <pre>
     * StringUtilities.endsWith(null, null)      = true
     * StringUtilities.endsWith(null, "def")     = false
     * StringUtilities.endsWith("abcdef", null)  = false
     * StringUtilities.endsWith("abcdef", "def") = true
     * StringUtilities.endsWith("ABCDEF", "def") = false
     * StringUtilities.endsWith("ABCDEF", "cde") = false
     * </pre>
     */
    public static boolean endsWith(String str, String suffix) {
        return endsWith(str, suffix, false);
    }

    /**
     * <pre>
     * StringUtilities.endsWithIgnoreCase(null, null)      = true
     * StringUtilities.endsWithIgnoreCase(null, "def")     = false
     * StringUtilities.endsWithIgnoreCase("abcdef", null)  = false
     * StringUtilities.endsWithIgnoreCase("abcdef", "def") = true
     * StringUtilities.endsWithIgnoreCase("ABCDEF", "def") = true
     * StringUtilities.endsWithIgnoreCase("ABCDEF", "cde") = false
     * </pre>
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return endsWith(str, suffix, true);
    }

    private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str == null || suffix == null) {
            return (str == null && suffix == null);
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        int strOffset = str.length() - suffix.length();
        return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    /**
     * 按字节长度截取字符串(可截取完整中文)
     * 
     * <pre>
     * 功能：截取固定长度字符串，且能正确处理中文
     * </pre>
     * @param str
     * @param length
     * @return
     */
    public static String substring(String str, int length) {
        String charsetName = "Unicode";
        byte[] bytes;
        try {
            bytes = str.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unknown encoding![" + charsetName + "]", e);
        }
        int n = 0;// 表示当前的字节数
        int i = 2;// 要截取的字节数，从第3个字节开始
        for (; i < bytes.length && n < length; i++) {
            if (i % 2 == 1) {// 奇数位置，为UCS2编码中两个字节的第二个字节
                n++;// 在UCS2第二个字节时n+1
            } else {
                if (bytes[i] != 0) {// 当UCS2编码的第一个字节不等于0时，当UCS字符为汉字，一个汉字算两个字节
                    n++;
                }
            }
        }
        if (i % 2 == 1) {// 如果i为奇数时，处理成偶数
            if (bytes[i - 1] != 0) {
                i = i - 1;// 当UCS2字符是汉字时，去掉这个截取一半的汉字
            } else {
                i = i + 1;// 当UCS2字符是数字或字母时，则保留该字符
            }
        }
        String string;
        try {
            string = new String(bytes, 0, i, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unknown encoding![" + charsetName + "]", e);
        }
        return string;
    }

    /**
     * <pre>
     * StringUtilities.substringBefore(null, *)      = null
     * StringUtilities.substringBefore("", *)        = ""
     * StringUtilities.substringBefore("abc", "a")   = ""
     * StringUtilities.substringBefore("abcba", "b") = "a"
     * StringUtilities.substringBefore("abc", "c")   = "ab"
     * StringUtilities.substringBefore("abc", "d")   = "abc"
     * StringUtilities.substringBefore("abc", "")    = ""
     * StringUtilities.substringBefore("abc", null)  = "abc"
     * </pre>
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <pre>
     * StringUtilities.substringAfter(null, *)      = null
     * StringUtilities.substringAfter("", *)        = ""
     * StringUtilities.substringAfter(*, null)      = ""
     * StringUtilities.substringAfter("abc", "a")   = "bc"
     * StringUtilities.substringAfter("abcba", "b") = "cba"
     * StringUtilities.substringAfter("abc", "c")   = ""
     * StringUtilities.substringAfter("abc", "d")   = ""
     * StringUtilities.substringAfter("abc", "")    = "abc"
     * </pre>
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * <pre>
     * StringUtilities.substringBeforeLast(null, *)      = null
     * StringUtilities.substringBeforeLast("", *)        = ""
     * StringUtilities.substringBeforeLast("abcba", "b") = "abc"
     * StringUtilities.substringBeforeLast("abc", "c")   = "ab"
     * StringUtilities.substringBeforeLast("a", "a")     = ""
     * StringUtilities.substringBeforeLast("a", "z")     = "a"
     * StringUtilities.substringBeforeLast("a", null)    = "a"
     * StringUtilities.substringBeforeLast("a", "")      = "a"
     * </pre>
     */
    public static String substringBeforeLast(String str, String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <pre>
     * StringUtilities.substringAfterLast(null, *)      = null
     * StringUtilities.substringAfterLast("", *)        = ""
     * StringUtilities.substringAfterLast(*, "")        = ""
     * StringUtilities.substringAfterLast(*, null)      = ""
     * StringUtilities.substringAfterLast("abc", "a")   = "bc"
     * StringUtilities.substringAfterLast("abcba", "b") = "a"
     * StringUtilities.substringAfterLast("abc", "c")   = ""
     * StringUtilities.substringAfterLast("a", "a")     = ""
     * StringUtilities.substringAfterLast("a", "z")     = ""
     * </pre>
     */
    public static String substringAfterLast(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (isEmpty(separator)) {
            return EMPTY;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == (str.length() - separator.length())) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * <pre>
     * StringUtilities.capitalize(null)  = null
     * StringUtilities.capitalize("")    = ""
     * StringUtilities.capitalize("cat") = "Cat"
     * StringUtilities.capitalize("cAt") = "CAt"
     * </pre>
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
                .toString();
    }

    public static String capitalise(String str) {
        return capitalize(str);
    }

    /**
     * <pre>
     * StringUtilities.indexOf(null, *)          = -1
     * StringUtilities.indexOf(*, null)          = -1
     * StringUtilities.indexOf("", "")           = 0
     * StringUtilities.indexOf("", *)            = -1 (except when * = "")
     * StringUtilities.indexOf("aabaabaa", "a")  = 0
     * StringUtilities.indexOf("aabaabaa", "b")  = 2
     * StringUtilities.indexOf("aabaabaa", "ab") = 1
     * StringUtilities.indexOf("aabaabaa", "")   = 0
     * </pre>
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        return str.indexOf(searchStr);
    }

}
