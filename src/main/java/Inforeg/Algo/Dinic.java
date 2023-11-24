package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import Inforeg.ObjetGraph.Arc;
import Inforeg.ObjetGraph.Node;
import java.awt.Color;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Dinic extends Algorithm implements AlgorithmST, Processing {

    public Dinic() {
        super();
        this.setName("Dinic");
    }

    @Override
    public void process(Draw d) {
        d.setStatus(Draw.ALGO_INPUT);
    }
    
    @Override
    public void process(Draw d, Node srcNode, Node destNode) {
    int src = d.getG().getNodeId(srcNode);
    int dest = d.getG().getNodeId(destNode);
    Graph g = d.getG();
    g.updateVariable();
    int V = g.getNbsommets();

    // Residual graph
    int[][] rGraph = new int[V][V];
    for (int u = 0; u < V; u++) {
        for (int v = 0; v < V; v++) {
            rGraph[u][v] = g.getAdjMatrix()[u][v];
        }
    }

    // 'level' array to store the level of each node
    int[] level = new int[V];

    int max_flow = 0;

    // While there is a path from source to sink
    while (bfsLevelGraph(rGraph, src, dest, level, V)) {
        // Find blocking flow using DFS or similar method
        int flow;
        do {
            flow = sendFlow(src, Integer.MAX_VALUE, dest, level, rGraph, V);
            max_flow += flow;
        } while (flow > 0);
    }

        // Code to update the visualization and result

    // Update the flow on each edge in the visualization
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (g.getAdjMatrix()[i][j] > 0) { // There is an edge from i to j
                int flowUsed = g.getAdjMatrix()[i][j] - rGraph[i][j];
                Arc arc = d.findLine(i, j);
                if (arc != null) {
                    arc.setFlow(flowUsed); // Set the flow used in this edge
                    // Update the color of the edge based on the flow
                    if (flowUsed > 0) {
                        arc.setColorDisplayed(Color.GREEN);
                    } else {
                        arc.setColorDisplayed(Color.RED);
                    }
                }
            }
        }
    }

    // Update the result label with the max flow value
    String resultText = String.format("Le flot maximal du graphe entre les sommets %s et %s est de %d.",
                                      d.getNodes().get(src).getLabel(),
                                      d.getNodes().get(dest).getLabel(),
                                      max_flow);
    d.setResultat(resultText);

    // Indicate that the algorithm has finished
    d.algoFinished();

}

// BFS method to construct level graph
private boolean bfsLevelGraph(int[][] rGraph, int src, int dest, int[] level, int V) {
    // Initialize level array with -1
    Arrays.fill(level, -1);
    level[src] = 0;

    Queue<Integer> q = new LinkedList<>();
    q.offer(src);

    while (!q.isEmpty()) {
        int u = q.poll();
        for (int v = 0; v < V; v++) {
            if (rGraph[u][v] > 0 && level[v] < 0) {
                level[v] = level[u] + 1;
                q.offer(v);
            }
        }
    }
    return level[dest] >= 0;
}

// DFS-like method to send flow
private int sendFlow(int u, int flow, int dest, int[] level, int[][] rGraph, int V) {
    if (u == dest)
        return flow;

    for (int v = 0; v < V; v++) {
        if (rGraph[u][v] > 0 && level[v] == level[u] + 1) {
            int current_flow = Math.min(flow, rGraph[u][v]);
            int temp_flow = sendFlow(v, current_flow, dest, level, rGraph, V);

            if (temp_flow > 0) {
                rGraph[u][v] -= temp_flow;
                rGraph[v][u] += temp_flow;
                return temp_flow;
            }
        }
    }
    return 0;
}

}
