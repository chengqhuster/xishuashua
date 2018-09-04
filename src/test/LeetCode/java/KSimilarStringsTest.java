package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class KSimilarStringsTest {
    private String A;
    private String B;
    private int output;
    private KSimilarStrings kSimilarStrings;

    @Before
    public void setup() {
        kSimilarStrings = new KSimilarStrings();
    }

    public KSimilarStringsTest(String A, String B, int output) {
        this.A = A;
        this.B = B;
        this.output = output;
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA1 = "abc";
        String caseA2 = "cab";
        int resA = 2;
        String caseB1 = "abacadaea";
        String caseB2 = "bcdeaaaaa";
        int resB = 4;
        return Arrays.asList(new Object[][] {
                {caseA1, caseA2, resA},
                {caseB1, caseB2, resB},
        });
    }

    @Test
    public void SolutionTest() {
        int res = kSimilarStrings.kSimilarity(A, B);
        System.out.println(res);
        Assert.assertEquals(output, res);
    }
}
