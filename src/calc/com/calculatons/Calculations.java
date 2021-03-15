package calc.com.calculatons;

import java.util.Stack;

public class Calculations {
    //Метод возвращает true, если проверяемый символ - оператор
    static private boolean isOperator(char symbol) {
        return (("+-/*^()".indexOf(symbol) != -1));
    }

    static private byte getStackPriority(char symbol) {
        switch (symbol) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
                return 2;
            case '-':
                return 3;
            case '*':
            case '/':
                return 4;
            case '^':
                return 5;
            default:
                return 6;
        }
    }

    //Метод возвращает true, если проверяемый символ - разделитель ("пробел" или "равно")
    static private boolean isSeparator(char symbol) {
        return ((" =".indexOf(symbol) != -1));
    }
}

