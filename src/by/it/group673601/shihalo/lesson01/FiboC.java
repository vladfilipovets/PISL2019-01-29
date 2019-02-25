package by.it.group673601.shihalo.lesson01;

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
        ArrayList<Long> mass = new ArrayList<>();
        mass.add(0L); mass.add(1L);
        for(int i = 2; i < m*m-1; i++) {
            long buf = ((mass.get(i-1) + mass.get(i-2)) % m);
            if(buf == 1L && mass.get(i-1) == 0L) { mass.remove(i-1); break; }
            else { mass.add(buf); }
        }
        int num = (int) n % mass.size();
        return mass.get(num);
    }


}

