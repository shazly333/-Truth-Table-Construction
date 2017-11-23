import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class GenerateInput implements GenerateAllInputs {


    @Override
    public int gitnumberofinput(final String expression) {
        final HashSet<Character> distinctnumber= new HashSet<>();
        for(int i=0;i<expression.length();i++)
        {
            if(Character.isLetter(expression.charAt(i)))
            {
                distinctnumber.add(expression.charAt(i));
            }
        }

        return distinctnumber.size();
    }

    @Override
    public void generateAllValidInputs(
            final HashMap<Character, Boolean> onerowofinput,
            final Iterator  i, final Expression expression) {
        if(!i.hasNext())
        {
            expression.results.add(.evaluate(onerowofinput));
            expression.allInputs.add(onerowofinput);
            return;
        }
        final Map.Entry pair = (Map.Entry)i.next();
        onerowofinput.put((Character) pair.getKey(), true);
        generateAllValidInputs(onerowofinput, i,expression);
        onerowofinput.put((Character) pair.getKey(), false);
        generateAllValidInputs(onerowofinput, i,expression);
    }
}
