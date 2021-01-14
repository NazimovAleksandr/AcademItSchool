package ru.academits.java.nazimov.tree;

import java.util.LinkedList;

public class Tree<T> {
    private TreeNode<T> root;
    private int count;

    public Tree() {
    }

    public int getNodeCount() {
        return count;
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            count++;

            return;
        }

        TreeNode<T> node = root;

        while (true) {
            if (data.hashCode() < node.getData().hashCode()) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    node.setLeft(new TreeNode<>(data));
                    count++;

                    break;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    node.setRight(new TreeNode<>(data));
                    count++;

                    break;
                }
            }
        }
    }

    public boolean contains(T data) {
        if (data == null) {
            return false;
        }

        TreeNode<T> node = root;

        while (true) {
            if (data.equals(node.getData())) {
                return true;
            }

            if (data.hashCode() < node.getData().hashCode()) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    return false;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean remove(T data) {
        if (data == null) {
            return false;
        }

        TreeNode<T> previousNode = null;
        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (data.equals(currentNode.getData())) {
                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    if (previousNode == null) {
                        root = null;

                        count--;
                        return true;
                    }

                    if (previousNode.getLeft() == currentNode) {
                        previousNode.setLeft(null);
                    } else {
                        previousNode.setRight(null);
                    }

                    count--;
                    return true;
                }

                if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                    if (previousNode == null) {
                        root = root.getLeft();

                        count--;
                        return true;
                    }

                    if (previousNode.getLeft() == currentNode) {
                        previousNode.setLeft(currentNode.getLeft());
                    } else {
                        previousNode.setRight(currentNode.getLeft());
                    }

                    count--;
                    return true;
                }

                if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                    if (previousNode == null) {
                        root = root.getRight();

                        count--;
                        return true;
                    }

                    if (previousNode.getLeft() == currentNode) {
                        previousNode.setLeft(currentNode.getRight());
                    } else {
                        previousNode.setRight(currentNode.getRight());
                    }

                    count--;
                    return true;
                }

                TreeNode<T> removedNode = currentNode;
                TreeNode<T> parent = previousNode;

                previousNode = currentNode;
                currentNode = currentNode.getRight();

                while (currentNode.getLeft() != null) {
                    previousNode = currentNode;
                    currentNode = currentNode.getLeft();
                }

                if (previousNode.getLeft() == null) {
                    previousNode.setLeft(currentNode.getRight());
                }

                if (parent == null) {
                    currentNode.setLeft(root.getLeft());

                    root = currentNode;

                    count--;
                    return true;
                }

                if (parent.getRight() == removedNode) {
                    parent.setRight(currentNode);
                } else {
                    parent.setLeft(currentNode);
                }

                currentNode.setLeft(removedNode.getLeft());

                count--;
                return true;
            }

            previousNode = currentNode;

            if (data.hashCode() < currentNode.getData().hashCode()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public void toPassInWide() {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.pollFirst();

            // Выполняем для этого элемента нужную работу
            print(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void toPassInDepth() {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pollLast();

            // Выполняем для этого элемента нужную работу
            print(node.getData());

            if (node.getRight() != null) {
                stack.add(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }

    public void toPassInDepthRecursive() {
        toPassInDepthRecursive(root);
    }

    private void toPassInDepthRecursive(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        // Выполняем для этого элемента нужную работу
        print(node.getData());

        toPassInDepthRecursive(node.getLeft());
        toPassInDepthRecursive(node.getRight());
    }

    private void print(T data) {
        System.out.print("[" + data + "] ");
    }
}
