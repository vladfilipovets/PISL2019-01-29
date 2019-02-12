package by.it.group673601.shihalo.lesson02;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.

Алгоритм жадный. Для реализации обдумайте надежный шаг.

*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                            new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                            new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                            new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                            new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                            new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
                          };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result;
        result = new ArrayList<>();
        Arrays.sort(events, (e1, e2)->{ return Integer.compare(e1.start, e2.start); });
        //for(Event event : events) { System.out.println(event); }
        for(Event event : events) { if(event.start >= from) { result.add(event); break; } }
        for(Event event : events) {
            if (event.start >= from) {
                if (event.start > to) { break; }
                if (event.stop <= to) {
                    if (event.start >= result.get(result.size() - 1).stop) { result.add(event); }
                    if (event.stop < result.get(result.size() - 1).stop) { result.set(result.size() - 1, event); }
                }
            }
        }
        return result;                        //вернем итог
    }
}
