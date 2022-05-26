import ecs100.*;
/**
 * Support class for pokemonCards
 *
 * @author AL
 * @version 26/05/22
 */
public class pokemonCard
{
    // fields
    private String name;        // pokemon name
    private double value;       // momentary value
    private String image;       // image file name
    static final String DEFAULT_IMAGE = "pokemon.jpg";
    private int locX;       // x location of image
    private int locY;       // y location of image
    final double WIDTH = 100;   // width of image
    final double HEIGHT = 150;  //height of image


    /**
     * Constructor for objects of class pokemonCard
     */
    public pokemonCard(String nm, double val, String img)
    {
        name = nm;
        value = val;
        if (img == null) {
            this.image = DEFAULT_IMAGE;
        }else{
            this.image = img;
        }
    }

    /**
     * Contructor overloading
     * Set default image to obj
     */
    public pokemonCard(String nm, double val){
        this(nm, val, DEFAULT_IMAGE);
    }
    
    /**
     * Display image on GUI
     */
    public void displayImage(int locX, int locY){
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }

    /**
     * Getter for name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Getter for value
     */
    public double getValue() {
        return this.value;
    }
    /**
     * Detects if mouse is on the image
     */
    public boolean onProfile(double x, double y){
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
        if ((x >= locX) && (x<= locX+WIDTH) && (y >= locY) && (y<= locY+HEIGHT)){
            return true;
        }else{
            return false;
        }
    }  
}
