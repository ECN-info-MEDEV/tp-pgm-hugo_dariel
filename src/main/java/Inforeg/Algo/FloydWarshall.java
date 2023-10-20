package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import Inforeg.ObjetGraph.Arc;
import Inforeg.ObjetGraph.Node;
import java.awt.Color;


public class FloydWarshall extends Algorithm implements AlgorithmST, Processing {

    public FloydWarshall() {
        super();
        this.setName("Floyd-Warshall");
    }

    @Override
    public void process(Draw d) {
        d.setStatus(Draw.ALGO_INPUT);
    }

    @Override
    public void process(Draw d, Node srcNode, Node destNode) {
        int src = d.getG().getNodeId(srcNode);
        int dest = d.getG().getNodeId(destNode);
        int[][] dist;

        Graph g = d.getG();
        g.updateVariable();

        dist = new int[g.getNbsommets()][g.getNbsommets()];

        // Initialisation de la matrice des distances
        for (int i = 0; i < g.getNbsommets(); i++) {
            for (int j = 0; j < g.getNbsommets(); j++) {
                dist[i][j] = g.getAdjMatrix()[i][j];
            }
        }

        Node node;

        // Algorithme de Floyd-Warshall pour trouver les plus courts chemins
        for (int k = 0; k < g.getNbsommets(); k++) {
            for (int i = 0; i < g.getNbsommets(); i++) {
                for (int j = 0; j < g.getNbsommets(); j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        // Mise à jour visuelle (optionnelle)
                        node = d.getNode(i);
                        d.stepBysStep.colorNode(node, Color.GRAY, false);
                        d.stepBysStep.setInfoText("Mise a jour de " + node.getLabel() + " nouvelle distance " + dist[i][j]);
                        d.stepBysStep.nextStep();
                    }
                }
            }
        }

        int s = src;
        int p = dest;
        int count = 0;

        // Récupération du chemin le plus court
        while ((s != dest) && (count < d.getNodes().size())) {
            System.out.println(s + " " + p);
            Arc l = d.findLine(s, p);
            if (l != null) {
                l.setColorDisplayed(Color.RED);
                s = p;
                p = dest;
                count++;
            } else {
                p = -1;
            }
        }

        // Affichage du résultat
        if (s != dest) {
            d.reinit();
            d.setResultat("Il n'existe pas de chemin entre les sommets "
                    + d.getNodes().get(src).getLabel() + " et " + d.getNodes().get(dest).getLabel() + ".");
        } else {
            d.repaint();
            d.algoFinished();
            d.setResultat("Il existe un plus court chemin entre les sommets "
                    + d.getNodes().get(src).getLabel() + " et " + d.getNodes().get(dest).getLabel()
                    + ", de distance " + dist[src][dest] + ".");
        }
    }
}
