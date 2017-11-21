import java.util.HashMap;
import java.util.HashSet;

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
            final HashMap<Character, Boolean> onerowofinput) {

    }

}
