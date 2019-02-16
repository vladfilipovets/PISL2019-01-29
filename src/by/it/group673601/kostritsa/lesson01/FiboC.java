package by.it.group673601.kostritsa.lesson01;

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
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        List<Long> sequencePeriod = getSequencePeriod(m);
        long period = sequencePeriod.size(); // находим период Пизано
        int val = (int)(n % period);
        return sequencePeriod.get(val);
    }

    private static List<Long> getSequencePeriod(long m){
        List<Long> p = new ArrayList<>();
        p.add((long)0);
        p.add((long)1);
        for(int i = 2; i < m * 6; i++){
            p.add((p.get(i - 1) + p.get(i - 2)) % m);
            if(p.get(i) == 1 && p.get(i-1) == 0){
                break;
            }
        }
        p.remove(p.size()-1);
        p.remove(p.size()-1);
        return p;
    }

}