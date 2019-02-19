package by.it.group673602.blinets.lesson02;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class C_GreedyKnapsack {

  public static void main(String[] args) throws FileNotFoundException {
    long startTime = System.currentTimeMillis();
    String root = System.getProperty("user.dir") + "/src/";
    File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
    double costFinal = new C_GreedyKnapsack().calc(f);
    long finishTime = System.currentTimeMillis();
    System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
  }

  double calc(File source) throws FileNotFoundException {
    Scanner input = new Scanner(source);
    int n = input.nextInt();      //сколько предметов в файле
    int W = input.nextInt();      //какой вес у рюкзака
    Item[] items = new Item[n];   //получим список предметов
    for (int i = 0; i < n; i++) { //создавая каждый конструктором
      items[i] = new Item(input.nextInt(), input.nextInt());
    }
    //покажем предметы
    for (Item item : items) {
      System.out.println(item);
    }
    System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

    //тут необходимо реализовать решение задачи
    //итогом является максимально воможная стоимость вещей в рюкзаке
    //вещи можно резать на кусочки (непрерывный рюкзак)
    double result = 0;
    //тут реализуйте алгоритм сбора рюкзака
    //будет особенно хорошо, если с собственной сортировкой
    //кроме того, можете описать свой компаратор в классе Item
    //ваше решение.

    Map<Double, Item> CostIteam = new HashMap<>();   // предметы по соотношению веса и стоимости
    for (Item item : items) {
     CostIteam.put((double) (item.cost / item.weight), item);
    }
    Map<Double, Item> optimalIteam = new HashMap<Double, Item>();  // самы дорогой предмет по соотношению веса и стоимости
    CostIteam.entrySet().stream().sorted(Map.Entry.<Double, Item>comparingByKey().reversed())
        .forEach(doubleItemEntry -> optimalIteam
            .put(doubleItemEntry.getKey(), doubleItemEntry.getValue()));
    Double key;
    key = optimalIteam.keySet().iterator().next();

    Double optimalWeightIteamBackpack = Double.valueOf(W / n);    // оптимальный вес для заполнения рюкзака

    Double optimalCost;       // стоимость предмета после деления
    if (optimalWeightIteamBackpack < optimalIteam.get(key).weight) {
      optimalCost =
          (optimalWeightIteamBackpack * optimalIteam.get(key).cost) / optimalIteam.get(key).weight;
      result = optimalCost * n;
    }

    System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
    return result;
  }

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
      //тут может быть ваш компаратор

      return 0;
    }
  }
}