package calc.com.calculatons;

public class CalcExceptions {
    public static String calcException(Double num){
        return num == Double.POSITIVE_INFINITY ? "Вы ввели неверное значение" : num.toString();
    }
}
