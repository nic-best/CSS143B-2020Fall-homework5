package Problem1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//linked list chosen as it can have any size
public class LevelOrderTraverse {
    public static List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> nodeQueue = new LinkedList<TreeNode<Integer>>();
        List<List<Integer>> result = new ArrayList<>();
        if(root!=null){
            nodeQueue.add(root);
        }

        //outer while loop is to check when we are done with the entire tree
        while(nodeQueue.size()>0){
            List<Integer> thisResult = new ArrayList<Integer>();
            Queue<TreeNode<Integer>> tempQueue = new LinkedList<TreeNode<Integer>>();

            //inner while loop checks when we are done with the current level
            //inner while loop goes through current layer, adding all its children to a temp queue,
            // removing and saving the values of the nodes as we go across the level
            while(nodeQueue.size()>0){
                if(hasChildren(nodeQueue.peek())){
                    //add its children (if any exist) to the temporary queue
                    if(nodeQueue.peek().left!=null){
                        tempQueue.add(nodeQueue.peek().left);
                    }
                    if(nodeQueue.peek().right!=null){
                        tempQueue.add(nodeQueue.peek().right);
                    }
                }
                //saves the value of the (now removed) node to the result list
                thisResult.add(nodeQueue.remove().val);
            }
            //once all the children are added of the nodeQueue to the temporary queue,
            //start using the temporary queue as the nodeQueue
            nodeQueue = tempQueue;
            //adds the current level to the result list
            result.add(thisResult);
        }
        return result;
    }

    private static boolean hasChildren(TreeNode<Integer> node){
        return(node.left!=null || node.right!=null);
    }

}
