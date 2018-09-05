package LeetCode.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class RemoveInvalidParenthesesTest {
    private String input;
    RemoveInvalidParentheses removeInvalidParentheses;

    public RemoveInvalidParenthesesTest(String input) {
        this.input = input;
    }

    @Before
    public void setup() {
        removeInvalidParentheses = new RemoveInvalidParentheses();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA = "()())()";
        return Arrays.asList(new Object[][] {
                {caseA}
        });
    }

    @Test
    public void SolutionTest() {
        List<String> res = removeInvalidParentheses.removeInvalidParentheses(input);
        for(String s : res) {
            System.out.println(s);
        }
    }
}
