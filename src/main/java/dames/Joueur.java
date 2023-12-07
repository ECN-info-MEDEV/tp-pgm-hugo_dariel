package dames;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Piece> pieces;
    private int nbPieces;
    public String getNom() {
        return nom;
    }
    public int getNbPieces() {
        return nbPieces;
    }
    public void setNbPieces(int nbPieces) {
        this.nbPieces=nbPieces;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public Joueur(String nom, ArrayList<Piece> pieces){
        this.nom=nom;
        this.pieces=new ArrayList<Piece>();
        this.pieces=pieces;
        nbPieces=pieces.size();


    }

}
