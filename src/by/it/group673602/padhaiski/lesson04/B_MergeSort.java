package by.it.group673602.padhaiski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)
Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.
Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt(); // size of array
        int[] array =new int[n];
        System.out.println("Array before sorting: ");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            System.out.print(array[i] + " ");
        }
        System.out.println("\n" + "Array after sorting: ");
        int[] buffer1 = Arrays.copyOf(array,array.length);
        int[] buffer2 = new int[buffer1.length];
        array = mergeSort(buffer1, buffer2, 0, array.length);
        return array;
    }

    private int[] mergeSort(int[] buffer1, int[] buffer2, int startIndex, int lastIndex) {
        if(startIndex>=lastIndex - 1){
            return buffer1;
        }
        int middleIndex = startIndex + (lastIndex - startIndex)/2;
        int[] firstArray = mergeSort(buffer1, buffer2, startIndex, middleIndex);
        int[] secondArray = mergeSort(buffer1, buffer2, middleIndex, lastIndex);

        int index1 = startIndex, index2 = middleIndex, destIndex = startIndex;
        int[] result = (firstArray == buffer1) ? buffer2 : buffer1;
        while (index1 < middleIndex && index2 < lastIndex){
            result[destIndex++] = (firstArray[index1] < secondArray[index2])
                    ? firstArray[index1++] : secondArray[index2++];
        }
        while (index1 < middleIndex){
            result[destIndex++] = firstArray[index1++];
        }
        while (index2<lastIndex){
            result[destIndex++] = secondArray[index2++];
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group673602/prakhotsky/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}

