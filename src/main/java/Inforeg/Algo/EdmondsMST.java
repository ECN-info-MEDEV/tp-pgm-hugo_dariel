package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import Inforeg.ObjetGraph.Arc;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * Chu/Edmonds Algorithm for Directed Graphs
 */
public class EdmondsMST extends Algorithm implements Processing {

    public EdmondsMST() {
        this.setName("Chu/Edmonds");
    }

    @Override
    public void process(Draw d) {
        Graph G = d.getG();
        G.updateVariable();

        // Check if the graph is directed
        if (!G.isOriente()) {
            JOptionPane.showMessageDialog(null, "Chu/Edmonds algorithm requires a directed graph!", "Chu/Edmonds", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Sort edges by weight
        Arc[] sortedArcs = G.getLines().toArray(new Arc[0]);
        Arrays.sort(sortedArcs, (a1, a2) -> Integer.compare(a1.getPoids(), a2.getPoids()));

        // Implementation of the Chu/Edmonds algorithm
        chuEdmondsAlgorithm(G, sortedArcs, d);
    }

    // ...

    private void chuEdmondsAlgorithm(Graph G, Arc[] sortedArcs, Draw d) {
        int n = G.getNbsommets();
        boolean[] inSubgraph = new boolean[n];
        Arrays.fill(inSubgraph, false);

        int[] predecessor = new int[n];
        Arrays.fill(predecessor, -1);

        ArrayList<Arc> subgraphArcs = new ArrayList<>();

        // Iterate over all vertices to find and collapse cycles
        for (int i = 0; i < n; i++) {
            if (!inSubgraph[i]) {
                collapseCycles(G, i, predecessor, inSubgraph, sortedArcs, subgraphArcs, d);
            }
        }

        // Convert ArrayList to array and update the visualization
        Arc[] subgraph = new Arc[subgraphArcs.size()];
        subgraphArcs.toArray(subgraph);
        updateVisualization(G, subgraph, d);
    }

    private void collapseCycles(Graph G, int startVertex, int[] predecessor, boolean[] inSubgraph, Arc[] sortedArcs, ArrayList<Arc> subgraphArcs, Draw d) {
        // ... Implement the logic to identify and collapse cycles
        // This logic will involve more complex graph operations
    }

    private void updateVisualization(Graph G, Arc[] subgraph, Draw d) {
        for (Arc arc : subgraph) {
            // Highlight the arcs in the subgraph
            d.stepBysStep.colorArc(G.findLine(arc.getFrom(), arc.getTo()), Color.GREEN);
            d.stepBysStep.setInfoText("Subgraph edge");
            d.stepBysStep.nextStep();
        }
        d.setResultat("Result of the Chu/Edmonds algorithm.");
        d.algoFinished();
    }

// ...


    private void findAndCollapseCycles(Graph G, int startVertex, int[] predecessor, boolean[] inSubgraph, Arc[] sortedArcs, Draw d) {
        // Mark the current vertex as visited
        inSubgraph[startVertex] = true;

        // Iterate over all outgoing edges from the current vertex
        for (Arc arc : sortedArcs) {
            if (arc.getFrom().getId() == startVertex) {
                int endVertex = arc.getTo().getId();

                // Check if the end vertex is already in the subgraph
                if (!inSubgraph[endVertex]) {
                    predecessor[endVertex] = startVertex;
                    findAndCollapseCycles(G, endVertex, predecessor, inSubgraph, sortedArcs, d);
                }
                // If a cycle is detected (additional logic required here)
                // Collapse the cycle (additional logic required here)
                // Visualization of edge processing
                d.stepBysStep.colorArc(G.findLine(arc.getFrom(), arc.getTo()), Color.YELLOW);
                d.stepBysStep.setInfoText("Processing edge");
                d.stepBysStep.nextStep();
            }
        }
        // Additional logic to handle cycles and update the subgraph
    }
}
