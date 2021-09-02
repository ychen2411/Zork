/**
 * This StoryTree class return a tree that contains nodes with
 * three children. It is structured as a continuous chain of
 * decisions the user may make to reach the end of the game.
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 * Data member: StoryTreeNode root
 *              StoryTreeNode cursor
 *              GameState state
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class StoryTree {
    private StoryTreeNode root;
    private StoryTreeNode cursor;
    private GameState state;

    /**
     * returns the GameState of the tree, winning end, losing end
     * or game is not over yet
     * @precondition
     *      state is not null
     *
     * @return
     *      the current state of the game
     */
    public GameState getGameState() {
        return state;
    }

    /**
     * sets the cursor to the node
     *
     * @precondition
     *      tree is initialized
     *
     * @postcondition
     *      the cursor is set to node
     * @param node
     *      the node to be set to cursor
     */
    public void setCursor(StoryTreeNode node) {
        cursor = node;
    }

    /**
     * returns the root of the tree
     * @precondition
     *      tree is initialized
     * @return
     *      the root of the tree
     */
    public StoryTreeNode getRoot() {return root;}

    /**
     * returns the cursor of the tree
     * @precondition
     *      tree is initialized
     * @return
     *      cursor of the tree
     */
    public StoryTreeNode getCursor() {
        return cursor;
    }

    /**
     * return the cursor's position
     *
     * @precondition
     *      tree is initialized
     * @return
     *      position of the cursor
     */
    public String getCursorPosition() {
        return cursor.getPosition();
    }

    /**
     * return the cursor's message
     *
     * @precondition
     *      tree is initialized
     *
     * @return
     *      message of the cursor
     */
    public String getCursorMessage() {
        return cursor.getMessage();
    }

    /**
     * sets the cursor to root
     * @precondition
     *      the tree object is initialized
     * @postcondition
     *      cursor is set to root
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * return a 2D String arrays that contains the
     * cursor's children's positions and options
     *
     * @precondition
     *      tree is initialized
     * @return
     *      position and option of the cursor's
     *      children
     */
    public String[][] getOption() {
        String[][] str = new String[3][2];
        if (cursor.getLeftChild() != null) {
            str[0][0] = cursor.getLeftChild().getPosition();
            str[0][1] = cursor.getLeftChild().getOption();
        }
        if (cursor.getMiddleChild() != null) {
            str[1][0] = cursor.getMiddleChild().getPosition();
            str[1][1] = cursor.getMiddleChild().getOption();
        }
        if (cursor.getRightChild() != null) {
            str[2][0] = cursor.getRightChild().getPosition();
            str[2][1] = cursor.getRightChild().getOption();
        }
        return str;
    }

    /**
     * returns the cursor's children's options
     *
     * @precondition
     *      tree is initialized
     * @return
     *      options of the cursor's children
     *
     */
    public String[] getOnlyCursorOption() {
        String[] str = new String[3];
        if (cursor.getLeftChild() != null)
            str[0] = cursor.getLeftChild().getOption();
        if (cursor.getMiddleChild() != null)
            str[1] = cursor.getMiddleChild().getOption();
        if (cursor.getRightChild() != null)
            str[2] = cursor.getRightChild().getOption();

        return str;
    }

    /**
     * set the message of the cursor
     *
     * @precondition
     *      tree is initialized
     *
     * @postcondition
     *      cursor's message is set to message
     *
     * @param message
     *      message of the cursor
     */
    public void setCursorMessage(String message) {
        cursor.setMessage(message);
    }

    /**
     * set cursor's option
     *
     * @precondition
     *      tree is initialized
     *
     * @postcondition
     *      cursor's option if set to option
     *
     * @param option
     *      cursor's position
     */
    public void setCursorOption(String option) {
        cursor.setOption(option);
    }

    /**
     * return an instance of StoryTree object with the
     * root node and the cursor pointed to the root
     *
     * @postcondition
     *      StoryTree object is initialized
     */
    public StoryTree() {
        root = new StoryTreeNode("root", "root", "Hello, welcome to Zork!");
        cursor = root;
    }

    /**
     * read in text file describing a StoryTree and convert it to
     * the StoryTree object
     *
     * @param filename
     *      name of the file to read from
     * @precondition
     *      filename is non-null, non-empty String
     *
     * @return
     *      StoryTree generated by the passed text file
     *
     * @throws IllegalArgumentException
     *      thrown the filename is empty or null or the file
     *      and data is file is inconsistent
     * @throws FileNotFoundException
     *      thrown when file don't exist
     */
    public static StoryTree readTree(String filename) {
        StoryTree tree = new StoryTree();
        try {
            File file = new File(filename);
            Scanner fileIn = new Scanner(file);

            while(fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                tree.addToPosition(line);
            }
            fileIn.close();
        }
        catch (FileNotFoundException e) {
            System.out.print("Error, file cannot be read. \n" +
                    "An empty tree is created, would you like to edit the tree?\n");
        }

        return tree;
    }

    /**
     * writes the data from file to the nodes
     * @precondition
     *      StoryTree is initialized
     * @posrcondition
     *      positions, option, and message is added to the node
     * @param line
     *      the lines in the file
     */
    private void addToPosition(String line) {

        String[] infoInNode = line.split("\\|");
        StoryTreeNode newNode = new StoryTreeNode("","","");
        newNode.setPosition(infoInNode[0].trim());
        newNode.setOption(infoInNode[1].trim());
        newNode.setMessage(infoInNode[2].trim());

        String position = infoInNode[0].trim();
        StoryTreeNode ptr = root;

        for (int i = 0; i < position.length() - 1; i++ ) {
            if (position.charAt(i) == '-')
                continue;
            if (position.charAt(i) == '1')
                ptr= ptr.getLeftChild();
            else if (position.charAt(i) == '2')
                ptr= ptr.getMiddleChild();
            else if (position.charAt(i) == '3')
                ptr = ptr.getRightChild();
        }

        if (position.charAt(position.length()-1) == '1')
            ptr.setLeftChild(newNode);
        if (position.charAt(position.length()-1) == '2')
            ptr.setMiddleChild(newNode);
        if (position.charAt(position.length()-1) == '3')
            ptr.setRightChild(newNode);
    }

    /**
     * Saves a StoryTree to indicated file using the specified dtat
     * format
     *
     * @param filename
     *      name of the file to read from
     *
     * @param tree
     *      A reference to the tree to save to the indicated file
     *
     * @precondition
     *      tree is not null, filename is not null, not empty String
     */
    public static void saveTree(String filename, StoryTree tree) {
        if (filename.equals("") || filename == null || tree == null)
            throw new IllegalArgumentException();

        try {
            StoryTreeNode ptr = tree.root.getLeftChild();
            StoryTreeNode ptr1 = tree.root.getMiddleChild();
            StoryTreeNode ptr2 = tree.root.getRightChild();
            FileWriter writer = new FileWriter(filename);
            tree.traversAndAdd(writer,ptr);
            tree.traversAndAdd(writer,ptr1);
            tree.traversAndAdd(writer,ptr2);
            writer.close();
        }
        catch (IOException e) {
            System.out.print("Errors occurred writing the file");
        }

    }

    /**
     * save the nodes to the file in the indicated format
     *
     * @param writer
     *      the file writer
     * @param node
     *      the node to write in file
     *
     * @precondition
     *      fileWriter is not null, not empty String
     *      node is initialized
     *
     * @postcondition
     *      nodes are written to lines of the file, 1 node per line
     *
     */
    public void traversAndAdd(FileWriter writer,StoryTreeNode node) {
        try {
            if (node == null)
                return;
            String position = node.getPosition();
            String option = node.getOption();
            String message = node.getMessage();
            String line = position + " | " + option + " | " + message;
            writer.write(line + "\n");
            if (node.getLeftChild() != null)
                traversAndAdd(writer,node.getLeftChild());
            if (node.getMiddleChild() != null)
                traversAndAdd(writer, node.getMiddleChild());
            if (node.getRightChild() != null)
                traversAndAdd(writer, node.getRightChild());
        }
        catch (IOException e) {
        }
    }

    /**
     * select the child with the name indicated by position
     *
     * @param position
     *      The position String of the child to node select
     * @precondition
     *      The child with the indicated position member variable
     *      exists as a direct child of the cursor
     * @postcondition
     *      cursor references node indicated by position
     * @throws NodeNotPresentException
     *      thrown when node in indicated position was not found
     * @throws IllegalArgumentException
     *      thrown when position is null or empty
     *
     */
    public void selectChild(String position) throws NodeNotPresentException {
        if (position == null)
            throw new IllegalArgumentException();
        StoryTreeNode ptr = root;
        findSelectChild(ptr,position);
        if (!cursor.getPosition().equals(position))
            throw new NodeNotPresentException();
    }

    /**
     * find the selected child
     *
     * @param node
     *      the nodes in the tree
     * @param position
     *      the position of the child
     *
     * @postcondition
     *      set the cursor to the node if the node's
     *      position matches the position in parameter
     */
    public void findSelectChild(StoryTreeNode node, String position){
        if (node.getPosition().equals(position)) {
            cursor = node;
            return;
        }
        if (node.getLeftChild() != null)
            findSelectChild(node.getLeftChild(),position);
        if (node.getMiddleChild() != null)
            findSelectChild(node.getMiddleChild(),position);
        if (node.getRightChild() != null)
            findSelectChild(node.getRightChild(),position);
    }

    /**
     * Add a new child under the current cursor with given
     * option and message. The position must implement
     *
     * @param option
     *      option set to the new child
     * @param message
     *      message set to the new child
     * @postcondition
     *      cursor has new child with specified message, option
     * @throws TreeFullException
     *      thrown when all three child spot are already taken
     *
     * @throws IllegalArgumentException
     *      thrown when either String is empty or null
     */
    public void addChild(String option, String message) throws TreeFullException{
        if (cursor.getLeftChild() != null && cursor.getMiddleChild() != null
                && cursor.getRightChild() != null)
            throw new TreeFullException();
        if (option == null || option.equals("") ||
                message == null || message.equals(""))
            throw new IllegalArgumentException();

        String pos;
        StoryTreeNode newNode;
        if (cursor.getLeftChild() == null) {
            pos = cursor.getPosition() + "-1";
            newNode = new StoryTreeNode(pos,option,message);
            cursor.setLeftChild(newNode);
        } else if (cursor.getMiddleChild() == null) {
            pos = cursor.getPosition() + "-2";
            newNode = new StoryTreeNode(pos,option,message);
            cursor.setMiddleChild(newNode);
        } else if (cursor.getRightChild() == null) {
            pos = cursor.getPosition() + "-3";
            newNode = new StoryTreeNode(pos,option,message);
            cursor.setRightChild(newNode);
        }
    }

    /**
     * remove an immediate child under the current cursor, the
     * child indicated by position.
     *
     * @param position
     *      position of child to be removed.
     * @preconditions
     *      The child with the indicated position exists as the
     *      direct child of the cursor
     * @postcondition
     *      The child is indicated position is removed
     *
     * @return
     *      A reference to the child removed, along with
     *      it's attached sub-tree
     *
     * @throws NodeNotPresentException
     *      thrown when the position is not found
     *      as direct child of the cursor
     */
    public StoryTreeNode removeChild(String position) throws NodeNotPresentException{
        if (!cursor.getLeftChild().getPosition().equals(position) &&
                !cursor.getMiddleChild().getPosition().equals(position) &&
                !cursor.getRightChild().getPosition().equals(position)) {
            throw new NodeNotPresentException();
        }
        StoryTreeNode ans = new StoryTreeNode("NO-POSITION","","");
        if(position.charAt(position.length()-1) == '1') {
            ans = cursor.getLeftChild();
//            cursor.setLeftChild(null);
            cursor.setLeftChild(cursor.getMiddleChild());
            cursor.setMiddleChild(cursor.getRightChild());
            cursor.setRightChild(null);
        } else if (position.charAt(position.length()-1) == '2') {
            ans = cursor.getMiddleChild();
//            cursor.setMiddleChild(null);
            cursor.setMiddleChild(cursor.getRightChild());
            cursor.setRightChild(null);
        } else if (position.charAt(position.length()-1) == '3') {
            ans = cursor.getRightChild();
            cursor.setRightChild(null);
        }

        StoryTreeNode cursorPtr = cursor;
        shiftToLeft(cursorPtr, cursorPtr.getPosition());

        return ans;
    }

    /**
     * Shifting all the nodes in the tree to left aligned,
     * and change their position to the accordance position
     * in the tree.
     *
     * @param ptr
     *      the nodes in the tree
     * @param str
     *      the position of the nodes
     */
    public void shiftToLeft(StoryTreeNode ptr, String str) {
        if (ptr == null) return;
        ptr.setPosition(str);
        if (ptr.getLeftChild() != null)
            shiftToLeft(ptr.getLeftChild(), str + "-1");
        if (ptr.getMiddleChild() != null)
            shiftToLeft(ptr.getMiddleChild(),str + "-2");
        if (ptr.getRightChild() != null)
            shiftToLeft(ptr.getRightChild(), str + "-3");
    }
}
