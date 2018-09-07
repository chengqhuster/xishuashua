package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LongestValidParenthesesTest {
    private String input;
    private int output;
    private LongestValidParentheses longestValidParentheses;

    public LongestValidParenthesesTest(String input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        longestValidParentheses = new LongestValidParentheses();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA = "())";
        int resA = 2;
        String caseB = ")((()())()(()))()))";
        int resB = 16;
        return Arrays.asList(new Object[][] {
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void SolutionTest() {
        int res = longestValidParentheses.longestValidParentheses(input);
        System.out.println("input  :" + input);
        System.out.println("result :" + res);
        Assert.assertEquals(output, res);
    }
}
