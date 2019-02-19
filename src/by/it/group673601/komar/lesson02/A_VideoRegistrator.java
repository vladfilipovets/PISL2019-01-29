package by.it.group673601.komar.lesson02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.

Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration) {
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> res;
        res = new ArrayList<>();
        int i, b = 0;
        List<Double> buf = new ArrayList<>();
        for (i = 0; i < events.length; i++) {
            res.add(events[i]);
        }
        Collections.sort(res);
        i = 0;
        buf.add(res.get(0));
        while (i != res.size() - 1) {
            for (int j = 0; j < res.size(); j++) {
                if ((res.get(i) + workDuration) >= res.get(j)) {
                    b++;
                }

            }
            if (b == res.size()) {
                break;
            }
            i = b;
            b = 0;
            buf.add(res.get(i));
        }
        return buf;
    }
}
