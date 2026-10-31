import java.util.Stack;

public class Calculator {
    public Calculator() {}

    private static int precedence(char ops) {
        if (ops == '*' || ops == '/') return 2;
        if (ops == '+' || ops == '-') return 1;
        return 0;
    }

    private static double applyOperation(char ops, double b, double a) {
        switch (ops) {
            case '+': return (a + b);
            case '-': return (a - b);
            case '*': return (a * b);
            case '/': if (b == 0) throw new ArithmeticException("Can't divide with zero");
                      return (a / b);
        }
        return 0;
    }

    public double evaluate (String operation) {
        char[] tokens = operation.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();    

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            if (Character.isDigit(tokens[i])) {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(sb.toString()));
            } 
            else if (tokens[i]  == '(') {
                ops.push(tokens[i]);
            }
            else if (tokens[i] == ')') {
                while (!ops.empty() && ops.peek() != '(') { 
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));                     
                }
                ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && (precedence(ops.peek()) >= precedence(tokens[i]))) { 
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }    

        while (!ops.empty()) {
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
        }   

        return values.pop();
    }
}