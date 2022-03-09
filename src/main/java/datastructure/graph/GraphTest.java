package datastructure.graph;

public class GraphTest {

    public static void main(String[] args) {
//        test();
//        testBfs();
        testDfs();
    }


    static void test() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);

        graph.removeVertex("V2");
        graph.removeEdge("V1", "V0");

        graph.print();

        graph.bfs("V0");
    }

    static void testBfs() {
        Graph<Object, Double> graph = createDirectedGraph(Data.BFS_02);
        graph.bfs(0);
    }

    static void testDfs() {
        Graph<Object, Double> graph = createUndirectedGraph(Data.DFS_02);
        graph.dfs("a");
    }

    // 无向图
    void undirectedGraph() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V0", "V1");
        graph.addEdge("V1", "V0");

        graph.addEdge("V0", "V2");
        graph.addEdge("V2", "V0");

        graph.addEdge("V0", "V3");
        graph.addEdge("V3", "V0");

        graph.addEdge("V1", "V2");
        graph.addEdge("V2", "V1");

        graph.addEdge("V2", "V3");
        graph.addEdge("V3", "V2");

    }

    // 生成有向图
    static Graph<Object, Double> createDirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            // 长度为1，只创建顶点
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                // 带权值的边
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }

        return graph;
    }

    // 生成无向图
    static Graph<Object, Double> createUndirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            // 长度为1，只创建顶点
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                // 无向图，两个方向的边都要添加
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                // 带权值的边
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }


}
