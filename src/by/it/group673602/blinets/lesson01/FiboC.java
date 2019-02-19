package by.it.group673602.blinets.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

  private long startTime = System.currentTimeMillis();

  public static void main(String[] args) {
    FiboC fibo = new FiboC();
    int n = 10;
    int m = 2;
    System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
  }

  private long time() {
    return System.currentTimeMillis() - startTime;
  }

  long fasterC(long n, int m) {
    //решение практически невозможно найти интуитивно
    //вам потребуется дополнительный поиск информации
    //см. период Пизано
    ArrayList<Long> periodMAss = getPeriod(m);
    long period = periodMAss.size()-2;
    int val = (int) (n%period);
    return periodMAss.get(val);
  }

  ArrayList getPeriod(long m) {
    ArrayList<Long> periodMass = new ArrayList();
    periodMass.add(0, 0L);
    periodMass.add(1, 1L);
    for (int i = 2; i < m * 6; i++) {
      periodMass.add((periodMass.get(i - 1) + periodMass.get(i - 2)) % m);
      if (periodMass.get(i) == 1 && periodMass.get(i - 1) == 0) {
        break;
      }
    }
    return periodMass;
  }

}

