package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LongestPalindromicSubsequenceTest {
    private String input;
    private int output;
    private LongestPalindromicSubsequence longestPalindromicSubsequence;

    public LongestPalindromicSubsequenceTest(String input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        longestPalindromicSubsequence = new LongestPalindromicSubsequence();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA = "babad";
        int resA = 3;
        String caseB = "abcdfcba";
        int resB = 7;
        return Arrays.asList(new Object[][]{
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void SolutionTest() {
        System.out.println("input:  " + input);
        int res = longestPalindromicSubsequence.longestPalindromeSubseq(input);
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
