package kruskalapp;

public class KruskalApp {

    public static void main(String[] args) {

        graph4kruskal G = new graph4kruskal(10);

        G.setArr(0, new int[]{1, 2, 3, 4}, new int[]{3, 3, 5, 1}); // 0 вершина, {1,2,3,4} - с кем связана, {3,3,5,1} - веса ребер соответсвенно
        G.setArr(1, new int[]{5}, new int[]{2});
        G.setArr(2, new int[]{3, 7}, new int[]{1, 4});
        G.setArr(3, new int[]{4, 6, 7, 10}, new int[]{2, 3, 2, 2});
        G.setArr(4, new int[]{5, 6}, new int[]{1, 2});
        G.setArr(5, new int[]{9}, new int[]{3});
        G.setArr(6, new int[]{8, 9}, new int[]{5, 8});
        G.setArr(7, new int[]{8, 10}, new int[]{2, 7});
        G.setArr(8, new int[]{10}, new int[]{4});
        G.setArr(9, new int[]{11}, new int[]{7});

        G.kruskal(); // вызов алгоритма Крускала
        G.displayMST(); // вывод минимального остовного дерева

    }
}
