import java.io.*;
import java.util.*;

class Graph {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adj; // Adjacency List
    static int count = 0, time = 0;

    class Edge {
        int u, v;
        Edge(int u, int v) {    
            this.u = u;
            this.v = v;
        }
    }

    // Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void BCCUtil(int u, int disc[], int low[], LinkedList<Edge> st, int parent[]) {
        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj[u]) {
            if (disc[v] == -1) { // If v is not visited
                children++;
                parent[v] = u;
                st.add(new Edge(u, v));
                BCCUtil(v, disc, low, st, parent);

                low[u] = Math.min(low[u], low[v]);

                // Check for articulation point
                if ((disc[u] == 1 && children > 1) || (disc[u] > 1 && low[v] >= disc[u])) {
                    System.out.print("Biconnected Component: ");
                    while (!st.isEmpty() && (st.getLast().u != u || st.getLast().v != v)) {
                        System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
                        st.removeLast();
                    }
                    System.out.println(u + "--" + v);
                    st.removeLast();
                    count++;
                }
            }
            else if (v != parent[u] && disc[v] < disc[u]) {
                low[u] = Math.min(low[u], disc[v]);
                st.add(new Edge(u, v));
            }
        }
    }

    void BCC() {
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        LinkedList<Edge> st = new LinkedList<>();

        // Initialize arrays
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        // Call BCCUtil for all unvisited vertices
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                BCCUtil(i, disc, low, st, parent);
                
                // Print remaining edges as a component
                if (!st.isEmpty()) {
                    System.out.print("Biconnected Component: ");
                    while (!st.isEmpty()) {
                        System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
                        st.removeLast();
                    }
                    System.out.println();
                    count++;
                }
            }
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(12);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 3);
        g.addEdge(3, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 2);
        g.addEdge(2, 4);
        g.addEdge(4, 2);
        g.addEdge(3, 4);
        g.addEdge(4, 3);
        g.addEdge(1, 5);
        g.addEdge(5, 1);
        g.addEdge(0, 6);
        g.addEdge(6, 0);
        g.addEdge(5, 6);
        g.addEdge(6, 5);
        g.addEdge(5, 7);
        g.addEdge(7, 5);
        g.addEdge(5, 8);
        g.addEdge(8, 5);
        g.addEdge(7, 8);
        g.addEdge(8, 7);
        g.addEdge(8, 9);
        g.addEdge(9, 8);
        g.addEdge(10, 11);
        g.addEdge(11, 10);

        // Reset count and time before running
        count = 0;
        time = 0;
        
        g.BCC();
        System.out.println("Total number of biconnected components: " + count);
    }
}
