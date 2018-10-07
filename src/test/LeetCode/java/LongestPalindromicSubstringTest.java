package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LongestPalindromicSubstringTest {
    private String input;
    private String output;
    private LongestPalindromicSubstring longestPalindromicSubstring;

    public LongestPalindromicSubstringTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        longestPalindromicSubstring = new LongestPalindromicSubstring();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA = "babad";
        String resA = "bab";
        String caseB = "bbbbbbbbb";
        String resB = "bbbbbbbbb";
        return Arrays.asList(new Object[][]{
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void SolutionTest() {
        System.out.println("input:  " + input);
        String res = longestPalindromicSubstring.longestPalindrome(input);
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
