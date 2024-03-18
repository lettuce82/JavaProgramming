import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class TestSimpleRegexMatches {


    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;

        while (matcher.find()) {
            matches++;
        }

        return matches;
    }

    @Test
    public void givenText_whenSimpleRegexMatches_thenCorrect() {

        String source = "foo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        assertTrue(matcher.find());
    }

    @Test
    public void givenText_whenSimpleRegexMatchesTwice_thenCorrect() {
        String source = "foo_foo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        assertEquals(matches, 2);
    }

    @Test
    public void givenText_whenMatchesWithDotMetach_thenCorrect() {
        int matches = runTest(".", "foo");
        assertTrue(matches > 0);
    }

    @Test
    public void givenORSet_whenmatchesAny_thenCorrest() {
        int matches = runTest("[abc]", "b");
        assertEquals(matches, 1);
    }

    @Test
    public void givenORSet_whenMathchesAllCombinations_thenCorrest() {
        int matches = runTest("[bcr]at", "bat cat rat");
        assertEquals(3, matches);
    }

    @Test
    public void givenNORSet_whenMatchesNon_thenCorrect() {
        int matches = runTest("[^abc]", "ag");
        assertTrue(matches > 0);
    }

    @Test
    public void givenUpperCaseRange_whenMatchesUpperCase_thenCorrect() {
        int matches = runTest(
        "[A-Z]", "Two Uppercase alphabets 34 overall");
        assertEquals(matches, 2);
    }

    @Test
    public void givenLowerCaseRange_whenMatchesLowerCase_thenCorrect() {
        int matches = runTest(
        "[a-z]", "Two Uppercase alphabets 34 overall");
        assertEquals(matches, 26);
    }

    @Test
    public void givenBothLowerAndUpperCaseRange_whenMatchesAllLetters_thenCorrect() {
        int matches = runTest(
        "[a-zA-Z]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 28);
    }

    @Test
    public void givenNumberRange_whenMatchesAccurately_thenCorrect() {
        int matches = runTest(
        "[1-5]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 2);
    }

    @Test
    public void givenNumberRange_whenMatchesAccurately_thenCorrect2(){
        int matches = runTest(
        "3[0-5]", "Two Uppercase alphabets 34 overall");

        assertEquals(matches, 1);
    }

    @Test
    public void givenTwoSets_whenMatchesUnion_thenCorrect() {
        int matches = runTest("[1-3[7-9]]", "123456789");

        assertEquals(matches, 6);
    }

    @Test
    public void givenDigits_whenMatches_thenCorrect() {
        int matches = runTest("\\d", "123");

        assertEquals(matches, 3);
    }

    @Test
    public void givenNonDigits_whenMatches_thenCorrect() {
        int matches = runTest("\\D", "a6c");

        assertEquals(matches, 2);
    }

    @Test
    public void givenWhiteSpace_whenMatches_thenCorrect() {
        int matches = runTest("\\s", "a c");

        assertEquals(matches, 1);
    }

    @Test
    public void givenNonWhiteSpace_whenMatches_thenCorrect() {
        int matches = runTest("\\S", "a c");

        assertEquals(matches, 2);
    }

    @Test
    public void givenWordCharacter_whenMatches_thenCorrect() {
        int matches = runTest("\\w", "hi!");

        assertEquals(matches, 2);
    }

    @Test
    public void givenNonWordCharacter_whenMatches_thenCorrect() {
        int matches = runTest("\\W", "hi!");

        assertEquals(matches, 1);
    }

    @Test
    public void givenOneOrManyQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a+", "hi");

        assertFalse(matches > 0);
    }

    @Test
    public void givenBraceQuantifierWithRange_whenMatchesLazily_thenCorrect() {
        int matches = runTest("a{2,3}?", "aaaa");

        assertEquals(matches, 2);
    }

    @Test
    public void givenCapturingGroup_whenMatchesWithBackReference_thenCorrect2() {
        int matches = runTest("(\\d\\d)(ab)\\1\\1", "12ab12ab1212");

        assertEquals(matches, 1);
    }

    @Test
    public void givenText_whenMatchesAtWordBoundary_thenCorrect() {
        int matches = runTest("\\bdog\\b", "a,dog,is friendly");

        assertTrue(matches > 0);
    }

    @Test
    public void takeInt() {
        Pattern pattern = Pattern.compile("\\b[+-]?([0-9]|[1-9][0,9]{0,9})\\b");
        Matcher matcher = pattern.matcher("+1");
        assertTrue(matcher.find());
    }

}
