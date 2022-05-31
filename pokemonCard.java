import ecs100.*;
/**
 * Support class for pokemonCards
 *
 * @author AL
 * @version 26/05/22
 */
public class PokemonCard
{
    // fields
    private String name;        // pokemon name
    private double value;       // momentary value
    private String image;       // image file name
    static final String DEFAULT_IMAGE = "pokemon.jpg";
    private int locX;       // x location of image
    private int locY;       // y location of image
    static final double WIDTH = 100;   // width of image
    static final double HEIGHT = 150;  //height of image


    /**
     * Constructor for objects of class pokemonCard
     * @param nm name of the pokemon card
     * @param val value of the card
     * @param img image file name
     */
    public PokemonCard(String nm, double val, String img)
    {
        name = nm;
        value = val;
        if (img == null) {
            this.image = DEFAULT_IMAGE;
        }
        else {
            this.image = img;
        }
    }

    /**
     * Contructor overloading
     * Set default image to obj
     * @param nm name of the pokemon card
     * @param val value of the card
     */
    public PokemonCard(String nm, double val) {
        this(nm, val, DEFAULT_IMAGE);
    }
    
    /**
     * Display image on GUI
     * @param x x coordinate
     * @param y y coordinate
     */
    public void displayImage(int x, int y) {
        locX = x;
        locY = y;
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }

    /**
     * Getter for name
     * @return String the name of the pokemon 
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for value
     * @return the value of the card
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * Detects if mouse is on the image
     * @param x coord of the mouse
     * @param y coord of the mouse
     * @return boolean true if the mouse coordinates are within the image
     * and false if not
     */
    public boolean onProfile(double x, double y) {
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
        if ((x >= locX) && (x <= locX + WIDTH) && 
            (y >= locY) && (y <= locY + HEIGHT)) {
            return true;
        }
        else {
            return false;
        }
    }  
}
