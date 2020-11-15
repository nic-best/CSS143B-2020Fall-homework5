import Problem1.TreeNode;
import Problem3.InsertInBST;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Problem3Test {
    public static class BSTTestCase<T> {
        final TreeNode<T> tree;
        final int valueToInsert;
        final List<T> expect;

        public BSTTestCase(TreeNode<T> tree, int valueToInsert, List<T> expect) {
            this.tree = tree;
            this.valueToInsert = valueToInsert;
            this.expect = expect;
        }
    }

    @Test
    public void testInsertBST() {
        List<BSTTestCase<Integer>> testCases = getBSTTestCases();

        for (BSTTestCase<Integer> testCase : testCases) {
            InsertInBST.insert(testCase.tree, testCase.valueToInsert);
            List<Integer> actual = inOrderTraverse(testCase.tree);
            assertEquals(testCase.expect, actual);
        }
    }

    @Test
    public void testInOrderTraverse() {
        List<TreeNode<Integer>> testCases = new ArrayList<TreeNode<Integer>>();
        //testCases contains each unique test case in getBSTTestCases (prior to inserting new values)

        //testcase 0
        testCases.add(new TreeNode<>(1));

        //testcase 1
        TreeNode<Integer> root = new TreeNode<Integer>(2);
        root.left = new TreeNode<Integer>(1);
        testCases.add(root);

        //testcase 2
        root = new TreeNode<Integer>(4);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(6);
        testCases.add(root);

        //testcase 3
        root = new TreeNode<>(9);
        root.left = new TreeNode<>(5);
        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(6);
        root.right = new TreeNode<>(10);
        root.right.right = new TreeNode<>(13);
        testCases.add(root);

        //testcase 4 (not a BST)
        root = new TreeNode<>(5);
        root.left = new TreeNode<>(6);
        testCases.add(root);

        //testcase 5 (not a BST)
        root = new TreeNode<>(5);
        root.left = new TreeNode<>(6);
        root.left.left = new TreeNode<>(7);
        root.left.left.right = new TreeNode<>(8);
        testCases.add(root);


        List<List<Integer>> expected = new ArrayList<List<Integer>>();

        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(2, 4, 6));
        expected.add(Arrays.asList(3, 5, 6, 9, 10, 13));
        expected.add(Arrays.asList(6, 5));
        expected.add(Arrays.asList(7, 8, 6, 5));



        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> actual = inOrderTraverse(testCases.get(i));
            assertEquals(expected.get(i), actual);
        }
    }

    private static List<Integer> inOrderTraverse(TreeNode<Integer> node) {
        List<Integer> result = new ArrayList<>();
        inOrderTraverse(node, result);
        return result;
    }

    private static void inOrderTraverse(TreeNode<Integer> node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.left, result);
        result.add(node.val);
        inOrderTraverse(node.right, result);
    }


    private List<BSTTestCase<Integer>> getBSTTestCases() {
        List<BSTTestCase<Integer>> testCases = new ArrayList<>();

        //      1
        //     / \
        //    N   N
        testCases.add(new BSTTestCase<>(new TreeNode<>(1), 0, Arrays.asList(0, 1)));

        //      1
        //     / \
        //    N   N
        testCases.add(new BSTTestCase<>(new TreeNode<>(1), 1, Arrays.asList(1, 1)));

        //      1
        //     / \
        //    N   N
        testCases.add(new BSTTestCase<>(new TreeNode<>(1), 2, Arrays.asList(1, 2)));

        //      2
        //     / \
        //    1   N
        TreeNode<Integer> root = new TreeNode<>(2);
        root.left = new TreeNode<>(1);
        testCases.add(new BSTTestCase<>(root, 3, Arrays.asList(1, 2, 3)));

        //      2
        //     / \
        //    1   N
        root = new TreeNode<>(2);
        root.left = new TreeNode<>(1);
        testCases.add(new BSTTestCase<>(root, 3, Arrays.asList(1, 2, 3)));

        //      2
        //     / \
        //    1   N
        root = new TreeNode<>(2);
        root.left = new TreeNode<>(1);
        testCases.add(new BSTTestCase<>(root, 0, Arrays.asList(0, 1, 2)));

        //      4
        //     / \
        //    2   6
        root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(6);
        testCases.add(new BSTTestCase<>(root, 1, Arrays.asList(1, 2, 4, 6)));

        //      4
        //     / \
        //    2   6
        root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(6);
        testCases.add(new BSTTestCase<>(root, 3, Arrays.asList(2, 3, 4, 6)));

        //      4
        //     / \
        //    2   6
        root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(6);
        testCases.add(new BSTTestCase<>(root, 5, Arrays.asList(2, 4, 5, 6)));

        //      4
        //     / \
        //    2   6
        root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(6);
        testCases.add(new BSTTestCase<>(root, 7, Arrays.asList(2, 4, 6, 7)));

        //      9
        //     / \
        //    5   10
        //   / \   \
        //  3   6   13
        root = new TreeNode<>(9);
        root.left = new TreeNode<>(5);
        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(6);
        root.right = new TreeNode<>(10);
        root.right.right = new TreeNode<>(13);
        testCases.add(new BSTTestCase<>(root, 4, Arrays.asList(3, 4, 5, 6, 9, 10, 13)));
        testCases.add(new BSTTestCase<>(root, 11, Arrays.asList(3, 4, 5, 6, 9, 10, 11, 13)));
        testCases.add(new BSTTestCase<>(root, 2, Arrays.asList(2, 3, 4, 5, 6, 9, 10, 11, 13)));
        testCases.add(new BSTTestCase<>(root, 15, Arrays.asList(2, 3, 4, 5, 6, 9, 10, 11, 13, 15)));

        //      1
        //     / \
        //    N   N
        // homework
        // what problem can you see for insertInBst from this test case?
        // answer: the problem here is that the entire BST is just the .right of each preceding treenode.
        // It is still a BST, but we lose its fast search because there is a scenario (5 or greater) that we will need
        // to search the entire BST as any number 5 or greater is all the way to the right of the BST.
        //   1
        //  / \
        // N   2
        //      \
        //       3
        //        \
        //         4
        //          \
        //           5
        // discuss how you would solve it in a comment below
        // answer: The solution would be to periodically rebuild the BST starting at the middle value.
        // we could recursively rebuild each half of the bst (using a binary search like idea) by placing each half
        // of whatever part of the BST we are looking at to the left or right of the current node.
        // The new format would look like (using values 1-11 just to show the idea of balancing the two halves):
        //              6
        //          /        \
        //        3            9
        //      /   \        /    \
        //     1     5      7     11
        //      \   /        \    /
        //       2 4          8  10
        root = new TreeNode<>(1);
        testCases.add(new BSTTestCase<>(root, 2, Arrays.asList(1, 2)));
        testCases.add(new BSTTestCase<>(root, 3, Arrays.asList(1, 2, 3)));
        testCases.add(new BSTTestCase<>(root, 4, Arrays.asList(1, 2, 3, 4)));
        testCases.add(new BSTTestCase<>(root, 5, Arrays.asList(1, 2, 3, 4, 5)));

        return testCases;
    }
}