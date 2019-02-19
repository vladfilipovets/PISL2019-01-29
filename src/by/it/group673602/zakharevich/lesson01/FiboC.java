package by.it.group673602.zakharevich.lesson01;

import java.util.ArrayList;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

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
        ArrayList<Long> numbs = getSequencePeriod(m);
        String period = "01";
        double value = 0;
        for(int i = 2; i <= m*6; i++){
            numbs.add(((numbs.get(i-1) + numbs.get((i-2))) % m));
            if(numbs.get(i) == 1 && numbs.get(i-1) == 0){
                break;
            }
        }
        numbs.remove(numbs.size()-1);
        numbs.remove(numbs.size()-1);
        long s = numbs.size();
        return numbs.get((int) (n%s));
    }

    private ArrayList<Long> getSequencePeriod(long m){
        ArrayList<Long> numbs = new ArrayList<>();
        numbs.add((long)0);
        numbs.add((long)1);
        for(int i = 2; i <= m*6; i++){
            numbs.add(((numbs.get(i-1) + numbs.get((i-2))) % m));
            if(numbs.get(i) == 1 && numbs.get(i-1) == 0){
                break;
            }
        }
        return numbs;
    }

}

