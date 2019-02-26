package by.it.group673602.pelevanyuk.lesson02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration){

        List<Double> result;
        result = new ArrayList<>();
        List<Double> predictedEvents;
        predictedEvents = new ArrayList<>();

        int index = 0;

        for(double event : events){
            predictedEvents.add(event);
        }

        predictedEvents.sort(Comparator.naturalOrder());

        double timeWorkDuration  = predictedEvents .get(0);

        result.add(timeWorkDuration);

        timeWorkDuration =timeWorkDuration + workDuration;

        for (double time : predictedEvents) {
            if (time > timeWorkDuration) {

                result.add(time);

                timeWorkDuration  = time;

                timeWorkDuration = timeWorkDuration + workDuration;
            }
        }
        return result;
    }
}