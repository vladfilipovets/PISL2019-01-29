package by.it.group673601.kopytko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        List<Long> fib = new ArrayList();
        fib.add((long)0);
        fib.add((long)1);
        for(int i = 2; i < n; i++){
            fib.add((fib.get(i - 1) + fib.get(i - 2)) % m);
            if(fib.get(i) == 1 && fib.get(i-1) == 0){
                fib.remove(i);
                fib.remove(i-1);
                break;
            }
        }
        return fib.get((int)n% fib.size());
    }

    }




