package by.it.group673601.bespalov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {


    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        private Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            if((start - o.start) != 0){
                return start - o.start;
            }
            else
                return stop - o.stop;
        }
    }
    private int partition(Segment[] a, int low, int high){
        Segment x = a[low];
        int m = low;
        for(int i = low+1; i <= high; i++){
            if(a[i].compareTo(x)<=0){
                m++;
                Segment temp = a[i];
                a[i] = a[m];
                a[m] = temp;
            }
        }
        Segment temp = a[low];
        a[low] = a[m];
        a[m] = temp;
        return m;
    }

    private void quickSort(Segment[] a, int low, int high){
        if(low<high){
            int m = partition(a, low, high);
            quickSort(a, low, m-1);
            quickSort(a, m+1, high);
        }
    }

    private void quickSort(Segment[] a){
        quickSort(a, 0, a.length-1);
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Segment[] segments=new Segment[n+n+m];
        int[] result=new int[m];

        //читаем сами отрезки
        int index=0;
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int start, stop;
            start=scanner.nextInt();
            stop = scanner.nextInt();
            if(start > stop){
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            segments[index++]=new Segment(start, -1);
            segments[index++] = new Segment(stop, m+1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            segments[index++]=new Segment(x, i);//добавляем начало события и его номер
        }

        quickSort(segments);
        int segmentCount = 0;
        for(Segment segment : segments){
            if(segment.stop<0)
                segmentCount+=1;
            else if (segment.stop>m)
                segmentCount--;

            else
                result[segment.stop] = segmentCount;
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
