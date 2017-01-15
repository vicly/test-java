package vic.test.datastructure;

import java.util.Stack;

public class FlatternTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private TreeNode linkedListLastNode;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        linkedListLastNode = root;
        doFlattern(root);
    }

    private void doFlattern(TreeNode node) {
        if (node == null) return;

        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left != null) {
            linkedListLastNode.right = left;
            linkedListLastNode.left = null;
            linkedListLastNode = linkedListLastNode.right;
            doFlattern(left);
        }
        if (right != null) {
            linkedListLastNode.right = right;
            linkedListLastNode = linkedListLastNode.right;
            doFlattern(right);
        }
    }




    private TreeNode prev = null;
    public void flatten2(TreeNode root) {
        if (root == null)
            return;
        flatten2(root.right);
        flatten2(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public void flatten3(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (!stk.isEmpty()){
            TreeNode curr = stk.pop();
            if (curr.right!=null)
                stk.push(curr.right);
            if (curr.left!=null)
                stk.push(curr.left);
            if (!stk.isEmpty())
                curr.right = stk.peek();
            curr.left = null;  // dont forget this!!
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n2.left = n3;
        n2.right = n4;
        n5.left = n6;
        n5.right = n7;
        n1.left = n2;
        n1.right = n5;

        FlatternTree solution = new FlatternTree();
        solution.flatten2(n1);

        int loop = 0;
        TreeNode n = n1;
        while (n != null && loop < 100) {
            loop ++;
            System.out.println(n.val);
            n = n.right;
        }
    }
}


