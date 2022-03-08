package datastructure.graph;

import datastructure.linear.queue.Queue;

import java.util.*;

// 邻接表
public class ListGraph<V, E> implements Graph<V, E> {

    // 顶点集
    Map<V, Vertex<V, E>> vertices = new HashMap<>();
    // 边集
    Set<Edge<V, E>> edges = new HashSet<>();

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public void addVertex(V v) {
        // 如果存在则不新增
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        // 创建边的时候，如果顶点不存在，则创建顶点
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }


        // 如果之前存在这个边，就替换它，更新权值，不存在，则创建这个边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        // 因为使用了hashset，所以无法替换Key，就无法更新边的权值，只能先删除，再添加
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        // 删除顶点
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

        // 删除这个顶点的每个out边
        Iterator<Edge<V, E>> outEdgeIterator = vertex.outEdges.iterator();
        while (outEdgeIterator.hasNext()) {
            Edge<V, E> edge = outEdgeIterator.next();

            outEdgeIterator.remove();
            // to方的in边
            edge.to.inEdges.remove(edge);
            edges.remove(edge);
        }

        // 删除这个顶点的每个in边
        Iterator<Edge<V, E>> inEdgeIterator = vertex.inEdges.iterator();
        while (inEdgeIterator.hasNext()) {
            Edge<V, E> edge = inEdgeIterator.next();

            inEdgeIterator.remove();
            // from方的out边
            edge.from.outEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);

        // 删除边
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void bfs(V begin) {
        // 开始顶点
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        // 遍历队列、已遍历集合
        Queue<Vertex<V, E>> queue = new Queue<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        // 入队的，都认为已经遍历了
        queue.enQueue(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            // 遍历
            Vertex<V, E> vertex = queue.deQueue();
            System.out.println(vertex);

            // 将下一层未遍历过的放入队列
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;

                // 入队就认为已经遍历过
                queue.enQueue(edge.to);
                visitedVertices.add(edge.to);
            }
        }

    }

    public void print() {
        System.out.println("--------------------------------【顶点】--------------------------------");
        vertices.forEach((value, vertex) -> {
            System.out.println(value);
            System.out.println("--------------------------------out--------------------------------");
            System.out.println(vertex.outEdges);
            System.out.println("--------------------------------in--------------------------------");
            System.out.println(vertex.inEdges);
            System.out.println();
        });

        System.out.println("--------------------------------【边】--------------------------------");
        edges.forEach(System.out::println);

    }


    // 顶点
    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        // value相同的顶点认为相同
        @Override
        public boolean equals(Object obj) {
            return Objects.equals(((Vertex<V, E>)obj).value, value);
        }

        // equals相等的，hashcode必定相等，跟随equals生成hashcode
        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    // 边
    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        // from和to都相同的边认为相同
        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(edge.from, from) && Objects.equals(edge.to, to);
        }

        // 跟equals一样，根据from和to的信息生成hashcode，* 31是奇素数，更散列均匀
        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
