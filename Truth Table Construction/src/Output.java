import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Output {
    ArrayList<HashMap<Character, Boolean>> allInputs =new ArrayList<>();
    ArrayList<String> results = new ArrayList<>();
    String equation;
    public void setInput( final HashMap<Character, Boolean> row)
    {
        final HashMap<Character, Boolean> newInput = new HashMap<>();
        for (final Map.Entry<Character, Boolean> entry : row.entrySet()) {
            newInput.put(entry.getKey(), entry.getValue());
        }
        allInputs.add(newInput);

    }
}
