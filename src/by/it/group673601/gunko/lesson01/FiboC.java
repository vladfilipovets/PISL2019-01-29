package by.it.group673601.gunko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

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
        ArrayList<Long> periodSequence = new ArrayList<>();
        periodSequence.add(0L);
        periodSequence.add(1L);
        // период Пизано не может быть больше 6*m
        int i = 2;
        while (i < m * 6) {
            long temp = (periodSequence.get(i - 1) + periodSequence.get(i - 2)) % m;
            if (periodSequence.get(i - 1) == 0 && temp == 1) {
                break;
            }
            periodSequence.add(temp);
            i++;
        }
        periodSequence.remove(periodSequence.size() - 1);
        int val = (int) (n % periodSequence.size());
        return periodSequence.get(val);
    }
}
