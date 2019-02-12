package by.it.group673602.blinets.lesson02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.

Алгоритм жадный. Для реализации обдумайте надежный шаг.

*/

public class B_Sheduler {

  public static void main(String[] args) {
    B_Sheduler instance = new B_Sheduler();
    Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
        new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
        new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
        new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
        new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
        new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
    };

    List<Event> starts = instance
        .calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
    System.out
        .println(starts);                                 //покажем рассчитанный график занятий
  }

  List<Event> calcStartTimes(Event[] events, int from, int to) {
    //events - события которые нужно распределить в аудитории
    //в период [from, int] (включительно).
    //оптимизация проводится по наибольшему числу непересекающихся событий.
    //начало и конец событий могут совпадать.
    List<Event> result;
    result = new ArrayList<>();
    //ваше решение.

    Map<Integer, Integer> mapDistance = new HashMap(); // мапа с временем выполнения всех действий
    for (int i = 0; i < events.length; i++) {
      mapDistance.put(i, events[i].stop - events[i].start);
    }

    Map<Integer, Integer> mapStartEvent = new HashMap();  // мапа времени начала всех действтий
    for (int i = 0; i < events.length; i++) {
      mapStartEvent.put(i, events[i].start);
    }

    for (int i = 0; from < to; i++) {
      List<Integer> elements = new ArrayList<>();  // индексы событий которые начинаются в момент from
      while (true) {
        for (Map.Entry<Integer, Integer> entry : mapStartEvent.entrySet()) {
          if (entry.getValue() == from) {
            elements.add(entry.getKey());
          }
        }
        if (from >= to) {
          return result;
        }
        if (elements.isEmpty()) {
          from++;
        } else {
          break;
        }
      }

      Integer minDinstance = mapDistance.get(elements.get(0)); //мин время выполнения ивента
      result.add(i, events[elements.get(0)]);
      Integer elementsDo = elements.get(0);  // индекс ивента который будет выполнятся
      for (Integer element : elements) {
        if (mapDistance.get(element) < minDinstance) {
          result.set(i, events[element]);
          minDinstance = mapDistance.get(element);
          elementsDo = element;
        }
      }
      from = events[elementsDo].stop;
      System.out.println(result);
    }
    return result;                        //вернем итог
  }

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
      return "(" + start + ":" + stop + ")";
    }
  }
}
