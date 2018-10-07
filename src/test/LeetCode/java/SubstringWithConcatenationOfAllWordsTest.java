package LeetCode.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class SubstringWithConcatenationOfAllWordsTest {
    private String input1;
    private String[] input2;
    private SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords;

    public SubstringWithConcatenationOfAllWordsTest(String input1, String[] input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    @Before
    public void setup() {
        substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA1 = "barfoothefoobarman";
        String[] caseA2 = {"foo","bar"};
        String caseB1 = "wordgoodgoodgoodbestword";
        String[] caseB2 ={"word","good","best","good"};
        return Arrays.asList(new Object[][]{
                {caseA1, caseA2},
                {caseB1, caseB2}
        });
    }

    @Test
    public void SolutionTest() {
        System.out.println("input String: " + input1);
        System.out.println("input Words:  " + Arrays.deepToString(input2));
        List<Integer> res = substringWithConcatenationOfAllWords.findSubstring(input1, input2);
        System.out.println("result: " + res.toString());
    }
}
