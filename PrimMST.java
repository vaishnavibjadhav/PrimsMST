import java.util.*;

class PrimMST {
    static class Edge implements Comparable<Edge> {
        int dest, weight;
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class Graph {
        int V;
        List<List<Edge>> adj;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int src, int dest, int weight) {
            adj.get(src).add(new Edge(dest, weight));
            adj.get(dest).add(new Edge(src, weight)); // Undirected Graph
        }

        void primMST() {
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            boolean[] inMST = new boolean[V];
            int[] parent = new int[V];
            int[] key = new int[V];
            Arrays.fill(key, Integer.MAX_VALUE);
            key[0] = 0; 
            parent[0] = -1;

            pq.add(new Edge(0, 0));

            while (!pq.isEmpty()) {
                int u = pq.poll().dest;
                inMST[u] = true;

                for (Edge edge : adj.get(u)) {
                    int v = edge.dest;
                    int weight = edge.weight;
                    if (!inMST[v] && weight < key[v]) {
                        key[v] = weight;
                        pq.add(new Edge(v, weight));
                        parent[v] = u;
                    }
                }
            }

            System.out.println("Edge   Weight");
            for (int i = 1; i < V; i++) {
                System.out.println("V" + (parent[i] + 1) + " - V" + (i + 1) + "    " + key[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 11;
        Graph g = new Graph(V);

        // Adding edges based on your table
        g.addEdge(0, 4, 386);
        g.addEdge(1, 5, 434);
        g.addEdge(2, 9, 199);
        g.addEdge(3, 4, 349);
        g.addEdge(4, 6, 298);
        g.addEdge(5, 8, 194);
        g.addEdge(6, 7, 193);
        g.addEdge(7, 9, 177);
        g.addEdge(8, 10, 137);
        g.addEdge(9, 10, 84);

        g.primMST();
    }
}