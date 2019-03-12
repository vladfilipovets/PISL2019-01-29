package by.it.group673601.shihalo.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if(start < stop) {
                this.start = start;
                this.stop = stop;
            }
            else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, o.start);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
            //System.out.println(points[i]);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        for (int i=0;i < points.length;i++) {
            int count = 0;
            for (Segment segment: segments){
                if (points[i]<segment.start) {
                    break;
                }
                if (points[i]<=segment.stop && points[i]>=segment.start){
                    count++;
                }
            }
            result[i]=count;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    void quicksort(Segment a[], int l, int r) {
        Segment v = a[r];
        if (r <=l) { return; }
        int i = l;
        int j = r - 1;
        int p = l - 1;
        int q = r;
        while (i <= j) {
            while (a[i].compareTo(v) > 0) { i++; }
            while (a[j].compareTo(v) > 0 ) { j--; }
            if (i >= j) { break; }
            swap (a[i], a[j]);
            if (a[i] == v) { p++; swap(a[p], a[i]); }
            i++;
            if (a[j] == v) { q--; swap(a[q], a[j]); }
            j--;
        }
        swap(a[i], a[r]);
        j = i - 1;
        i++;
        for (int k = l; k <= p; k++, j--) { swap(a[k], a[j]); }
        for (int k = r - 1; k >= q; k--, i++) { swap(a[k], a[i]); }
        quicksort(a, l, j);
        quicksort(a, i, r);
    }

    private void swap(Segment s1, Segment s2) {
        Segment swapTemp = s1;
        s1 = s2;
        s2 = swapTemp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
