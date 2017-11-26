import java.util.HashMap;

public interface IvalidateAndCalculate {

    /**
     * set the boolean expression
     * */
    public void setExpression(String expression);

    /**
     * determine if the given expression is valid or not
     *
     * @param expression
     * 			the given expression
     * @return true if the expression is valid false otherwise
     * */

    public boolean isAvalidExpression ();

    /**
     * evaluates the given expression depending on the given states of inputs
     * @param values
     * 			the current values for each input whether true or false
     *
     * @return the value of the expression after substituting
     * */
    public Boolean evaluateExpression(HashMap<Character,Boolean> values );
}
