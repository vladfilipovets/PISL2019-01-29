package by.it.group673601.vershok.lesson01.lesson05;

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

    private int I = 0, J = 0;

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return (o.stop - o.start) - (stop - start);
        }

    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, segments.length - 1);

        for (int i = 0; i < points.length; i++) {
            int count = 0;
            int low = 0;
            int high = segments.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (points[i] <= segments[mid].stop && points[i] >= segments[mid].start) {
                    count++;
                    break;
                } else if (segments[mid].stop < points[i]) {
                    low = mid + 1;
                } else if (segments[mid].stop > points[i]) {
                    high = mid - 1;
                }
            }
            result[i] = count;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    void partition(Segment a[], int l, int r) {
        I = l - 1;
        J = r;
        int p = l - 1, q = r;
        Segment v = a[r];

        while (true) {

            while (v.compareTo(a[++I]) > 0) ;


            while (v.compareTo(a[--J]) >= 0)
                if (J == l)
                    break;


            if (I >= J) break;

            swap(a[I], a[J]);


            if (a[I] == v) {
                p++;
                swap(a[p], a[I]);
            }

            if (a[J] == v) {
                q--;
                swap(a[J], a[q]);
            }
        }

        swap(a[I], a[r]);

        J = I - 1;
        for (int k = l; k < p; k++, J--)
            swap(a[k], a[J]);

        I = I + 1;
        for (int k = r - 1; k > q; k--, I++)
            swap(a[I], a[k]);
    }

    void quickSort(Segment a[], int l, int r) {
        if (r <= l) return;

        partition(a, l, r);

        quickSort(a, l, J);
        quickSort(a, I, r);
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
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
