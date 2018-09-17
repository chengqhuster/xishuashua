package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ReverseNodesInkGroupTest {
    private ListNode input;
    private int k;
    private ReverseNodesInkGroup reverseNodesInkGroup;

    public ReverseNodesInkGroupTest(ListNode input, int k) {
        this.input = input;
        this.k = k;
    }

    @Before
    public void setup() {
        reverseNodesInkGroup = new ReverseNodesInkGroup();
    }

    @Parameterized.Parameters
    public static List getParams() {
        ListNode caseA = new ListNode(1);
        ListNode b = new ListNode(2);
        caseA.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        ListNode d = new ListNode(4);
        c.next = d;
        ListNode e = new ListNode(5);
        d.next = e;
        int kA = 2;
        int kB = 3;
        int kC = 4;
        return Arrays.asList(new Object[][]{
                {caseA, kA},
                {caseA, kB},
                {caseA, kC},
        });
    }

    @Test
    public void solutionTest() {
        System.out.print(" input list:");
        ListNode temp = input;
        while(temp != null) {
            System.out.print("  " + temp.val);
            temp = temp.next;
        }
        System.out.print("\n");
        ListNode res = reverseNodesInkGroup.reverseKGroup(input, k);
        System.out.print("output list:");
        temp = res;
        while(temp != null) {
            System.out.print("  " + temp.val);
            temp = temp.next;
        }
        System.out.print("\n");
    }
}
