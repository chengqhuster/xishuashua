package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LongestSubstringWithoutRepeatingCharactersTest {
    private String input;
    private int output;
    private LongestSubstringWithoutRepeatingCharacters longestSubstring;

    public LongestSubstringWithoutRepeatingCharactersTest(String input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        longestSubstring = new LongestSubstringWithoutRepeatingCharacters();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA = "ababcabc";
        int resA = 3;
        String caseB = "bbbbbbbbb";
        int resB = 1;
        return Arrays.asList(new Object[][]{
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void SolutionTest() {
        System.out.println("input:  " + input);
        int res = longestSubstring.lengthOfLongestSubstring(input);
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
