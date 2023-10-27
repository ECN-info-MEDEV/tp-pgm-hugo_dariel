package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.ObjetGraph.Node;
import java.util.LinkedList;
import java.util.Queue;

public class Dinic extends Algorithm implements AlgorithmST, Processing {

    public Dinic() {
        super();
        this.setName("Dinic Max Flow");
    }

    @Override
    public void process(Draw d) {
        d.setStatus(Draw.ALGO_INPUT);
    }

    @Override
    public void process(Draw d, Node srcNode, Node destNode) {
        int src = d.getG().getNodeId(srcNode);
        int dest = d.getG().getNodeId(destNode);
        int maxFlow;

        FlowGraph graph = new FlowGraph(d.getG().getNbsommets());

        maxFlow = dinicMaxFlow(graph, src, dest);

        if (maxFlow >= 0) {
            d.setResultat("Flux Máximo: " + maxFlow);
        } else {
            d.setResultat("Não existe um fluxo válido entre os vértices " +
                    d.getNodes().get(src).getLabel() + " et " + d.getNodes().get(dest).getLabel() + ".");
        }
    }

    // Structure d'une arête dans un réseau de flux.
    static class Edge {
        int to, capacity, flow;
        Edge reverse;

        Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
        }
    }

    // Structure de graphe de flux.
    static class FlowGraph {
        int V;
        LinkedList<Edge>[] adj;

        FlowGraph(int V) {
            this.V = V;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        // Ajoute une arête avec capacité au réseau de flux.
        void addEdge(int u, int v, int capacity) {
            Edge forward = new Edge(v, capacity);
            Edge backward = new Edge(u, 0); // Flux initial est zéro
            forward.reverse = backward;
            backward.reverse = forward;
            adj[u].add(forward);
            adj[v].add(backward);
        }
    }

    static final int INF = Integer.MAX_VALUE;

    // Fonction de recherche de chemin BFS (Breadth-First Search).
    static boolean bfs(FlowGraph graph, int source, int sink, int[] dist) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        dist[source] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : graph.adj[u]) {
                if (dist[edge.to] == INF && edge.flow < edge.capacity) {
                    queue.add(edge.to);
                    dist[edge.to] = dist[u] + 1;
                }
            }
        }

        return dist[sink] != INF;
    }

    // Fonction pour augmenter le flux le long d'un chemin dans le réseau résiduel.
    static int dfs(FlowGraph graph, int[] dist, int u, int sink, int minFlow) {
        if (u == sink) {
            return minFlow;
        }

        for (Edge edge : graph.adj[u]) {
            if (dist[edge.to] == dist[u] + 1 && edge.flow < edge.capacity) {
                int flow = dfs(graph, dist, edge.to, sink, Math.min(minFlow, edge.capacity - edge.flow));
                if (flow > 0) {
                    edge.flow += flow;
                    edge.reverse.flow -= flow;
                    return flow;
                }
            }
        }

        return 0;
    }

    // Algorithme de Dinic pour trouver le flux maximal dans le réseau.
    static int dinicMaxFlow(FlowGraph graph, int source, int sink) {
        int maxFlow = 0;
        int[] dist = new int[graph.V];

        while (bfs(graph, source, sink, dist)) {
            int flow;
            while ((flow = dfs(graph, dist, source, sink, INF)) > 0) {
                maxFlow += flow;
            }
        }

        return maxFlow;
    }
}

