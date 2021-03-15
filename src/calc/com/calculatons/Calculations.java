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

    //Метод возвращает true, если проверяемый символ - разделитель
    static private boolean isSeparator(char symbol) {
        return ((" =".indexOf(symbol) != -1));
    }


    static private double Counting(String input) {
        var arrInput = input.toCharArray();
        Stack<Double> temp = new Stack<>();

        for (int i = 0; i < input.length(); i++)
        {
            //Если символ - цифра, то читаем все число и записываем на вершину стека
            if (Character.isDigit(arrInput[i])) {
                String a = "";
                while (!isSeparator(arrInput[i]) && !isOperator(arrInput[i]))
                {
                    a += arrInput[i];
                    i++;
                    if (i == arrInput.length) break;
                }
                temp.push(Double.parseDouble(a));
                i--;
            } else if (isOperator(arrInput[i]))
            {
                double second = temp.pop();
                double first = temp.pop();
                //Вычисляется результат
                temp.push(tempResCalc(second, first, arrInput[i]));
            }
        }
        return temp.peek();
    }

    private static double tempResCalc(double second, double first, char symbol) {
        switch (symbol) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '^':
                return Double.parseDouble(String.valueOf(Math.pow(first, second)));
        }
        return 0;
    }
}


