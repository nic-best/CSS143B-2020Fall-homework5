package Problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraverse {
    public static List<Integer> inorderTraversalIterative(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> nodeStack = new Stack<TreeNode<Integer>>();
        List<Integer> result = new ArrayList<>();

        if(root!=null){
            nodeStack.add(root);
        }


        //if we hit the null of a left branch, that means we must look for a right branch before we start
        //searching for a left branch again. (to avoid removing then immediately re-adding the left node to the stack)
        boolean finishedBranch = false;

        //continue statement used in while loop to check if we can keep following the branch
        while(nodeStack.size()>0){
            //if we can go left, go left
            if(nodeStack.peek().left!=null && !finishedBranch){
                nodeStack.add(nodeStack.peek().left);
                continue;
            }
            else{
                //since we got to the end of this branch, dont search for left branches in the next iteration
                finishedBranch=true;
            }

            //once we cant go left, visit the current node
            TreeNode<Integer> current = nodeStack.pop(); // save the current one
            result.add(current.val);

            //then go right
            if(current.right!=null){
                //we can start looking for non null left branches again
                finishedBranch=false;
                nodeStack.add(current.right);
            }

        }

        return result;
    }
}
