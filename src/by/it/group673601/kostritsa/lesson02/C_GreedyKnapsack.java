package by.it.group673601.kostritsa.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            return o.cost / o.weight - this.cost / this.weight;
        }
    }

    double calc(File source) throws FileNotFoundException {
        int itemCount;
        int weightRemaining;
        Item[] items;
        try (Scanner input = new Scanner(source)) {
            itemCount = input.nextInt();
            weightRemaining = input.nextInt();
            items = new Item[itemCount];
            for (int i = 0; i < itemCount; i++) {
                items[i] = new Item(input.nextInt(), input.nextInt());
            }
        }

        // Сортируем по стоимости на единицу веса
        double result = 0;
        Arrays.sort(items);
        List<Item> itemList = Stream.of(items)
                .sorted()
                .collect(Collectors.toList());

        // берем все подряд, пока не кончится рюкзак, если не влазит целиком, отрезаем кусочек.
        for (Item item : itemList) {
            if (weightRemaining >= item.weight) {
                result += item.cost;
                weightRemaining -= item.weight;
            } else {
                result += (double) item.cost / item.weight * weightRemaining;
                break;
            }
        }

        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println(String.format("Всего предметов: %d. Рюкзак вмещает %d кг.", itemCount, weightRemaining));
        System.out.println(String.format("Удалось собрать рюкзак на сумму %f", result));
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}