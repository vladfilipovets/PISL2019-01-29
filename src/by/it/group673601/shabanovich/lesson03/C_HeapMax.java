package by.it.group673601.shabanovich.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private class MaxHeap<T extends Comparable<T>> {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение.
        //Будет мало? Ну тогда можете его собрать как Generic и/или использовать в варианте B
        private static final int DEFAULT_SIZE = 16;
        private static final double DEFAULT_OCCUPANCY = 0.75;

        private int currentMemorySize = DEFAULT_SIZE;

        private Object[] heap = new Object[DEFAULT_SIZE];
        private int size = 0;

        @SuppressWarnings("unchecked")
        void siftDown(int i) {
            while (2 * i + 1 < size) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int j = left;
                if (right < size && ((T) heap[right]).compareTo((T) heap[left]) < 0) {
                    j = right;
                }
                if (((T) heap[i]).compareTo((T) heap[j]) >= 0) {
                    break;
                }
                swap(i, j);
                i = j;
            }
        }

        @SuppressWarnings("unchecked")
        void siftUp(int i) {
            while (((T) heap[i]).compareTo((T) heap[(i - 1) / 2]) > 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        void insert(T value) {
            heap[size] = value;
            size++;
            siftUp(size - 1);
            if ((double) size / currentMemorySize > DEFAULT_OCCUPANCY) {
                resize();
            }
        }

        void swap(int i, int j) {
            Object temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        @SuppressWarnings("unchecked")
        T extractMax() { //извлечение и удаление максимума
            T max = (T) heap[0];
            heap[0] = heap[size - 1];
            heap[size - 1] = null;
            size--;
            siftDown(0);
            return max;
        }

        void resize() {
            heap = Arrays.copyOf(heap, currentMemorySize * 2);
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap<Long> heap = new MaxHeap<>();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
                //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group673601/kostritsa/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
