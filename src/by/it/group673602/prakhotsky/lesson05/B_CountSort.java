package by.it.group673602.prakhotsky.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt(); // size of array
        int[] points=new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }
        int min, max = min = points[0];
        max = getMaxElement(points);
        min = getMinElement(points);
        sort(points, min, max);
        return points;
    }

    private void sort(int[] array, int min, int max) {
        int[] count = new int[max - min+1];
        for(int i =0; i < array.length; i++){
            count[array[i] - min]++;
        }
        int index = 0;
        for(int i =0; i < count.length; i++){
            for(int j=0; j <count[i]; j++){
                array[index++] = i + min;
            }
        }
    }

    private int getMaxElement(int[] array) {
        int max = array[0];
        for(int i = 1; i<array.length;i++){
            if(array[i]>max){
                max = array[i];
            }
        }
        return max;
    }

    private int getMinElement(int[] array) {
        int min = array[0];
        for(int i = 1; i<array.length;i++){
            if(array[i]<min){
                min = array[i];
            }
        }
        return min;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group673602/prakhotsky/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
