package by.it.group673601.bespalov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            Segment p = (Segment)o;

            if((start - p.start) != 0){
                return start - p.start;
            }
            else
                return stop - p.stop;
        }
    }
    private void swap(Segment[] a, int i, int j){
        Segment t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int[] division(Segment[] a, int low, int high){
        Random rand = new Random();
        int ran = low + rand.nextInt(high - low);
        int pivot;
        pivot= low;
        int pivEnd ;
        pivEnd = low;
        swap(a, pivot, ran);
        for(int i = low + 1; i <= high; i++){
            if(a[i].compareTo(a[pivot]) <= 0){
                pivEnd+=1;
                swap(a, i ,pivEnd);
                if(a[pivEnd].compareTo(a[pivot]) < 0){
                    swap(a, pivEnd, pivot);
                    pivot=pivot+1;
                }
            }
        }
        return new int[]{pivot, pivEnd};
    }

    private void quickSort3(Segment[] a, int low, int high){
        while(low < high){
            int[] middle = division(a, low, high);
            quickSort3(a, low, middle[0] - 1);
            low = middle[1] + 1;
        }
    }

    private void quickSort3(Segment[] a){
        quickSort3(a, 0, a.length-1);
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
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
            segments[index++]=new Segment(x, i);
        }

        quickSort3(segments);
        int segmentCount = 0;
        for(Segment segment : segments){
            if(segment.stop<0)
                segmentCount++;
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
