package Problem2;

import Problem1.TreeNode;

public class LCA {

    /*
    1. Find treeNode P in root.
    2. Check P's parent's children (use BFS) to find q, if not found, move a level higher, then check children using bfs
     */


    /*
    1. Find a treeNode
        if foundP is false
            check if it is p (if it is p here, return true)
            if not, check left (go to step 1)

            if not in left, check right (go to step 1)
                return false
        else
            if foundQ is false
                check if it is q (if it is q here, return true)
                if not check left (go to step 1)

                if not in left, check right (go to step
            else
                return true
     */

    //https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
    //same algorithm described in saturday 11/14/2020 lecture
    public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        //if this is true, it means that neither p or q are in this (completed) branch
        if(root==null){
            return null;
        }

        //if p or q is at this node, stop checking this branch and return back the node
        if(root.equals(p)||root.equals(q)){
            return root;
        }

        //if p or q isnt here, check the left and right branches of the node for p and q
        TreeNode<Integer> left = lowestCommonAncestor(root.left, p, q);
        TreeNode<Integer> right = lowestCommonAncestor(root.right, p, q);

        //if p and q and q are to the left and right of this node, then this is our lowest common ancestor
        //(left and right because we checked the left and right sides, and both gave us non null values)
        if(left!=null&&right!=null){
            return root;
        }
        //if p or q is only to the left, then a node to our left is the lowest common ancestor
        if(left!=null){
            return left;
        }

        //if p or q is only to the right, then a node to our right is the lowest common ancestor
        else{
            return right;
        }
    }
}
