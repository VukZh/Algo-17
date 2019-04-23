package kruskalapp;

public class KruskalApp {

    public static void main(String[] args) {

        int[][] v1 = { // вершины
            {1, 2, 3, 4},
            {5},
            {3, 7},
            {4, 6, 7, 10},
            {5, 6},
            {9},
            {8, 9},
            {8, 10},
            {10},
            {11},};

        int[][] w1 = { // веса вершин
            {3, 3, 5, 1},
            {2},
            {1, 4},
            {2, 3, 2, 2},
            {1, 2},
            {3},
            {5, 8},
            {2, 7},
            {4},
            {7},};

        graph4kruskal G = new graph4kruskal(v1, w1); // инийиализация двумя массивами - вершин и весов

//
        Edge[] result = G.kruskal(); // вызов алгоритма Крускала

        System.out.println("Kruskal :");
        for (int i = 0; i < result.length; i++) {
            System.out.println(i + "-  " + result[i].V1 + " " + result[i].V2); // + хранится вес ребра - result[i].W
        }

//        G.displayMST(); // вывод минимального остовного дерева
    }
}
