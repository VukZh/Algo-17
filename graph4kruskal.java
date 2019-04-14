package kruskalapp;

import java.util.Objects;

public class graph4kruskal {

    private final DArray<DArray<eWeight>> G; // граф
    private final DArray<Edge> AllEdges; // массив всех ребер
    private final DArray<Edge> Edges; // массив ребер минимального остовного дерева
    private final DArray<Integer> Unit; // Union
    private int sizeEdges; // общее число ребер графа
    private int sizeEdgesKruskal; // число ребер минимального остовного дерева
    private int sizeVertex; // общее число вершин графа

    graph4kruskal() {
        G = new DArray<>();
        AllEdges = new DArray<>();
        Edges = new DArray<>();
        Unit = new DArray<>();
        sizeEdges = 0;
        sizeVertex = 0;
        sizeEdgesKruskal = 0;
    }

    graph4kruskal(int size) {

        G = new DArray<>();
        DArray<eWeight> tmp = new DArray<>();
        tmp.add(0, null);
        for (int i = 0; i < size; i++) {
            G.add(i, tmp);
        }

        AllEdges = new DArray<>();
        Edges = new DArray<>();
        Unit = new DArray<>();
        sizeEdges = 0;
        sizeVertex = size;
        sizeEdgesKruskal = 0;
    }

    public void set(int g_i, int el_i, eWeight ew) { // установка для матрицы вектора смежности (g_i - вершина, el_i индекс массива вершин куда уходят ребра, ew - объект - вершина на которую можно уйти с g_i с весом ребра)
        DArray<eWeight> tmp;
        if (el_i == 0) {
            tmp = new DArray<>();
        } else {
            tmp = G.get(g_i);
        }
        tmp.add(el_i, ew);
        G.add(g_i, tmp);
    }

    public void setArr(int v, int[] vertex, int[] weight) { // 
        int g_i = v;
        int sizeArr = vertex.length;
        for (int el_i = 0; el_i < sizeArr; el_i++) {
            set(g_i, el_i, new eWeight(vertex[el_i], weight[el_i]));
        }
    }

    private eWeight get(int g_i, int el_i) { // получение вершины с весом из матрицы вектора смежности (g_i - вершина с которой идет связь на нашу вершину, el_i индекс массива вершин для вершины g_i)
        DArray<eWeight> tmp = G.get(g_i);
        return tmp.get(el_i);
    }

    private int sizeV() { // размер матрицы вектора смежности
        return G.size();
    }

    private int sizeS(int v) { // число вершин, на которые есть связь с вершины v
        DArray<eWeight> tmp = G.get(v);
        return tmp.size();
    }

    private void addEdge(int v1, eWeight v2) { // добавление ребра в массив всех ребер
//        DArray<Edge> tmp;
        int v_1 = v1;
        int v_2 = v2.vertex;
        int w = v2.weight;
        AllEdges.add(sizeEdges, new Edge(v_1, v_2, w));
        sizeEdges++;
    }

    private void createArrayEdges() { // заполнение массива всех ребер
        for (int i = 0; i < sizeV(); i++) {
            for (int j = 0; j < sizeS(i); j++) {
                addEdge(i, get(i, j));
            }
        }
    }

    public void displayArrayEdges() { // вывод массива всех ребер
        System.out.println("ArrayEdges");
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

    public void kruskal() {

        createArrayEdges(); // создаем массив ребер        
//        displayArrayEdges();
        sortingArrayEdges(); // сортируем по весу массив ребер
//        displayArrayEdges();
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
    }

    public void displayMST() { // вывод минимального остовного дерева
        System.out.println("Kruskal find MST:");
        for (int i = 0; i < sizeEdgesKruskal; i++) {
            System.out.println(i + "-" + Edges.get(i).V1 + " " + Edges.get(i).V2 + " " + Edges.get(i).W);
        }
    }

}
