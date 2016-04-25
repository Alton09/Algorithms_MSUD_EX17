package ex17;

/**
 * A simple binary tree.
 * @Author John Qualls
 */
public class BinaryTree {

    /**
     * Value of the root of this tree;
     * null if and only if empty tree.
     * @serial
     */
    private Node rootValue = null;

    /**
     * Left child of the root of this tree.
     * <code>null</code> used as indicator of missing child.
     * @serial
     */
    private BinaryTree leftChild;

    /**
     * Right child of the root of this tree.
     * <code>null</code> used as indicator of missing child.
     * @serial
     */
    private BinaryTree rightChild;
    
    /**
     * Construct an empty tree.
     */
    public BinaryTree() {
        this.rootValue = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Constructs a tree with no children
     * whose value is specified by the parameter.
     * @param rootvalue the value stored at the root of the tree
     * @throws IllegalArgumentException if parameter is null
     */
    public BinaryTree(final Node rootvalue)
    throws IllegalArgumentException {
        this(rootvalue, null, null);
    }

    /**
     * Constructs a tree with specified value,
     *   left child, and right child.
     * @param rootvalue the value stored at the root of the tree
     * @param leftchild the left child of the root;
     *        <code>null</code> if no such child
     * @param rightchild the right child of the root;
     *        <code>null</code> if no such child
     * @throws IllegalArgumentException
     *         if <code>rootvalue</code> parameter is null
     */
    public BinaryTree(final Node rootvalue,
    final BinaryTree leftchild,
    final BinaryTree rightchild)
    throws IllegalArgumentException {
        if (rootvalue == null) {
            throw new IllegalArgumentException();
        }
        this.rootValue = rootvalue;
        this.leftChild = leftchild;
        this.rightChild = rightchild;
    }

    /**
     * Empty tree predicate.
     * @return <code>true</code> if this is an empty tree;
     *         <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return (this.rootValue == null);
    }

    /**
     * Returns the value of the root of this tree.
     * @return the value of the root
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public Node getValue() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.rootValue;
    }

    /**
     * Returns the left child of this tree.
     * @return the left child; null if no such child
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public BinaryTree getLeftChild() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.leftChild;
    }

    /**
     * Returns the right child of this tree.
     * @return the right child; null if no such child
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public BinaryTree getRightChild() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.rightChild;
    }

    /**
     * Modifies the value of the root of this tree.
     * @param value the new value for the root
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public void setValue(final Node value) throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.rootValue = value;
    }

    /**
     * Replaces the left child of the root of this tree.
     * @param child the new left child for this tree
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public void setLeftChild(final BinaryTree child)
    throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.leftChild = child;
    }

    /**
     * Replaces the right child of the root of this tree.
     * @param child the new right child for this tree
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public void setRightChild(final BinaryTree child)
    throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.rightChild = child;
    }

    /**
     * Utility to determine the height of this tree;
     *   -1 if empty tree.
     * @return the height of this tree, -1 if empty
     */
    public final int height() {
        if (isEmpty()) {
            return -1;
        }
        int maxChildHeight = -1;
        if (this.leftChild != null) {
            maxChildHeight = this.leftChild.height();
        }
        if (this.rightChild != null) {
            maxChildHeight = Math.max(maxChildHeight, this.rightChild.height());
        }
        return 1 + maxChildHeight;
    }
}
