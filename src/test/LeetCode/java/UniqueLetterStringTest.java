package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class UniqueLetterStringTest {
    private String input;
    private int output;
    private UniqueLetterString uniqueLetterString;

    public UniqueLetterStringTest(String input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        uniqueLetterString = new UniqueLetterString();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA = "ABC";
        int resA = 10;
        String caseB = "ABA";
        int resB = 8;
        String caseC = "ABACDFHJUKOLNBFRRYHSJUDGVBNSHDFTGAHJFOKSBGHSHFJKJSPALMXCBVFAREWUQIKJOLNVCDWSF" +
                "TTHNVDFJKMBCXDEGYUIOKCYGCTYFTYRXJKHVVJVYGHVJKCJGFXCTF";
        int resC = 57562;
        return Arrays.asList(new Object[][]{
                {caseA, resA},
                {caseB, resB},
                {caseC, resC}
        });
    }

    @Test
    public void solutionTest() {
        int res = uniqueLetterString.uniqueLetterString(input);
        Assert.assertEquals(output, res);
    }
}
