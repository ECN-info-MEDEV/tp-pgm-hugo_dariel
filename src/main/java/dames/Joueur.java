package dames;

import java.util.ArrayList;

public class Joueur {
    public String getNom() {
        return nom;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    private String nom;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    private ArrayList<Piece> pieces;
    public Joueur(String nom, ArrayList<Piece> pieces){
        this.nom=nom;
        this.pieces=new ArrayList<Piece>();
        this.pieces=pieces;

    }
    
    public Joueur(){
        this.nom="joueur";
        this.pieces=new ArrayList<Piece>();
        for(int i=0;i<12;i++){
        this.pieces.add(new Piece());}

    }

}
