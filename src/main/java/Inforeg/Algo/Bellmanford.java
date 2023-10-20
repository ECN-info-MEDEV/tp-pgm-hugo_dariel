package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import Inforeg.ObjetGraph.Arc;
import Inforeg.ObjetGraph.Node;
import java.awt.Color;

/**
 *
 * @author Victor Meirelles
 * @author Dariel BEZERRA
 */
public class Bellmanford extends Algorithm implements AlgorithmST, Processing {

    public Bellmanford() {
        super();
        this.setName("Bellman-Ford");
    }

    @Override
    public void process(Draw d) {
        d.setStatus(Draw.ALGO_INPUT);
    }

    @Override
    public void process(Draw d, Node srcNode, Node destNode) {

        int src = d.getG().getNodeId(srcNode);
        int dest = d.getG().getNodeId(destNode);
        int[] dist;
        int[] predecesseur;

        Graph g = d.getG();
        g.updateVariable();

        dist = new int[g.getNbsommets()];
        predecesseur = new int[g.getNbsommets()];

        boolean vu[] = new boolean[g.getNbsommets()];

        for (int i = 0; i < g.getNbsommets(); i++) {
            dist[i] = Integer.MAX_VALUE;
            vu[i] = false;
        }

        dist[src] = 0;
        predecesseur[src] = -1;
        Node node;

        for (int count = 0; count < g.getNbsommets() - 1; count++) {
            int u = findMin(dist, vu, g.getNbsommets());
            node = d.getNode(u);
            d.stepBysStep.colorNode(node, Color.ORANGE, false);
            d.stepBysStep.setInfoText("Distance la plus petite du noeud " + node.getLabel() + " est " + dist[u]);
            d.stepBysStep.nextStep();

            vu[u] = true;

            for (int v = 0; v < g.getNbsommets(); v++) {
                if (!vu[v] && g.getAdjMatrix()[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + g.getAdjMatrix()[u][v] < dist[v]) {
                    dist[v] = dist[u] + g.getAdjMatrix()[u][v];
                    node = d.getNode(v);
                    d.stepBysStep.colorNode(node, Color.GRAY, false);
                    d.stepBysStep.setInfoText("Mise a jour de " + node.getLabel() + " nouvelle distance " + dist[v]);
                    d.stepBysStep.nextStep();
                    predecesseur[v] = u;
                }
            }
        }

        int s = dest;
        int p = predecesseur[s];
        int count = 0;

        while ((s != src) && (count < d.getNodes().size()) && (p != -1)) {
            System.out.println(src + " " + s + " " + p);
            Arc l = d.findLine(p, s);
            if (l != null) {
                l.setColorDisplayed(Color.RED);
                s = p;
                p = predecesseur[p];
                count++;
            } else {
                p = -1;
            }
        }

        if (s != src) {
            d.reinit();
            d.setResultat("Il n'existe pas de chemin entre les sommets "
                    + d.getNodes().get(src).getLabel() + " et " + d.getNodes().get(dest).getLabel() + ".");
        } else {
            d.repaint();
            d.algoFinished();
            d.setResultat("Il existe un plus court chemin entre les sommets "
                    + d.getNodes().get(src).getLabel() + " et " + d.getNodes().get(dest).getLabel()
                    + ", de distance " + dist[dest] + ".");
        }
    }


}
