package Inforeg.Algo;

import Inforeg.Draw.Draw;
import Inforeg.Graph.Graph;
import Inforeg.ObjetGraph.Node;
import java.awt.Color;

public class Centralite extends Algorithm implements Processing {

    public Centralite() {
        super();
        this.setName("Centralité");
    }

    @Override
    public void process(Draw d) {
        Graph g = d.getG();
        g.updateVariable();
        int n = g.getNbsommets();
        int[][] dist = new int[n][n];

        // Initialisation de la matrice des distances
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (g.getAdjMatrix()[i][j] != 0) {
                    dist[i][j] = g.getAdjMatrix()[i][j];
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }       

        
        
        // Algorithme de Floyd-Warshall
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                    && dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

        

        // Calcul de la centralité de proximité
        double[] centralite = new double[n];
        int sum;
        Integer minSum = Integer.MAX_VALUE;
        int indexMinSum = -1;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if(dist[i][j]!=Integer.MAX_VALUE){
                        sum += dist[i][j];
                        centralite[i] = 1.0 / sum;
                    }
                    else{
                        sum = Integer.MAX_VALUE;
                        centralite[i]=Integer.MAX_VALUE;
                        break;
                    }
                }
            }
            if (sum < minSum) {
                minSum = sum;
                indexMinSum = i;
            }

            // Affichage des sommes pour chaque point
            Node node = d.getNode(i);
            d.stepBysStep.setInfoText("Somme des distances pour " + node.getLabel() + ": " + sum);
            d.stepBysStep.nextStep();
        }

        // Mettre en évidence le point de centralité
        if (indexMinSum != -1 && minSum < Integer.MAX_VALUE) {
            Node nodeCentral = d.getNode(indexMinSum);
            d.stepBysStep.colorNode(nodeCentral, Color.RED, true);
            d.stepBysStep.setInfoText("Point de centralité: " + nodeCentral.getLabel() + 
                                      " avec une somme de " + minSum);
            d.stepBysStep.nextStep();
            d.algoFinished();
            d.setResultat("Point de centralité: " + nodeCentral.getLabel() + " avec une somme de " + minSum);
            Color newColor = Color.decode("#e6194B");
            nodeCentral.setColorDisplayed(newColor);
        } else {
            d.stepBysStep.setInfoText("Aucun point de centralité trouvé.");
            d.stepBysStep.nextStep();
            d.reinit();
            d.setResultat("Il n'existe pas un point de centralité");
            
        }
        
    }

    
}
