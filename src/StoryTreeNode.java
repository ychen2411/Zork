/**
 * This StoryTreeNode class creates nodes and the children nodes
 * that is used in the tree. It also contain methods to see if the
 * leaf is a winning leaf or losing leaf.
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 * Data member: static final String WIN_MESSAGE
 *              static final String LOSE_MESSAGE
 *              String position
 *              String option
 *              String message
 *              StoryTreeNode leftChild
 *              StoryTreeNode rightChild
 *              StoryTreeNode middleChild
 */
public class StoryTreeNode {
    public static final String WIN_MESSAGE = "YOU WIN";
    public static final String LOSE_MESSAGE = "YOU LOSE";
    private String position;
    private String option;
    private String message;
    StoryTreeNode leftChild;
    StoryTreeNode middleChild;
    StoryTreeNode rightChild;

    /**
     * sets the position of the node
     *
     * @precondition
     *      the node is initialized
     *
     * @position
     *      the position of the node is
     *      set to the position
     * @param position
     *      position of the node
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * returns the position of the node
     *
     * @precondition
     *      the node is initialized
     *
     * @return
     *      position of the node
     */
    public String getPosition(){
        return position;
    }

    /**
     * set message of the node
     *
     * @precondition
     *      the node is initialized
     *
     * @postcondition
     *      node's message is set to message
     *
     * @param message
     *      message of the node
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * return message of the node
     *
     * @precondition
     *      the node is initialized
     *
     * @return
     *      message of the node
     */
    public String getMessage() {
        return message;
    }

    /**
     * set option of the node
     *
     * @precondition
     *      the node is initialized
     *
     * @postcondition
     *      the option of the node is
     *      set to option
     *
     * @param option
     *      option of the node
     */
    public void setOption(String option){
        this.option = option;
    }

    /**
     * return option of the node
     *
     * @precondtion
     *      the node is initialized
     * @return
     *      option of the node
     */
    public String getOption() {
        return option;
    }

    /**
     * sets the left child of cursor
     *
     * @precondition
     *      node is initialized
     *
     * @postcondition
     *      node's leftChild is set to leftChild
     * @param leftChild
     *      the node to be set to left child
     */
    public void setLeftChild(StoryTreeNode leftChild){
        this.leftChild = leftChild;
    }

    /**
     * return the left child node of the cursor
     *
     * @precondition
     *      node and the leftChild os the node
     *      is initialized
     * @return
     *      left child node of cursor
     *
     */
    public StoryTreeNode getLeftChild(){
        return leftChild;
    }

    /**
     * sets the middle child of the cursor
     *
     * @precondition
     *      node is initialized
     * @postcondition
     *      middle child of the node is
     *      set to MidChild
     * @param midChild
     *      node to be set to midChild
     */
    public void setMiddleChild(StoryTreeNode midChild){
        this.middleChild = midChild;
    }

    /**
     * return the middle child of the cursor
     *
     * @precondition
     *      node's midChild is initialized
     * @return
     *      middle child of the cursor
     */
    public StoryTreeNode getMiddleChild(){
        return middleChild;
    }

    /**
     *set the right child of the cursor
     * @precondition
     *      the node is initialized
     * @postcondition
     *      the right child of the node is set
     *      to the rightChild
     * @param rightChild
     *      node to be set to the right child
     */
    public void setRightChild(StoryTreeNode rightChild){
        this.rightChild = rightChild;
    }

    /**
     * return the right child of the cursor
     * @precondition
     *      the rightChild of the node is
     *      initialized
     * @return
     *      right child of the cursor
     */
    public StoryTreeNode getRightChild(){
        return rightChild;
    }

    /**
     * returns an instance of StoryTreeNode object with
     * specified position, option, and message. Also sets the
     * leftChild, rightChild, and middleChild to null
     * when creating the instance
     *
     * @param position
     *      position of the node
     * @param option
     *      option of the node
     * @param message
     *      message of the node
     */
    public StoryTreeNode(String position, String option, String message){
        this.position = position;
        this.option = option;
        this.message = message;
        leftChild = null;
        middleChild = null;
        rightChild = null;
    }

    /**
     * checks if this node has any children
     *
     * @precondition
     *      node is initialized
     * @postconditions
     *      the tree remains unchanged
     * @return
     *      tree if there are no children
     *      false otherwise
     */
    public boolean isLeaf(){
        if (leftChild == null && middleChild == null
                && rightChild == null)
            return true;
        return false;
    }

    /**
     * check is the leaf is a winning node
     *
     * @precondition
     *      this node is initialized
     * @postcondition
     *      tree remains unchanged
     * @return
     *      true if the node is a leaf and
     *      contains WIN_MESSAGE false
     *      otherwise
     */
    public boolean isWinningNode(){
        return (this.isLeaf() &&
                this.getMessage().contains(WIN_MESSAGE));
    }

    /**
     * check if it is a losing node
     *
     * @precondition
     *      the node is initialized
     * @postcondition
     *      the tree remains unchanged
     * @return
     *      true if the node is a leaf and does not contain
     *      WIN_MESSAGE return false otherwise
     */
    public boolean isLosingNode() {
        return (this.isLeaf() &&
                !this.getMessage().contains(WIN_MESSAGE));
    }

}
