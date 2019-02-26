package by.it.group673602.padhaiski.lesson01;

import java.math.BigInteger;

public class FiboB {

    private long startTime = System.currentTimeMillis();
    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        int n = 55555;
        FiboB fibo = new FiboB();
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        if(n<2){
            return BigInteger.ONE;
        }
        BigInteger[] cache = new BigInteger[n];
        cache[0] = cache[1] = BigInteger.ONE;
        for(int i=2; i<n; i++) {
            cache[i] = cache[(i - 1)].add(cache[(i - 2)]);
        }
        return cache[n-1];
    }
}