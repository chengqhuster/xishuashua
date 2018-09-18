package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class MergeKSortedListsTest {
    private ListNode[] input;
    private MergeKSortedLists mergeKSortedLists;

    public MergeKSortedListsTest(ListNode[] input) {
        this.input = input;
    }

    @Before
    public void setup() {
        mergeKSortedLists = new MergeKSortedLists();
    }

    @Parameterized.Parameters
    public static List getParams() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        a.next = b;
        ListNode c = new ListNode(6);
        b.next = c;
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(3);
        d.next = e;
        ListNode f = new ListNode(4);
        e.next = f;
        ListNode g = new ListNode(2);
        ListNode h = new ListNode(6);
        g.next = h;
        ListNode[] caseA = new ListNode[]{a, d, g};
        return Arrays.asList(new Object[][]{
                {caseA}
        });
    }

    @Test
    public void solutionTest() {
        for(int i=0; i<input.length; i++) {
            System.out.print("No." + (i+1) + " list: ");
            ListNode head = input[i];
            while(head != null) {
                System.out.print(head.val + "  ");
                head = head.next;
            }
            System.out.print("\n");
        }
        ListNode res = mergeKSortedLists.mergeKLists(input);
        System.out.print("result   : ");
        while(res != null) {
            System.out.print(res.val + "  ");
            res = res.next;
        }
    }
}
