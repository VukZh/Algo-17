package kruskalapp;


public class KruskalApp {
    
    public static void main(String[] args) {
        
        graph4kruskal G = new graph4kruskal();
        
        G.set(0, 0, new eWeight(1, 3)); // ввод графа (небезопасный метод - вершины с 0, связи с 0, без проверки связей на несуществующие вершины; -1 - нет исходящих ребер)
        G.set(0, 1, new eWeight(2, 3));
        G.set(0, 2, new eWeight(3, 5));
        G.set(0, 3, new eWeight(4, 1));
        G.set(1, 0, new eWeight(5, 2));
        G.set(2, 0, new eWeight(3, 1));
        G.set(2, 1, new eWeight(7, 4));
        G.set(3, 0, new eWeight(4, 2));
        G.set(3, 1, new eWeight(6, 3));
        G.set(3, 2, new eWeight(7, 2));
        G.set(3, 3, new eWeight(10, 2));
        G.set(4, 0, new eWeight(5, 1));
        G.set(4, 1, new eWeight(6, 2));
        G.set(5, 0, new eWeight(9, 3));
        G.set(6, 0, new eWeight(8, 5));
        G.set(6, 1, new eWeight(9, 8));
        G.set(7, 0, new eWeight(8, 2));
        G.set(7, 1, new eWeight(10, 7));
        G.set(8, 0, new eWeight(10, 4));
        G.set(9, 0, new eWeight(11, 7));      
        
        G.kruskal();
        G.displayMST();
        
    }
    
}
