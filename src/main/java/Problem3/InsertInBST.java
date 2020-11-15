package Problem3;

import Problem1.TreeNode;

public class InsertInBST {
    //recursive solution
    public static void insert(TreeNode<Integer> root, int valToInsert) {
        //if our tree is empty, we cant insert into this tree because we don't have access to the data structure that
        //hosts the root node (we would need access to that object so we can reassign its root node)
        if(root==null){
            return;
        }
        //Check to see if the val we want to insert is greater than (to the right of) the current node
        if(valToInsert>root.val){
            //if there is no more right tree, then create a new node in this place
            if(root.right==null){
                root.right=new TreeNode<Integer>(valToInsert);
            }
            //if there is more right tree, recur down that tree
            else{
                insert(root.right, valToInsert);
            }
        }
        //if the node is smaller than (to the left of) the current node
        else{
            //if there is no more left tree, then create a new node in this place
            if(root.left==null){
                root.left=new TreeNode<Integer>(valToInsert);
            }
            //if there is a left tree, recur down the left tree
            else{
                insert(root.left, valToInsert);
            }
        }
    }
}
