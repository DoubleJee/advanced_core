package datastructure.graph;

// 图接口
public interface Graph<V, E> {

    // 顶点数量
    int verticesSize();

    // 边数量
    int edgesSize();

    // 添加顶点
    void addVertex(V v);

    // 添加边
    void addEdge(V from, V to);

    // 添加带权值的边
    void addEdge(V from, V to, E weight);

    // 删除顶点
    void removeVertex(V v);

    // 删除边
    void removeEdge(V from, V to);

}
