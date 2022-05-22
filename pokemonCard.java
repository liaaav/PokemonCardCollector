import ecs100.*;
/**
 * Support class for pokemonCards
 *
 * @author AL
 * @version 20/05/22
 */
public class pokemonCard
{
    // fields
    private String name;
    private double value;
    private String image;
    static final String DEFAULT_IMAGE = "pokemon.jpg";
    final int LOC_X = 100;
    final int LOC_Y = 100;
    final double WIDTH = 100;
    final double HEIGHT = 100;


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
     * Display profile on GUI
     */
    public void displayProfile(){
        UI.drawImage(this.image, LOC_X, LOC_Y, WIDTH, HEIGHT);
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
     * Mouse on profile
     */
    public boolean onProfile(double x, double y){
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
        if ((x >= LOC_X) && (x<= LOC_X+WIDTH) && (y >= LOC_Y) && (y<= LOC_Y+HEIGHT)){
            return true;
        }else{
            return false;
        }
    }  
}
