import java.util.HashMap;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Masrawy Net
 *
 */
public class ValidateAndCalculate implements IvalidateAndCalculate {

	private String expression;
	private String exp;
	private boolean isValid = false;

	@Override
	public void setExpression(String expression) {
		this.expression = expression;
		String exp = removeSpaces(expression);
		this.exp = exp;
	}

	@Override
	public boolean isAvalidExpression() {
				int numberOfOpenPraces = 0;
		int numberOfClosedPraces = 0;
		for (int i = 0; i < exp.length(); i++) {

			if (exp.charAt(i) == '(') {
				numberOfOpenPraces++;
			}
			if (exp.charAt(i) == ')') {
				numberOfClosedPraces++;
			}
			// ()
			if (i != exp.length() - 1 && (isOpenpraces(exp.charAt(i)) && isClosepraces(exp.charAt(i + 1)))) {
				isValid = false;
				return isValid;
			}
			// ......( , // ...)a or ...(a
			else if (i == exp.length() - 1 && (isOpenpraces(exp.charAt(i)) || isOperator(exp.charAt(i))
					|| (isAletter(exp.charAt(i)) && exp.length() != 1
							&& (isClosepraces(exp.charAt(i - 1)) || isOpenpraces(exp.charAt(i - 1)))))) {
				isValid = false;
				return isValid;
			}
			// ab
			else if (i != exp.length() - 1 && (isAletter(exp.charAt(i)) && isAletter(exp.charAt(i + 1)))) {
				isValid = false;
				return isValid;
			}
			// )a
			else if (i != exp.length() - 1 && (isClosepraces(exp.charAt(i)) && isAletter(exp.charAt(i + 1)))) {
				isValid = false;
				return isValid;
			}
			// (&
			else if (i != exp.length() - 1
					&& (isOpenpraces(exp.charAt(i)) && (isOperator(exp.charAt(i + 1)) && exp.charAt(i + 1) != '~'))) {
				isValid = false;
				return isValid;
			}
			// )~
			else if (i != exp.length() - 1
					&& (isClosepraces(exp.charAt(i)) && (isOperator(exp.charAt(i + 1)) && exp.charAt(i + 1) == '~'))) {
				isValid = false;
				return isValid;
			}
			// x~...
			else if (i != exp.length() - 1
					&& (isAletter(exp.charAt(i)) && (isOperator(exp.charAt(i + 1)) && exp.charAt(i + 1) == '~'))) {
				isValid = false;
				return isValid;
			}
			// &|
			else if (i != exp.length() - 1
					&& (isOperator(exp.charAt(i)) && (isOperator(exp.charAt(i + 1)) && exp.charAt(i + 1) != '~'))) {
				isValid = false;
				return isValid;
			}
		}
		if (numberOfClosedPraces == numberOfOpenPraces) {
			isValid = true;
			return isValid;
		} else {
			isValid = false;
			return isValid;
		}
	}

	@Override
	public boolean evaluateExpression(HashMap<Character, Boolean> values) {
		String postFixExp = infixToPostfix(this.exp);
		Stack<Boolean> s = new Stack<>();
		for (char x : postFixExp.toCharArray()) {
			if (isAletter(x)) {
				s.push(values.get(x));
				continue;
			} else if (isOperator(x)) {
				boolean result;
				if (isNot(x)) {
					boolean p = s.pop();
					result = not(p);
					s.push(result);

				} else {
					boolean q = s.pop();
					boolean p = s.pop();

					switch (x) {
					case '&':
						s.push(and(p, q));
						break;
					case '|':
						s.push(or(p, q));
						break;
					case '>':
						s.push(conditional(p, q));
						break;
					case '<':
						s.push(biConditional(p, q));
						break;
					}
				}
			}
		}
		
		if(s.size() == 1){
			return s.pop();
		}else{
			throw new RuntimeException();
		}
	}

	private int precedence(char c) {
		int precedence = 0;
		if (c == '>' || c == '<') {
			precedence = 1;
		} else if (c == '&' || c == '|') {
			precedence = 2;
		} else if (c == '~') {
			precedence = 3;
		} else if (c == '(' || c == ')') {
			precedence = 4;
		}
		return precedence;
	}

	private String removeSpaces(String expression) {

		StringBuilder exp = new StringBuilder();

		if (expression == null || expression.length() == 0) {
			return null;
		}

		for (int j = 0; j < expression.length(); j++) {
			if (expression.charAt(j) != ' ') {
				exp.append(expression.charAt(j));
			}
		}

		return exp.toString();
	}

	private boolean isValidSymbol(String exp, int i) {
		if (exp.charAt(i) == '&' || exp.charAt(i) == '|' || exp.charAt(i) == '~' || exp.charAt(i) == '>'
				|| exp.charAt(i) == '<' || exp.charAt(i) == '(' || exp.charAt(i) == ')') {
			return true;
		} else {
			return false;
		}
	}

	private boolean isAletter(char x) {
		if ((x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z')) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isOpenpraces(char x) {
		if (x == '(') {
			return true;
		} else {
			return false;
		}
	}

	private boolean isClosepraces(char x) {
		if (x == ')') {
			return true;
		} else {
			return false;
		}
	}

	private boolean isOperator(char x) {
		if (x == '&' || x == '|' || x == '~' || x == '>' || x == '<') {
			return true;
		} else {
			return false;
		}
	}

	public String infixToPostfix(String exp) {

		Stack<Character> s = new Stack<Character>();
		if (exp == null || exp.length() == 0) {
			throw null;
		}

		StringBuilder postfix = new StringBuilder();
		for (int i = 0; i < exp.length(); i++) {

			if (isAletter(exp.charAt(i))) {

				postfix.append(exp.charAt(i));

			} else if (isValidSymbol(exp, i)) {
				if (exp.charAt(i) == ')') {
					while (!s.isEmpty() && (char) s.peek() != '(') {
						postfix.append(s.pop());
					}
					if (s.peek() == null) {
						throw null;
					} else if ((char) s.peek() == '(') {
						s.pop();
					}
				} else {
					if (s.isEmpty() || s.peek() == '(' || (precedence(exp.charAt(i)) > precedence((char) s.peek()))) {

						s.push(exp.charAt(i));

					} else {
						
						if(isNot(exp.charAt(i)) && isNot(s.peek())){
							s.pop();
						}else{							
							while (!s.isEmpty() && ((precedence(exp.charAt(i)) <= precedence((char) s.peek())
									&& (char) s.peek() != '('))) {
								
								postfix.append(s.pop());
							}
							s.push(exp.charAt(i));
						}
					}

				}

			} else {
				throw new RuntimeException();
			}

		}
		while (!s.isEmpty()) {
			postfix.append(s.pop());
		}
		return postfix.toString();
	}


	private boolean isNot(char x) {
		return x == '~';
	}

	private boolean and(boolean x, boolean y) {
		return x & y;
	}
	
	private boolean or(boolean x, boolean y) {
		return x | y;
	}
	
	private boolean not(boolean x) {
		if (x == true) {
			return false;
		} else {
			return true;
		}
	}

	private boolean conditional(boolean p, boolean q) {
		if (p == true && q == false) {
			return false;
		} else {
			return true;
		}
	}

	private boolean biConditional(boolean p, boolean q) {
		return (conditional(p, q) & conditional(q, p));
	}

}
