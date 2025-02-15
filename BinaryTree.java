//Alexander Ross Section 001

import java.util.Queue;
import java.util.LinkedList;

/*
 * BinaryTree Class
 *
 * This class defines a binary tree object where each node has at most two child nodes.
 * The class supports insertion, value replacement, minimum value retrieval, node counting,
 * and calculating the average of all values.
 */

public class BinaryTree {

    // Constructors
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node node) {
        root = node;
    }

    /*
     * Class Node
     *
     * This defines a node in the binary tree.
     */

    static class Node {
        public int data;
        public Node left;
        public Node right;

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }

        Node(int d, Node l, Node r) {
            data = d;
            left = l;
            right = r;
        }
    }   /* End Class Node */

    public Node root;

    public void deleteTree() {
        root = null;
    }

    public void replaceValue(int oldVal, int newVal) {
        replaceValueHelper(root, oldVal, newVal);
    }

    public int findMin() {
        return findMinHelper(root);
    }

    public int nodesGT(int val) {
        return nodesGTHelper(root, val);
    }

    public double average() {
        int[] sumAndCount = averageHelper(root);
        return sumAndCount[1] == 0 ? 0 : (double) sumAndCount[0] / sumAndCount[1];
    }

    // Insert a new node into the tree
    Node insert(int data) {
        Node tempNode = new Node(data);

        if (root == null)
            return root = tempNode;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node front = queue.poll();

            if (front.left == null) {
                front.left = tempNode;
                break;
            } else if (front.right == null) {
                front.right = tempNode;
                break;
            }

            if (front.left != null)
                queue.add(front.left);

            if (front.right != null)
                queue.add(front.right);
        }

        return tempNode;
    }

    public String preOrder() {
        return preOrderHelper(root);
    }

    private String preOrderHelper(Node node) {
        if (node == null) {
            return "";
        }
        return node.data + " " + preOrderHelper(node.left) + preOrderHelper(node.right);
    }

    // Replace occurrences of oldVal with newVal
    private void replaceValueHelper(Node node, int oldVal, int newVal) {
        if (node == null) return;

        if (node.data == oldVal) {
            node.data = newVal;
        }

        replaceValueHelper(node.left, oldVal, newVal);
        replaceValueHelper(node.right, oldVal, newVal);
    }

    // Find the minimum value in the tree
    private int findMinHelper(Node node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int leftMin = findMinHelper(node.left);
        int rightMin = findMinHelper(node.right);

        return Math.min(node.data, Math.min(leftMin, rightMin));
    }

    // Count nodes with values greater than val
    private int nodesGTHelper(Node node, int val) {
        if (node == null) {
            return 0;
        }

        int count = (node.data > val) ? 1 : 0;
        count += nodesGTHelper(node.left, val);
        count += nodesGTHelper(node.right, val);

        return count;
    }

    // Calculate sum and count of nodes for average computation
    private int[] averageHelper(Node node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] leftResult = averageHelper(node.left);
        int[] rightResult = averageHelper(node.right);

        int sum = node.data + leftResult[0] + rightResult[0];
        int count = 1 + leftResult[1] + rightResult[1];

        return new int[]{sum, count};
    }
