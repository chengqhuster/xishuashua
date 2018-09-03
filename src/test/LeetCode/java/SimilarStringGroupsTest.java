package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class SimilarStringGroupsTest {
    private String[] input;
    private int output;
    private SimilarStringGroups similarStringGroups;

    public SimilarStringGroupsTest(String[] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        similarStringGroups = new SimilarStringGroups();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String[] caseA = new String[]{"tars","rats","arts","star"};
        int resA = 2;
        String[] caseB = new String[]{"aaaa","aaaa","aaaa","aaaa"};
        int resB = 1;
        return Arrays.asList(new Object[][] {
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void solutionTest() {
        int res = similarStringGroups.numSimilarGroups(input);
        System.out.println("input:  " + Arrays.toString(input));
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
