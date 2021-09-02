/**
 * This Zork class test the StoryTree class and StoryTreeNode and
 * the methods within them.
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Zork {
    public static void main(String[] args) {
        System.out.print("Hello and Welcome to Zork!\n\n");
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the file name: ");
        String fileName = in.nextLine();
        StoryTree tree = StoryTree.readTree(fileName);
        System.out.print("Loading game from file...\n\n" +
                "File loaded!\n");
//        try {
//            FileWriter writer = new FileWriter("Yanhui.txt");
//            writer.write("HELLO WORLD\n");
//            writer.write("BYE WORLD");
//            writer.close();
//        } catch (IOException e) {
//            System.out.print("ERROR IN CREATING FILE\n\n\n");
//        }

        String choice = "";
        String vChoice = "";
        do {
            System.out.print("Would you like to edit (E), play (P), or quit (Q)? ");
            choice = in.next();
            vChoice = choice.toUpperCase();

            switch(vChoice) {
                case "E": {
                    editTree(tree);
                    StoryTree.saveTree(fileName,tree);
                    break;
                }
                case "P": {
                    playTree(tree);
                    break;
                }
                case "Q": {
                    System.out.print("Game being saved to " + fileName + "...\n");
                    StoryTree.saveTree(fileName,tree);
                    System.out.print("Save successful!\n\n" +
                            "Program terminates normally");
                    System.exit(0);
                    break;
                }
            }

        } while (!vChoice.equalsIgnoreCase("Q"));

    }

    /**
     * play the tree
     *
     * @param tree
     *      the StoryTree object
     *
     * @precondition
     *      the StoryTree object is initialized
     */
    public static void playTree(StoryTree tree) {
        Scanner in = new Scanner(System.in);
        System.out.print("Let's play!\n");
        tree.setCursor(tree.getCursor().getLeftChild());
        while (tree.getCursor() != null) {
            if (tree.getCursor().isWinningNode() || tree.getCursor().isLosingNode()) {
                System.out.print(tree.getCursor().getMessage());
                break;
            }
            System.out.print(tree.getCursor().getMessage() + "\n");
            String[] option = tree.getOnlyCursorOption();
            int i = 1;
            for (String a : option) {
                if (a == null)
                    break;
                System.out.print(i + ") "+ a + "\n");
                i++;
            }
            System.out.print("Please make a choice: ");
            String num = in.nextLine();
            if (num.equals("1") && tree.getCursor().getLeftChild() != null) {
                tree.setCursor(tree.getCursor().getLeftChild());
            } else if (num.equals("2") && tree.getCursor().getMiddleChild() != null) {
                tree.setCursor(tree.getCursor().getMiddleChild());
            } else if (num.equals("3") && tree.getCursor().getRightChild() != null) {
                tree.setCursor(tree.getCursor().getRightChild());
            }
        }
        tree.resetCursor();
        System.out.print("\n\nThanks for playing.\n");
    }

    /**az
     * edit the tree
     *
     * @param tree
     *      the StoryTree object
     *
     * @precondition
     *      the StoryTree object is initialized
     */
    public static void editTree(StoryTree tree){
        Scanner in = new Scanner(System.in);
        System.out.print("\nZork Editor:\n");
        String[] menu = {"V: View the cursor's position, option and message.",
                "S: Select a child of this cursor (Options are 1,2, and 3.",
                "O: Set the option of the cursor.",
                "M: Set the message of the cursor.",
                "A: Add a child StoryNode to the cursor.",
                "D: Delete one of the cursor's children and all its descendants.",
                "R: Move the cursor to the root of the tree.",
                "Q: Quit editing and return to main menu."};
        for (String a : menu) {
            System.out.print(a + "\n");
        }
        String choice = "";
        String vChoice = "";
        do {
            try {
                System.out.print("\nPlease select an option: ");
                choice = in.nextLine();
                vChoice = choice.toUpperCase();

                switch (vChoice) {
                    case "V": {
                        System.out.print("\nPosition: " + tree.getCursorPosition());
                        System.out.print("\nOption: " + tree.getCursor().getOption());
                        System.out.print("\nMessage: " + tree.getCursorMessage());
                        break;
                    }
                    case "S": {
                        System.out.print("\bPlease select a child: [1,2,3] ");
                        String num = in.nextLine();
                        if (num.equals("1") && tree.getCursor().getLeftChild() != null) {
                            tree.setCursor(tree.getCursor().getLeftChild());
                            System.out.print("\ncursor is set to the left child of the current node.\n");
                        } else if (num.equals("2") && tree.getCursor().getMiddleChild() != null) {
                            tree.setCursor(tree.getCursor().getMiddleChild());
                            System.out.print("\ncursor is set to the middle child of the current node.\n");
                        } else if (num.equals("3") && tree.getCursor().getRightChild() != null) {
                            tree.setCursor(tree.getCursor().getRightChild());
                            System.out.print("\ncursor is set to the right child of the current node.\n");
                        } else {
                            System.out.print("\nError. No child " + num + " for the current node.\n");
                        }
                        break;
                    }
                    case "O": {
                        System.out.print("\nPlease enter a new option: ");
                        String option = in.nextLine();
                        tree.setCursorOption(option);
                        System.out.print("\nOption is set.\n");
                        break;
                    }
                    case "M": {
                        System.out.print("\nPlease enter a new message: ");
                        String message = in.nextLine();
                        tree.setCursorMessage(message);
                        System.out.print("\nMessage set.\n");
                        break;
                    }
                    case "A": {
                        if (tree.getRoot().getLeftChild() == null) {
                            System.out.print("Enter an option: ");
                            String option = in.nextLine();
                            System.out.print("Enter a message: ");
                            String message = in.nextLine();
                            StoryTreeNode head = new StoryTreeNode("1",option,message);
                            tree.getRoot().setLeftChild(head);
                            System.out.print("\nChild added.");
                        } else {
                            System.out.print("Enter an option: ");
                            String option = in.nextLine();
                            System.out.print("Enter a message: ");
                            String message = in.nextLine();
                            tree.addChild(option, message);
                            System.out.print("\nChild added.");
                        }
                        break;
                    }
                    case "D": {
                        String input = "";
                        String whichChild;
                        if (tree.getCursor().getLeftChild() != null && tree.getCursor().getMiddleChild() != null
                                && tree.getCursor().getRightChild() != null) {
                            System.out.print("Please select a child: [1,2,3] ");
                            input = in.nextLine();
                        } else if (tree.getCursor().getLeftChild() != null &&
                                tree.getCursor().getMiddleChild() != null &&
                                tree.getCursor().getRightChild() == null) {
                            System.out.print("Please select a child: [1,2] ");
                            input = in.nextLine();
                        } else if (tree.getCursor().getLeftChild() != null &&
                                tree.getCursor().getMiddleChild() == null &&
                                tree.getCursor().getRightChild() == null) {
                            System.out.print("Please select a child: [1] ");
                            input = in.nextLine();
                        }
                        else {
                            System.out.print("Choice not valid\n");
                        }
                        whichChild = tree.getCursorPosition().trim() + "-" + input;
                        tree.removeChild(whichChild);
                        System.out.print("subtree is deleted.\n" );

                        break;
                    }
                    case "R": {
                        tree.resetCursor();
                        tree.setCursor(tree.getCursor().getLeftChild());
                        System.out.print("\nCursor moved to root\n");
                        break;
                    }

                }
            }
            catch (TreeFullException e) {
                System.out.print("\n" + e.getMessage());
            }
            catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
            }
            catch (NodeNotPresentException e) {
                System.out.print(e.getMessage());
            }
        } while(!vChoice.equals("Q"));
    }
}
