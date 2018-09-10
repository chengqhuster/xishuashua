package ClassicProblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ReversePairsIITest {
    private int[][] input;
    private int output;
    private ReversePairsII reversePairsII;

    public ReversePairsIITest(int[][] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        reversePairsII = new ReversePairsII();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[][] caseA = new int[][]{{1,2}, {1,7}, {1,6}, {2,6}, {3,5}, {4,5}};
        int resA = 5;
        return Arrays.asList(new Object[][]{
                {caseA, resA}
        });
    }

    @Test
    public void SolutionTest() {
        for(int[] aa : input) {
            for(int a : aa) {
                System.out.print(a + "  ");
            }
            System.out.print("\n");
        }
        int res = reversePairsII.getReversePairs(input);
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
