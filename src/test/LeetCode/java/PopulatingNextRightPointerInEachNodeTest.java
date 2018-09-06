package LeetCode.java;

import LeetCode.java.DataStruct.TreeLinkNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class PopulatingNextRightPointerInEachNodeTest {
    private TreeLinkNode input;
    private PopulatingNextRightPointerInEachNode populatingNextRightPointerInEachNode;

    @Before
    public void setup() {
        populatingNextRightPointerInEachNode = new PopulatingNextRightPointerInEachNode();
    }

    public PopulatingNextRightPointerInEachNodeTest(TreeLinkNode input) {
        this.input = input;
    }

    @Parameterized.Parameters
    public static List getParams() {
        TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2);
        TreeLinkNode node3 = new TreeLinkNode(3);
        node1.left = node2;
        node1.right = node3;
        return Arrays.asList(new Object[][] {
                {node1}
        });
    }

    @Test
    public void SolutionTest() {
        printTree(input);
        populatingNextRightPointerInEachNode.connect(input);
        System.out.println("**********");
        printTree(input);
    }

    private void printTree(TreeLinkNode node) {
        if(node.next != null) {
            System.out.println(node.val + " -----> " + node.next.val);
        } else {
            System.out.println(node.val + " -----> #");
        }
        if(node.left != null)
            printTree(node.left);
        if(node.right != null)
            printTree(node.right);
    }
}
