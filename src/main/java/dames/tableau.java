/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dames;

import java.util.Arrays;

/**
 *
 * @author hugoc
 */
public class tableau {

    private Piece[][] grille;

    private Joueur joueur1, joueur2;

    public void CreerTableau() {

        this.grille = new Piece[8][8];
        this.joueur1 = new Joueur();
        this.joueur2 = new Joueur();
        int pos1 = 0;
        int pos2 = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0 && i < 3) {

                    this.grille[i][j] = this.joueur1.getPieces().get(pos1);
                    this.joueur1.getPieces().get(pos1).getPos().setPosition(i, j);
                    pos1++;
                } else if ((i + j) % 2 == 0 && i > 4) {
                    this.grille[i][j] = this.joueur2.getPieces().get(pos2);
                    this.joueur2.getPieces().get(pos2).getPos().setPosition(i, j);
                    pos2++;
                }
            }
        }

    }

    public void affiche(Joueur j) {
        if (j == this.joueur1) {
            int numRows = this.grille[0].length;
            int numCols = this.grille.length;

            int maxNomeLength = 1;

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (this.grille[row][col] != null) {
                        int nomeLength = this.grille[row][col].toString().length();
                        maxNomeLength = Math.max(maxNomeLength, nomeLength);
                    }
                }
            }
            int cellSize = maxNomeLength + 2;
            for (int row = numRows - 1; row >= 0; row--) {
                System.out.printf("%-" + maxNomeLength + "d  ", row);
                for (int col = 0; col < numCols; col++) {
                    if (this.grille[row][col] == null && (row + col) % 2 == 1) {
                        System.out.printf("%-" + cellSize + "s", " ");
                    } else if (this.grille[row][col] == null && (row + col) % 2 == 0) {
                        System.out.printf("%-" + cellSize + "s", "0");
                    } else {
                        String nome = this.grille[row][col].toString();
                        System.out.printf("%-" + cellSize + "s", nome);
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.printf("%-" + cellSize + "s", "");
            for (int col = 0; col < numCols; col++) {
                System.out.printf("%-" + cellSize + "s", col);
            }
        } else {
            int numRows = this.grille[0].length;
            int numCols = this.grille.length;

            int maxNomeLength = 1;

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (this.grille[row][col] != null) {
                        int nomeLength = this.grille[row][col].toString().length();
                        maxNomeLength = Math.max(maxNomeLength, nomeLength);
                    }
                }
            }
            int cellSize = maxNomeLength + 2;
            for (int row = numRows - 1; row >= 0; row--) {
                System.out.printf("%-" + maxNomeLength + "d  ", row);
                for (int col = 0; col < numCols; col++) {
                    if (this.grille[row][col] == null && (row + col) % 2 == 1) {
                        System.out.printf("%-" + cellSize + "s", " ");
                    } else if (this.grille[row][col] == null && (row + col) % 2 == 0) {
                        System.out.printf("%-" + cellSize + "s", "0");
                    } else {
                        String nome = this.grille[row][col].toString();
                        System.out.printf("%-" + cellSize + "s", nome);
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.printf("%-" + cellSize + "s", "");
            for (int col = 0; col < numCols; col++) {
                System.out.printf("%-" + cellSize + "s", col);
            }

        }
    }

    public void manger(Joueur j, Piece p, Point2D posmanger) {
        j.getPieces().remove(this.grille[posmanger.getX()][posmanger.getY()]);
        this.grille[posmanger.getX()][posmanger.getY()] = null;
        deplacer(p, posmanger.getX(), posmanger.getY());
    }

    public void deplacer(Piece p, int x, int y) {
        this.grille[p.getPos().getX()][p.getPos().getY()] = null;
        this.grille[x][y] = p;
    }
    
    

//    public void tourDeJeu(Boolean turn, Joueur j) {
//        if (turn) {
//            Boolean[] peutmanger = new Boolean[j.getNbPieces()];
//            Arrays.fill(peutmanger, Boolean.FALSE);
//            for (Piece p : this.joueur1.getPieces()) {
//                if (p.peutManger(this)[0] || p.peutManger(this)[1] || p.peutManger(this)[2] || p.peutManger(this)[3]) {
//                }
//            }
//        }
//
//    }
    
    
    public void tourDeJeu() {
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(grille[i][j] != null){
                    
                }
            }
        }
    }
    

    public Piece[][] getGrille() {
        return this.grille;
    }

}

