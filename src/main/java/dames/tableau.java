/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dames;

/**
 *
 * @author hugoc
 */
public class tableau {
    
    private Piece[][] grille;
    
    private Joueur joueur1,joueur2;
    
    public void CreerTableau(){
        this.grille = new piece[8][8];
        this.joueur1 = new joueur();
        this.joueur2 = new joueur();
        for(int i=0;i<12;i++){
        this.joueur1.pieces.get(i).setPosition();
        }

    }

     public void affiche() {
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
                if (this.grille[row][col] == null) {
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
    public void turnJeu(Boolean turn){
        if(turn){
            for(Piece p: this.joueur1.getPieces()){
                if(p.peutManger(this)){
                    
                }
        }
        }
        
    }
    public Piece[][] getGrille(){
        return this.grille;
    }
    

}
