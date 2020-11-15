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

    private static int findDistToTarget(ReversibleTreeNode<Integer> node, TreeNode<Integer> target, int dist, List<Integer> visited){
        visited.add(node.val);
        if(node.val.equals(target.val)){
            return dist;
        }
        if(node.left!=null && !visited.contains(node.left.val)){
            int distance = findDistToTarget(node.left, target, dist+1, visited);
            if(distance!=-1){
                return distance;
            }
        }
        if(node.right!=null && !visited.contains(node.right.val)){
            int distance = findDistToTarget(node.right, target, dist+1, visited);
            if(distance!=-1){
                return distance;
            }
        }
        if(node.ancestor!=null && !visited.contains(node.ancestor.val)){
            int distance = findDistToTarget(node.ancestor, target, dist+1, visited);
            if(distance!=-1){
                return distance;
            }
        }
        return -1;
    }

    /*
    sets the distance field for all treenodes in root tree
     */
    private static void setDistances(ReversibleTreeNode<Integer> root, TreeNode<Integer> target){
        List<Integer> visited = new ArrayList<Integer>();
        root.distToTarget = findDistToTarget(root, target, 0, visited);
        if(root.left!=null){
            setDistances(root.left, target);
        }
        if(root.right!=null){
            setDistances(root.right, target);
        }
    }

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
