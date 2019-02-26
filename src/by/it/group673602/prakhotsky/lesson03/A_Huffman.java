package by.it.group673602.prakhotsky.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//Lesson 3. A_Huffman.
//Разработайте метод encode(File file) для кодирования строки (код Хаффмана)

// По данным файла (непустой строке ss длины не более 104104),
// состоящей из строчных букв латинского алфавита,
// постройте оптимальный по суммарной длине беспрефиксный код.

// Используйте Алгоритм Хаффмана — жадный алгоритм оптимального
// безпрефиксного кодирования алфавита с минимальной избыточностью.

// В первой строке выведите количество различных букв kk,
// встречающихся в строке, и размер получившейся закодированной строки.
// В следующих kk строках запишите коды букв в формате "letter: code".
// В последней строке выведите закодированную строку. Примеры ниже

//        Sample Input 1:
//        a
//
//        Sample Output 1:
//        1 1
//        a: 0
//        0

//        Sample Input 2:
//        abacabad
//
//        Sample Output 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

public class A_Huffman {
    class Node implements Comparable<Node> {
        final int frequence; //частота символов
        String code;
        void fillCodes(String code){
            this.code = code;
        }
        private Node(int frequence) {
            this.frequence = frequence;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }
    private class InternalNode extends Node {
        Node left;  //левый ребенок бинарного дерева
        Node right; //правый ребенок бинарного дерева
        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }
        @Override
        void fillCodes(String code) {
            super.fillCodes(code);
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }
    }

    private class LeafNode extends Node {
        //лист
        char symbol; //символы хранятся только в листах
        LeafNode(char symbol, int frequence) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            super.fillCodes(code);
            System.out.println(this.symbol +": " + code);
        }
    }

    static private Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();

        Map<Character, Integer> count = new HashMap<>();

        // Считаю кол-во каждого символа
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            if(count.containsKey(c)){
                count.put(c, count.get(c)+1);
            }else {
                count.put(c,1);
            }
        }
        Map<Character, Node> charNodes = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry : count.entrySet()){
            LeafNode node = new LeafNode(entry.getKey(), entry.getValue());
            charNodes.put(entry.getKey(),node);
            priorityQueue.add(node);
        }
        int sum = 0;
        while (priorityQueue.size()>1){
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            InternalNode node = new InternalNode(first,second);
            sum += node.frequence;
            priorityQueue.add(node);
        }
        System.out.println(count.size() + " " + sum);
        Node root = priorityQueue.poll();
        root.fillCodes("");
        String  sb = "";
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            sb += charNodes.get(c).code;
        }
        return sb;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group673602/prakhotsky/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}
