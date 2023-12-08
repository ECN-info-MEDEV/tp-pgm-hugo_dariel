/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dames;

import java.util.Objects;

/**
 *
 * @author murkp
 */
public class Piece {

    /**
     * @param args the command line arguments
     */
    private Boolean dame;
    private Boolean coleur;
    private Point2D pos;
    
    public Piece(){
        this.dame = false;
        this.pos = new Point2D();
    }
    public Piece(Boolean dame, Point2D pos){
        this.dame = dame;
        this.pos = pos;
    }
    
    public Boolean getColeur(){
        return this.coleur;
    }
    
    public Boolean estDame(){
        return this.dame;
    }
    
    public void pieceDame(){
        this.dame = true;
    }
    
    public Point2D getPos(){
        return this.pos;
    }
    public void setPos(Point2D p){
        this.pos = p;
    }
    public Boolean[] peutManger(tableau g){
        Boolean DC = false;
        Boolean EC = false;
        Boolean DB = false;
        Boolean EB = false;
        if(this.dame){
            int i = 0;
            while(this.pos.getX()+i<7 && this.pos.getY()+i<7){
                if(Objects.equals(g.getGrille()[this.pos.getX()+i][this.pos.getY()+i].getColeur(), this.getColeur())){
                    i = 10;
                }
                if(!Objects.equals(g.getGrille()[this.pos.getX()+i][this.pos.getY()+i].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()+i+1][this.pos.getY()+i+1]==null){
                    DC = true;
                    i = 10;
                }
                i++;
            }
            i = 0;
            while(this.pos.getX()-i>0 && this.pos.getY()+i<7){
                if(Objects.equals(g.getGrille()[this.pos.getX()-i][this.pos.getY()+i].getColeur(), this.getColeur())){
                    i = 10;
                }
                if(!Objects.equals(g.getGrille()[this.pos.getX()-i][this.pos.getY()+i].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()-i-1][this.pos.getY()+i+1]==null){
                    EC = true;
                    i = 10;
                }
                i++;
            } 
            i = 0;
            while(this.pos.getX()+i<7 && this.pos.getY()-i>0){
                if(Objects.equals(g.getGrille()[this.pos.getX()+i][this.pos.getY()-i].getColeur(), this.getColeur())){
                    i = 10;
                }
                if(!Objects.equals(g.getGrille()[this.pos.getX()+i][this.pos.getY()-i].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()+i+1][this.pos.getY()-i-1]==null){
                    DB = true;
                    i = 10;
                }
                i++;
            } 
            i = 0;
            while(this.pos.getX()-i>0 && this.pos.getY()-i>0){
                if(Objects.equals(g.getGrille()[this.pos.getX()-i][this.pos.getY()-i].getColeur(), this.getColeur())){
                    i = 10;
                }
                if(!Objects.equals(g.getGrille()[this.pos.getX()-i][this.pos.getY()-i].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()-i-1][this.pos.getY()-i-1]==null){
                    EB = true;
                    i = 10;
                }
                i++;
            }
        }
        else{
           if(this.pos.getX()+2<8 && this.pos.getY()+2<8 && !Objects.equals(g.getGrille()[this.pos.getX()+1][this.pos.getY()+1].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()+2][this.pos.getY()+2]==null){
               DC = true;
           }
           if(this.pos.getX()-2>=0 && this.pos.getY()+2<8 && !Objects.equals(g.getGrille()[this.pos.getX()-1][this.pos.getY()+1].getColeur(),this.getColeur()) && g.getGrille()[this.pos.getX()-2][this.pos.getY()+2]==null){
               EC = true;
           }
           if(this.pos.getX()+2<8 && this.pos.getY()-2>=0 && !Objects.equals(g.getGrille()[this.pos.getX()+1][this.pos.getY()-1].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()+2][this.pos.getY()-2]==null){
               DB = true;
           } 
           if(this.pos.getX()-2>=0 && this.pos.getY()-2>=0 && !Objects.equals(g.getGrille()[this.pos.getX()-1][this.pos.getY()-1].getColeur(), this.getColeur()) && g.getGrille()[this.pos.getX()-2][this.pos.getY()-2]==null){
               EB = true;
           }
        }
        Boolean[] peut = {DC,EC,DB,EB};
        return peut;
    }
}
