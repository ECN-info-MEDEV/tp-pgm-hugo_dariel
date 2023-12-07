/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dames;

/**
 *
 * @author murkp
 */
public class Piece {

    /**
     * @param args the command line arguments
     */
    private Boolean dame;
    private Point2D pos;
    
    public piece(Boolean dame, Point2D pos){
        this.dame = dame;
        this.pos = pos;
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
}
