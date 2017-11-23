import java.util.HashMap;
import java.util.Iterator;

public interface GenerateAllInputs {

    /**
     * this function should return the number of variables in this expression
     * @param expression is the logical expression input
     */
    public int gitnumberofinput(String expression);

    /**
     * this function will generate all valid input and pass them to ... to calculate the output
     * @param onerowofinput this hash map will carry only one row of inputs in the truth table
     */
    public void generateAllValidInputs(HashMap<Character, Boolean> onerowofinput, Iterator  i );

}
