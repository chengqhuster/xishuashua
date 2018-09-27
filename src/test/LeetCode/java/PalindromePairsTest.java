package LeetCode.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class PalindromePairsTest {
    private String[] input;
    private PalindromePairs palindromePairs;

    public PalindromePairsTest(String[] input) {
        this.input = input;
    }

    @Before
    public void setup() {
        palindromePairs = new PalindromePairs();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String[] caseA = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
        return Arrays.asList(new Object[][] {
                {caseA}
        });
    }

    @Test
    public void solutionTest() {
        List<List<Integer>> res = palindromePairs.palindromePairs(input);
        System.out.print("input: ");
        for(String s : input) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
        for(List<Integer> pairs : res) {
            System.out.println("result pair: " + pairs.get(0) + " " +pairs.get(1));
        }
    }
}
