
package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Edmonds extends Algorithm {

    public Edmonds() {
        super();
        this.setName("Edmonds");
    }

    @Override
    public void process(Draw d) {
        d.setStatus(Draw.ALGO_INPUT);
        Graph g = d.getG();
        int V = g.getNbsommets();
        boolean[] matched = new boolean[V];
        Arrays.fill(matched, false);

        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        // Parcours de tous les sommets non appariés
        for (int u = 0; u < V; u++) {
            if (!matched[u]) {
                int match = findAugmentingPath(g, u, parent, matched, d);
                if (match != -1) {
                    matched[u] = true;
                    matched[match] = true;
                    // Marcar o arco do emparelhamento
                    d.stepBysStep.colorArc(g.findLine(g.getNode(u), g.getNode(match)), Color.GREEN);
                    d.stepBysStep.nextStep();
                }
            }
        }

        // Calcul de la taille de l'appariement maximum
        int maxMatchingSize = 0;
        for (int u = 0; u < V; u++) {
            if (matched[u]) {
                maxMatchingSize++;
            }
        }

        d.setResultat("Taille de l'appariement maximum : " + maxMatchingSize);
        d.algoFinished();
    }

    private int findAugmentingPath(Graph g, int u, int[] parent, boolean[] matched, Draw d) {
        int V = g.getNbsommets();
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        int[] base = new int[V];
        for (int i = 0; i < V; i++) {
            base[i] = i;
        }

        visited[u] = true;

        List<Integer> queue = new ArrayList<>();
        queue.add(u);

        while (!queue.isEmpty()) {
            int current = queue.get(0);
            queue.remove(0);

            // Parcours des voisins du sommet courant
            for (int v = 0; v < V; v++) {
                if (!visited[v] && g.getAdjMatrix()[current][v] == 1) {
                    visited[v] = true;
                    d.stepBysStep.colorArc(g.findLine(g.getNode(current), g.getNode(v)), Color.YELLOW);
                    d.stepBysStep.nextStep();

                    if (parent[v] == -1) {
                        parent[v] = current;
                        return v;
                    } else {
                        // Recherche de la base commune et mise à jour
                        int pu = findBase(base, current);
                        int pv = findBase(base, v);
                        if (pu != pv) {
                            base[pu] = pv;
                            queue.add(parent[v]);
                        }
                    }
                }
            }
        }

        return -1;
    }

    private int findBase(int[] base, int u) {
        if (base[u] != u) {
            base[u] = findBase(base, base[u]);
        }
        return base[u];
    }
}

