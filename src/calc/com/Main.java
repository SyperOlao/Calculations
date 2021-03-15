package calc.com;

import calc.com.calculatons.CalcExceptions;
import calc.com.calculatons.Calculations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("Введите выражение: ");
            System.out.println(CalcExceptions.calcException(Calculations.Calculate(input.nextLine())));
        }
    }
}

