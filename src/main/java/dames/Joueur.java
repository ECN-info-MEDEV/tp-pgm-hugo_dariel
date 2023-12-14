
package dames;

import java.util.ArrayList;

import dames.Piece;


//public class Joueur {
//    public String getNom() {
//        return nom;
//    }
//
//    public ArrayList<Piece> getPieces() {
//        return pieces;
//    }
//
//    private String nom;
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public void setPieces(ArrayList<Piece> pieces) {
//        this.pieces = pieces;
//    }
//
//    private ArrayList<Piece> pieces;
//    public Joueur(String nom, ArrayList<Piece> pieces){
//        this.nom=nom;
//        this.pieces=new ArrayList<Piece>();
//        this.pieces=pieces;
//
//    }
//    
//    public Joueur(){
//        this.nom="joueur";
//        this.pieces=new ArrayList<Piece>();
//        for(int i=0;i<12;i++){
//        this.pieces.add(new Piece());}
//
//    }
//
//}

public class Joueur {
    private String nom;
    private ArrayList<Piece> pieces;
    private int nbPieces;
    
    
    public Joueur(){
        this.nom="joueur";
        this.pieces=new ArrayList<Piece>();
        for(int i=0;i<12;i++){
        this.pieces.add(new Piece());}

    }
    public Joueur(String nom, ArrayList<Piece> pieces) {
        this.nom = nom;
        this.pieces = new ArrayList<Piece>();
        this.pieces = pieces;
        nbPieces = pieces.size();
    }
    
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
}
