package ru.academits.java.nazimov.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private final Comparator<T> comparator;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree() {
        this.comparator = null;
    }

    public int getSize() {
        return size;
    }

    private int getComparisonResult(T data1, T data2) {
        int result;

        if (comparator != null) {
            result = comparator.compare(data1, data2);
        } else {
            //noinspection unchecked
            result = ((Comparable<T>)data1).compareTo(data2);
        }

        return result;
    }

    private void checkData(T data) {
        if (comparator == null && !(data instanceof Comparable)) {
            throw new IllegalArgumentException(
                    "Класс " + data.getClass() + " должен реализовывать интерфейс Comparable<T> " +
                            " или передайте Comparator<T> в конструктов");
        }
    }

    private TreeNode<T> getParentNode(T data) {
        int comparisonResult = getComparisonResult(data, root.getData());

        if (comparisonResult == 0) {
            return root;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode;

        while (true) {
            parentNode = currentNode;

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }

            if (currentNode == null) {
                return parentNode;
            }

            comparisonResult = getComparisonResult(data, currentNode.getData());

            if (comparisonResult == 0) {
                return parentNode;
            }
        }
    }

    public void add(T data) {
        checkData(data);

        size++;

        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }

        TreeNode<T> node = getParentNode(data);
        TreeNode<T> left = node.getLeft();
        TreeNode<T> right = node.getRight();

        while (true) {
            if (getComparisonResult(data, node.getData()) < 0) {
                if (left == null) {
                    node.setLeft(new TreeNode<>(data));
                    return;
                }

                node = node.getLeft();
            } else {
                if (right == null) {
                    node.setRight(new TreeNode<>(data));
                    return;
                }

                node = node.getRight();
            }

            left = node.getLeft();
            right = node.getRight();
        }
    }

    public boolean contains(T data) {
        checkData(data);

        if (data == null) {
            return false;
        }

        TreeNode<T> node = getParentNode(data);
        TreeNode<T> left = node.getLeft();
        TreeNode<T> right = node.getRight();

        int comparisonResult = getComparisonResult(data, node.getData());

        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult < 0) {
            if (left != null) {
                return getComparisonResult(data, left.getData()) == 0;
            }
        } else {
            if (right != null) {
                return getComparisonResult(data, right.getData()) == 0;
            }
        }

        return false;
    }

    public boolean remove(T data) {
        checkData(data);

        if (size == 0 || data == null) {
            return false;
        }

        TreeNode<T> previousNode = getParentNode(data);
        TreeNode<T> currentNode;

        int comparisonResult = getComparisonResult(data, previousNode.getData());

        if (comparisonResult < 0) {
            currentNode = previousNode.getLeft();
        } else if (comparisonResult > 0) {
            currentNode = previousNode.getRight();
        } else {
            currentNode = previousNode.getRight();

            while (currentNode.getLeft() != null) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }

            if (previousNode.getLeft() == currentNode) {
                previousNode.setLeft(currentNode.getRight());
            }

            currentNode.setLeft(root.getLeft());
            currentNode.setRight(previousNode);

            root = currentNode;

            size--;
            return true;
        }

        if (currentNode == null) {
            return false;
        }

        if (getComparisonResult(data, currentNode.getData()) == 0) {
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                if (previousNode.getLeft() == currentNode) {
                    previousNode.setLeft(null);
                } else {
                    previousNode.setRight(null);
                }

                size--;
                return true;
            }

            if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                if (previousNode.getLeft() == currentNode) {
                    previousNode.setLeft(currentNode.getLeft());
                } else {
                    previousNode.setRight(currentNode.getLeft());
                }

                size--;
                return true;
            }

            if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                if (previousNode.getLeft() == currentNode) {
                    previousNode.setLeft(currentNode.getRight());
                } else {
                    previousNode.setRight(currentNode.getRight());
                }

                size--;
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

            if (previousNode.getLeft() == currentNode) {
                previousNode.setLeft(currentNode.getRight());
            }

            if (parent.getRight() == removedNode) {
                parent.setRight(currentNode);
            } else {
                parent.setLeft(currentNode);
            }

            if (currentNode != removedNode.getLeft()) {
                currentNode.setLeft(removedNode.getLeft());
            }

            if (currentNode != removedNode.getRight()) {
                currentNode.setRight(removedNode.getRight());
            }

            size--;
            return true;
        }

        return false;
    }

    public void passInWidth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void passInDepth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pollLast();

            consumer.accept(node.getData());

            if (node.getRight() != null) {
                stack.add(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }

    public void passInDepthRecursive(Consumer<T> consumer) {
        passInDepthRecursive(root, consumer);
    }

    private void passInDepthRecursive(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        passInDepthRecursive(node.getLeft(), consumer);
        passInDepthRecursive(node.getRight(), consumer);
    }
}
