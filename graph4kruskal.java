package kruskalapp;

import java.util.Objects;

public class graph4kruskal {

    private final eWeight[][] G; // граф

    private final DArray<Edge> AllEdges; // массив всех ребер
    private final DArray<Edge> Edges; // массив ребер минимального остовного дерева

    private final DArray<Integer> Unit; // Union

    private int sizeEdges; // общее число ребер графа
    private int sizeEdgesKruskal; // число ребер минимального остовного дерева
    private int sizeVertex; // общее число вершин графа


    graph4kruskal(int[][] vertex, int[][] weight) {
        G = new eWeight[vertex.length][];
        AllEdges = new DArray<>();
        Edges = new DArray<>();
        Unit = new DArray<>();
        sizeEdges = 0;
        sizeEdgesKruskal = 0;
  
        graphInit(vertex, weight); // заполняем граф
    }

    private void graphInit(int[][] v, int[][] w) {
        for (int i = 0; i < v.length; i++) {
            eWeight[] _w = new eWeight[v[i].length];
            for (int j = 0; j < v[i].length; j++) {
                _w[j] = new eWeight(v[i][j], w[i][j]);
            }
            G[i] = _w;
        }
    }

    private void addEdge(int v1, eWeight v2) { // добавление ребра в массив всех ребер
        int v_1 = v1;
        int v_2 = v2.vertex;
        int w = v2.weight;
        AllEdges.add(sizeEdges, new Edge(v_1, v_2, w));
        sizeEdges++;
    }

    private void createArrayEdges() { // заполнение массива всех ребер
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[i].length; j++) {
                addEdge(i, G[i][j]);
            }
        }
    }

    public void displayArrayEdges() { // вывод массива всех ребер
        System.out.println("ArrayEdges");
//        createArrayEdges();
//        sortingArrayEdges();
        for (int i = 0; i < sizeEdges; i++) {
            System.out.println(i + "- " + AllEdges.get(i).V1 + " " + AllEdges.get(i).V2 + " " + AllEdges.get(i).W);
        }
    }

    private void sortingArrayEdges() { // сортировка массива всех ребер по весу
        int in, out;
        for (out = 1; out < sizeEdges; out++) {
            Edge temp = AllEdges.get(out);
            in = out;
            while (in > 0 && AllEdges.get(in - 1).W > temp.W) { //
                AllEdges.set(in, AllEdges.get(in - 1));
                --in;
            }
            AllEdges.set(in, temp);
        }
    }

    private void initUnit() { // вначале каждая вершина не объеденена с другими
        sizeVertex();
        for (int i = 0; i < sizeVertex; i++) {
            Unit.add(i, i);
        }
    }

    private void setUnit(int u1, int u2) { // объединение по минимальному значению (схлопывание)
        int min = u2;
        int max = u1;
        if (u1 < u2) {
            min = u1;
            max = u2;
        }
        for (int i = 0; i < sizeVertex; i++) {
            if (Unit.get(i) == max) {
                Unit.set(i, min);
            }
        }
    }

    private void sizeVertex() { // число вершин графа - ищем через максимальное значение из V1 или V2
        for (int i = 0; i < sizeEdges; i++) {
            if (sizeVertex < AllEdges.get(i).V1) {
                sizeVertex = AllEdges.get(i).V1;
            }
            if (sizeVertex < AllEdges.get(i).V2) {
                sizeVertex = AllEdges.get(i).V2;
            }
        }
        sizeVertex++; // +1, т.к. считаем от нуля
    }

    public Edge[] kruskal() {

        createArrayEdges(); // создаем массив ребер 
        sortingArrayEdges(); // сортируем по весу массив ребер
        initUnit(); // создаем Union-Find

        for (int i = 0; i < sizeEdges; i++) {
            if (!Objects.equals(Unit.get(AllEdges.get(i).V1), Unit.get(AllEdges.get(i).V2))) { // вершины ребра с разных Union добавляем в массив ребер минимального остовного дерева
//                System.out.println("edge:   " + "V1--- " + AllEdges.get(i).V1 + " V2--- " + AllEdges.get(i).V2);
                Edges.add(sizeEdgesKruskal, new Edge(AllEdges.get(i).V1, AllEdges.get(i).V2, AllEdges.get(i).W));
                sizeEdgesKruskal++;
//                System.out.println("-union: " + "U1--- " + Unit.get(AllEdges.get(i).V1) + " U2--- " + Unit.get(AllEdges.get(i).V2));
                setUnit(Unit.get(AllEdges.get(i).V1), Unit.get(AllEdges.get(i).V2)); // слияние
            }
        }
        Edge[] result = new Edge[sizeEdgesKruskal];
        for (int i = 0; i < sizeEdgesKruskal; i++) {
            result[i] = Edges.get(i);
        }
        return result;
    }

    public void displayMST() { // вывод минимального остовного дерева
        System.out.println("Kruskal find MST:");
        for (int i = 0; i < sizeEdgesKruskal; i++) {
            System.out.println(i + "-  " + Edges.get(i).V1 + " " + Edges.get(i).V2 + " " + Edges.get(i).W);
        }
    }
}
