package by.it.group673602.padhaiski.lesson05;

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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if(stop<start){
                this.start = stop;
                this.stop = start;
            }
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            long r1 = (long)this.start;
            long r2 = (long)o.start;
            return Long.compare(r1, r2);
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getStop() {
            return stop;
        }

        public void setStop(int stop) {
            this.stop = stop;
        }
    }

    private void quiskSort(Segment[] array, int low, int hight){
        if(array.length<2){
            return;
        }
        if(low>=hight){
            return;
        }
        int mid = low + (hight-low)/2;
        Segment opora = array[mid];
        int i = low, j = hight;
        while (i<=j){
            while (array[i].start < opora.start){
                i++;
            }
            while (array[j].start > opora.start){
                j--;
            }
            if(i<=j){
                Segment temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
            if(low < j){
                quiskSort(array,low, j);
            }
            if(hight > i){
                quiskSort(array,i,hight);
            }
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt(); // number of segments
        Segment[] segments=new Segment[n];
        int m = scanner.nextInt(); // number of events
        int[] points=new int[m];
        int[] result=new int[m];
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        quiskSort(segments, 0, segments.length-1);
        for(int j = 0; j < points.length; j++) {
            for (int i = 0; i < segments.length; i++) {
                if(points[j] >= segments[i].getStart() && points[j] <= segments[i].getStop()){
                    result[j]++;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group673602/prakhotsky/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}