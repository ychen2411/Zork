/**
 * This TreeFullException creates an exception object that can be thrown
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 */
public class TreeFullException extends Exception{
    public TreeFullException() {
        super("All three child spots are already full");
    }
}
