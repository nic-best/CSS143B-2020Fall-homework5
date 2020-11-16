package ExtraCredit;

import Problem1.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KDistance {


    //make a second tree that is reversible (similar idea to reverse topological sorting, but we still want to conserve tree structure)
    //for each item in the second tree, find the distance to the target
    //traverse the second tree again and save all the nodes with distance k to the list
    public static List<Integer> distanceK(TreeNode<Integer> root, TreeNode<Integer> target, int k) {
        List<Integer> result = new ArrayList<>();
        ReversibleTreeNode<Integer> reversibleRoot = createReversible(root);
        setDistances(reversibleRoot, target);
        getAllWithDistK(result, reversibleRoot, k);
        return result;
    }

    /*
    Recursive method that traverses the entire TreeNode, creates a mirror treenode with the same structure
    Sets the ancestor field in the ReversibleTreeNode to be the immediate ancestor of the new nodes. (the current node)
     */
    private static ReversibleTreeNode<Integer> createReversible(TreeNode<Integer> root){
        ReversibleTreeNode<Integer> reverseRoot = new ReversibleTreeNode<Integer>(root.val);
        if(root.left!=null){
            reverseRoot.left = createReversible(root.left);
            reverseRoot.left.ancestor = reverseRoot;
        }
        if(root.right!=null){
            reverseRoot.right = createReversible(root.right);
            reverseRoot.right.ancestor = reverseRoot;
        }
        return reverseRoot;
    }

    /*
    Recursive method that explores neighboring unvisited parts of the node (starting at any specified reversible treenode)
    It increments a distance variable until we find the target node.
    Will not backtrack because there is an unvisited list
     */
    private static int findDistToTarget(ReversibleTreeNode<Integer> node, TreeNode<Integer> target, int dist, List<Integer> visited){
        //visits the node
        visited.add(node.val);
        //if this node is the target node, return our distance
        if(node.val.equals(target.val)){
            return dist;
        }
        //if we havent visited this node yet
        if(node.left!=null && !visited.contains(node.left.val)){
            int distance = findDistToTarget(node.left, target, dist+1, visited);
            //if this branch has the target node in it, return the distance
            if(distance!=-1){
                return distance;
            }
        }
        //if we havent visited this node yet
        if(node.right!=null && !visited.contains(node.right.val)){
            int distance = findDistToTarget(node.right, target, dist+1, visited);
            //if this branch has the target node in it, return the distance
            if(distance!=-1){
                return distance;
            }
        }
        //if we havent visited this node yet
        if(node.ancestor!=null && !visited.contains(node.ancestor.val)){
            int distance = findDistToTarget(node.ancestor, target, dist+1, visited);
            //if this branch has the target node in it, return the distance
            if(distance!=-1){
                return distance;
            }
        }
        //if we cant traverse any more nodes, and we havent found the target node, then this branch doesnt have the
        //target node in it.
        return -1;
    }

    /*
    sets the distance field for all reversibletreenodes in the reversible distance tree
     */
    private static void setDistances(ReversibleTreeNode<Integer> root, TreeNode<Integer> target){
        //list that stores the visited nodes (unique list for each instance of the recursive method)
        List<Integer> visited = new ArrayList<Integer>();
        //get get the distance from the root to the target
        root.distToTarget = findDistToTarget(root, target, 0, visited);
        //recursively get the distance from all the root's children and their children (etc) to the target
        if(root.left!=null){
            setDistances(root.left, target);
        }
        if(root.right!=null){
            setDistances(root.right, target);
        }
    }

    /*
    Traverses the ReversibleTree and saves all nodes with the specified distance to the list
     */
    private static void getAllWithDistK(List<Integer> kList, ReversibleTreeNode<Integer> root, int k){
        if(root.distToTarget==k){
            kList.add(root.val);
        }
        if(root.left!=null){
            getAllWithDistK(kList,root.left,k);
        }
        if(root.right!=null){
            getAllWithDistK(kList,root.right,k);
        }
    }

    /*
    Stores a node's ancestor and has space to store the distance to the target node
     */
    private static class ReversibleTreeNode<T> extends TreeNode<T> {
        public int distToTarget;
        public ReversibleTreeNode<T> ancestor;
        public ReversibleTreeNode<T> left;
        public ReversibleTreeNode<T> right;
        public ReversibleTreeNode(T val) {
            super(val);
        }
    }
}
