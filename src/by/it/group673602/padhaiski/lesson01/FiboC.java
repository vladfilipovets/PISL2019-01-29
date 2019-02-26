package by.it.group673602.padhaiski.lesson01;

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
        ArrayList<Long> numbs = getSequencePeriod(m);
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