package calc.com.calculatons;

import java.util.Stack;

public class Calculations {
    //Метод возвращает true, если проверяемый символ - оператор
    static private boolean isOperator(char symbol) {
        return (("+-/*^()".indexOf(symbol) != -1));
    }

    //Метод расстановки приоритета знаков
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

    //Метод преобразовывывания выражение в постфиксную запись
    static private String getExpression(String input) {
        var arrInput = input.toCharArray();
        StringBuilder output = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < arrInput.length; i++)
        {
            //Разделители пропускаем
            if (isSeparator(arrInput[i]))
                continue;

            //Если символ - цифра, то считываем все число
            if (Character.isDigit(arrInput[i]))
            {
                //Читаем до разделителя или оператора, что бы получить число
                while (!isSeparator(arrInput[i]) && !isOperator(arrInput[i])) {
                    output.append(arrInput[i]); //Добавляем каждую цифру числа к нашей строке
                    i++;

                    if (i == input.length()) break; //Если символ - последний, то выходим из цикла
                }
                output.append(" "); //Дописываем после числа пробел в строку с выражением
                i--; //Возвращаемся на один символ назад, к символу перед разделителем
            }

            //Если символ - оператор
            if (isOperator(arrInput[i]))
            {
                if (arrInput[i] == '(')
                    operatorStack.push(arrInput[i]);
                else if (arrInput[i] == ')')
                {
                    Character s = operatorStack.pop();

                    while (s != '(') {
                        output.append(s.toString()).append(' ');
                        s = operatorStack.pop();
                    }
                } else
                {
                    if (operatorStack.size() > 0)
                        if (getStackPriority(arrInput[i]) <= getStackPriority(operatorStack.peek()))
                            output.append(operatorStack.pop().toString()).append(" ");
                    operatorStack.push(arrInput[i]);
                }
            }
        }
        //Когда прошли по всем символам, выкидываем из стека операторы в строку
        while (operatorStack.size() > 0)
            output.append(operatorStack.pop()).append(" ");

        return output.toString();
    }

    //Метод расчета значения
    static private double Counting(String input) {
        var arrInput = input.toCharArray();
        Stack<Double> temp = new Stack<>();

        for (int i = 0; i < input.length(); i++)
        {
            //Если символ - цифра, то читаем все число и записываем на вершину стека
            if (Character.isDigit(arrInput[i])) {
                StringBuilder a = new StringBuilder();
                while (!isSeparator(arrInput[i]) && !isOperator(arrInput[i]))
                {
                    a.append(arrInput[i]);
                    i++;
                    if (i == arrInput.length) break;
                }
                temp.push(Double.parseDouble(a.toString()));
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

    static public double Calculate(String input)
    {
        return Counting(getExpression(input));
    }
}


