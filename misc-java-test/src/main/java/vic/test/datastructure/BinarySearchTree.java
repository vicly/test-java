package vic.test.datastructure;

/**
 * Binary Search Tree
 * 1. 二叉树
 * 2. 左子树 <= parent <= 右子树
 *
 * Insert: 与各节点比较
 *         小往左，大王右，至到叶子
 *
 * Delete:
 *     若 叶子节点，直接删掉
 *
 *     非叶子节点
 *       若 仅一个子节点，子树上移，替代其位置
 *
 *       若 有两个子节点
 *          左子最大节点 或 右子树最小节点 来替代其位置
 *          原左最大／右最小节点位置，由其子节点替代
 *
 *
 * @author Vic Liu
 */
public class BinarySearchTree {

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(5);
        bst.insert(8);
        bst.insert(1);
        bst.insert(2);
        bst.insert(9);

        bst.preorder();
        bst.inorder();
        bst.postorder();

        System.out.println("Inorder:");
        bst.inorderV2(bst.root);
    }


    Node root;

    public boolean insert(int val) {
        if (root == null) {
            root = new Node(val);
            return true;
        }
        else
            return root.insert(val);
    }

    public boolean find(int val) {
        if (root == null)
            return false;
        else
            return root.find(val);
    }

    public void preorder() {
        if (root != null) {
            System.out.println("Preorder:");
            root.preorder();
        }
    }

    public void postorder() {
        if (root != null) {
            System.out.println("Postorder:");
            root.postorder();
        }
    }

    public void inorder() {
        if (root != null) {
            System.out.println("Inorder:");
            root.inorder();
        }
    }

    // another inorder
    public void inorderV2(Node root) {
        if (root.left != null){
            inorderV2(root.left);
        }
        System.out.println(root.data);
        if (root.right != null){
            inorderV2(root.right);
        }
    }

    private class Node {
        private Node left;
        private Node right;
        private int data;

        private Node(int val) {
            data = val;
        }

        private boolean insert(int val) {
            boolean added = false;
            if (this == null) {
                this.data = val;
                return true;
            }
            else {
                if (val < this.data) {
                    if (this.left == null) {
                        this.left = new Node(val);
                        return true;
                    }
                    else
                        added = this.left.insert(val);
                }
                else if (val > this.data) {
                    if (this.right == null) {
                        this.right = new Node(val);
                        return true;
                    }
                    else
                        added = this.right.insert(val);
                }
            }
            return added;
        }

        private boolean find(int val) {
            boolean found = false;
            if (this == null)
                return false;
            else {
                if (val == this.data)
                    return true;
                else if (val < this.data && this.left != null)
                    found = this.left.find(val);
                else if (val > this.data && this.right != null)
                    found = this.right.find(val);
            }
            return found;
        }

        private void preorder() {
            if (this != null) {
                System.out.println(this.data);
                if (this.left != null)
                    this.left.preorder();
                if (this.right != null)
                    this.right.preorder();
            }
        }

        private void postorder() {
            if (this != null) {
                if (this.left != null)
                    this.left.postorder();
                if (this.right != null)
                    this.right.postorder();
                System.out.println(this.data);
            }
        }

        private void inorder() {
            if (this != null) {
                if (this.left != null)
                    this.left.inorder();
                System.out.println(this.data);
                if (this.right != null)
                    this.right.inorder();
            }
        }
    }
}
