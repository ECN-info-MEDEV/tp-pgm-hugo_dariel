package dames;

/** Classe que gère la position des éléments
 *
 * @author hugorugato et luanlopesf
 */
public class Point2D {

    private int x;
    private int y;

    //San parametres
    /**Constructor sans paramètres pour mettre pos (0,0)
     *
     */
    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

//Creation a partir de deux parametres
    /**Constructor avec deux parametres 
     *
     * @param v1 Position x
     * @param v2 Position y
     */
    public Point2D(int v1, int v2) {
        this.x = v1;
        this.y = v2;
    }

    /**Constructeur de recopie
     *
     * @param p Objet type Point2D
     */
    public Point2D(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }

//SetX
    /**Mis à jour de la position X
     *
     * @param v1 Position x
     */
    public void setX(int v1) {
        this.x = v1;
    }

//SetY
    /**Mis à jour de la position Y
     *
     * @param v2 Position y
     */
    public void setY(int v2) {
        this.y = v2;
    }

//GetX
    /**Retourner la position x
     *
     * @return la position x
     */
    public int getX() {
        return this.x;
    }

//GetY
    /**REtourner la position y
     *
     * @return la position y
     */
    public int getY() {
        return this.y;
    }

//SetPosition
    /**Mis à jour du x et du y
     *
     * @param v1 Position x
     * @param v2 Position y
     */
    public void setPosition(int v1, int v2) {
        this.x = v1;
        this.y = v2;
    }

//Translate
    /** Translate
     *
     * @param dx Delta en x
     * @param dy Delta en y
     */
    public void translate(int dx, int dy) {
        this.setPosition(this.getX() + dx,this.getY()+dy);
    }

//Affichage
    /** Afficher la position [x,y] 
     *
     */
    public void affiche() {
        System.out.println("[" + x + ";" + y + "]");
    }

//Distance
    /** Distance entre 2 points
     *
     * @param p Point dont la distance on veut calculer
     * @return la distance entre les deux points
     */
    public double distance(Point2D p) {
        return Math.sqrt((this.x - p.x)*(this.x - p.x)+ (this.y - p.y)*(this.y - p.y));
    }

}
