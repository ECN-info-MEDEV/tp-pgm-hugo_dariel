package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import Inforeg.ObjetGraph.Arc;
import Inforeg.ObjetGraph.Node;
import java.awt.Color;
import java.util.Stack;

public class eulerien extends Algorithm implements Processing {

    public eulerien() {
        super();
        this.setName("Parcours Eulerien");
    }
    
    
    @Override
    public void process(Draw d) {
        Graph g = d.getG();
        g.updateVariable();

        if (!estEulerien(g)) {
            d.setResultat("Le graphe n'est pas eulérien.");
            return;
        }

        Stack<Integer> chemin = new Stack<>();
        Stack<Node> noeudsChemin = new Stack<>();

        int src = trouverNoeudDeDepart(g);
        chemin.push(src);

        while (!chemin.isEmpty()) {
            int u = chemin.peek();

            if (g.getDegree(u) > 0) {
                int v = trouverProchainNoeud(g, u);
                Arc arc = d.findLine(u, v);
                if (arc != null) {
                    arc.setColorDisplayed(Color.RED);
                }

                chemin.push(v);
                noeudsChemin.push(d.getNode(v));
                d.stepBysStep.colorNode(d.getNode(v), Color.ORANGE, false);
                d.stepBysStep.setInfoText("Exploration du nœud " + d.getNode(v).getLabel());
                d.stepBysStep.nextStep();
            } else {
                chemin.pop();
                if (!noeudsChemin.isEmpty()) {
                    Node noeud = noeudsChemin.pop();
                    d.stepBysStep.colorNode(noeud, Color.GREEN, false);
                    d.stepBysStep.setInfoText("Retour au nœud " + noeud.getLabel());
                    d.stepBysStep.nextStep();
                }
            }
        }

        d.setResultat("Parcours Eulerien terminé.");
        d.repaint();
        d.algoFinished();
    }

    private boolean estEulerien(Graph g) {
        int sommetsImpairs = 0;
        for (int i = 0; i < g.getNbsommets(); i++) {
            if (g.getDegree(i) % 2 != 0) {
                sommetsImpairs++;
            }
        }
        return (sommetsImpairs == 0 || sommetsImpairs == 2);
    }

    private int trouverNoeudDeDepart(Graph g) {
        int noeudDepart = 0;
        for (int i = 0; i < g.getNbsommets(); i++) {
            if (g.getDegree(i) % 2 != 0) {
                noeudDepart = i;
                break;
            }
        }
        return noeudDepart;
    }

    private int trouverProchainNoeud(Graph g, int u) {
        int countEdges = 0;
        int lastV = -1;
        for (int v = 0; v < g.getNbsommets(); v++) {
            if (g.getAdjMatrix()[u][v] != 0) {
                countEdges++;
                lastV = v;
            }
        }
        if (countEdges == 1) {
            return lastV;
        }

        for (int v = 0; v < g.getNbsommets(); v++) {
            if (g.getAdjMatrix()[u][v] != 0) {
                if (!estPont(g, u, v)) {
                    return v;
                }
            }
        }
        return lastV;
    }

    private boolean estPont(Graph g, int u, int v) {
        int count1 = dfsCompte(g, u);

        g.getAdjMatrix()[u][v]--;
        g.getAdjMatrix()[v][u]--;

        int count2 = dfsCompte(g, u);

        g.getAdjMatrix()[u][v]++;
        g.getAdjMatrix()[v][u]++;

        return (count1 > count2);
    }

    private int dfsCompte(Graph g, int v) {
        boolean visite[] = new boolean[g.getNbsommets()];
        Stack<Integer> pile = new Stack<>();
        pile.push(v);

        int compteur = 0;
        while (!pile.isEmpty()) {
            int n = pile.pop();
            if (!visite[n]) {
                visite[n] = true;
                compteur++;

                for (int i = 0; i < g.getNbsommets(); i++) {
                    if (g.getAdjMatrix()[n][i] != 0 && !visite[i]) {
                        pile.push(i);
                    }
                }
            }
        }
        return compteur;
    }
}
