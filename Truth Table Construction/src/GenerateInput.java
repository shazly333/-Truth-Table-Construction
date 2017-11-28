import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GenerateInput implements GenerateAllInputs {

    ArrayList<Character> variables =new ArrayList<>();
    @Override
    public void getVariables(final String expression) {
        final HashSet<Character> distinctVariables= new HashSet<>();
        for(int i=0;i<expression.length();i++)
        {
            if(Character.isLetter(expression.charAt(i)))
            {
                distinctVariables.add(expression.charAt(i));
            }
        }
        final Iterator it = distinctVariables.iterator();
        while(it.hasNext())
        {
            variables.add((Character) it.next());
        }

    }

    @Override
    public void generateAllValidInputs(
            final HashMap<Character,Boolean> oneRowOfInput,
            final int  i, final Output expression) {
        if(i == variables.size())
        {
            final ValidateAndCalculate calculate = new ValidateAndCalculate();
            calculate.setExpression(expression.equation);
            // calculate the result with new variables and save results
            expression.results.add(calculate.evaluateExpression(oneRowOfInput).toString());
            // save input
            expression.setInput(oneRowOfInput);
            //System.out.println(expression.results.get(expression.results.size()-1));
            return;
        }
        // make the variable numbre i true
        oneRowOfInput.put(variables.get(i), true);
        generateAllValidInputs(oneRowOfInput, i+1,expression);
        // make the variable numbre i false
        oneRowOfInput.put(variables.get(i), false);
        generateAllValidInputs(oneRowOfInput, i+1,expression);
    }

}
