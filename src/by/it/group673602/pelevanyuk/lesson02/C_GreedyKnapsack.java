package by.it.group673602.pelevanyuk.lesson02;
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
import java.util.*;

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
            long r1 = (long)this.cost * o.weight;
            long r2 = (long)o.cost * this.weight;
            return -Long.compare(r1, r2);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner inputFile = new Scanner(source);
        int count = inputFile.nextInt();      //сколько предметов в файле
        int weight = inputFile.nextInt();      //какой вес у рюкзака

//        Item[] items = new Item[count];   //получим список предметов

        List<Item> items = new ArrayList<Item>(count);

        for (int i = 0; i <= count; i++) { //создавая каждый конструктором
            items.set(i,new Item(inputFile.nextInt(), inputFile.nextInt()));
        }

        Collections.sort(items);

        for (Item item : items) {
            System.out.println(item);
        }

        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",count,weight);

        double result = 0;

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group673602/pelevanyuk/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}