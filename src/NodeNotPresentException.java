/**
 * This NodeNotPresentException creates an exception object that can be thrown
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 */
public class NodeNotPresentException extends Exception {
    public NodeNotPresentException() {
        super("Node not found in the position");
    }
}
