package by.it.group673602.eliseev.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.Collections;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        try {
            System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
        }
        catch (ArithmeticException e){
            System.out.println("0 is not allowed here");
        }
    }

    long fasterC(long n, int m) throws ArithmeticException{
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        if (m == 0){
            throw new ArithmeticException();
        }
        else if (m == 1){
            return 0;
        }
        ArrayList<Long> fiboMas = new ArrayList<>(2);
        Collections.addAll(fiboMas, 0L, 1L);
        int i = 2;
        for (; i < m * 6; i++){
            fiboMas.add((fiboMas.get(i - 1) + fiboMas.get(i-2))% m);
            if (fiboMas.get(i - 1) == 0 && fiboMas.get(i) == 1){
                break;
            }
        }
        return fiboMas.get((int)n % (i - 1));
    }


}

