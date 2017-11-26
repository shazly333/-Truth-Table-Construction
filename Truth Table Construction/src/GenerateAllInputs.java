import java.util.HashMap;

public interface GenerateAllInputs {

    /**
     * this function should return the number of variables in this expression
     * @param expression is the logical expression input
     */
    public void getVariables(String expression);

    /**
     * this function will generate all valid input and pass them to ... to calculate the output
     * @param onerowofinput this hash map will carry only one row of inputs in the truth table
     */

    void generateAllValidInputs(HashMap<Character, Boolean> onerowofinput,
            int i, Output expression);

}
