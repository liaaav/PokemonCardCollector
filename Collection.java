import java.util.HashMap;
import ecs100.*;
import java.util.ArrayList;
/**
 * Collection of pokemon cards
 *
 * @author AL
 * @version 26/05/22
 */
public class Collection
{
    //declaring the hashmap
    private HashMap<Integer, PokemonCard> Collection;
    // store the id of current pokemonCard
    private int currPokemonCardId;
    // store the instance of the current pokemonCard
    private PokemonCard currPokemonCard;
    /**
     * Constructor for objects of class Collection
     */
    public Collection()
    {
        // initialise instance variables
        Collection = new HashMap<Integer, PokemonCard>();
        
        // create Collection
        PokemonCard p1 = new PokemonCard("WOOPER", 0.34, "wooper.png");
        PokemonCard p2 = new PokemonCard("WOOPER", 7.45, "shinyWooper.png");
        PokemonCard p3 = new PokemonCard
        ("GALARIAN PONYTA", 7.76, "galarianPonyta.png");
        
        Collection.put(1, p1);
        Collection.put(2, p2);
        Collection.put(3, p3);
        
        this.currPokemonCardId = 3;     //Stores the current id
    }

    /**
     * increases id by one
     */
    public void idIncrement() {
        this.currPokemonCardId += 1;
    }  
    
    /**
     * Sets current pokemon based on id passed in
     * @param id hashmap id of the pokemon card
     */
    public void setPokemonCardId(int id) {
        currPokemonCard = Collection.get(id);
    }
    
    /**
     * Add a pokemon Card to the map
     * @param name of pokemon
     * @param val value of card
     * @param img file path of image
     */
    public void addPokemonCard(String name, double val, String img) {
        // rounds value to 2dp
        double value = Math.round(val * 100.0) / 100.0;
        // puts card into hashmap
        Collection.put(currPokemonCardId, new PokemonCard(name, value, img));
        // sets current pokemon card to the one just added
        currPokemonCard = Collection.get(currPokemonCardId);
    }
    /**
     * Size of collection getter
     * @return int the collection size
     */
    public int getSize() {
        int size = Collection.size();
        return size;
    }
    /**
     * delete pokemon card
     * @param id of the pokemon card
     */
    public void deletePokemonCard(int id) {
        Collection.remove(id);
    }
    /** 
     * Getter for the current pokemonCard instance
     * @return pokemonCard
     */
    public PokemonCard getPokemonCard() {
        return this.currPokemonCard;
    }
    
    /**
     * Finds a pokemonCard based on name
     * Sets the current pokemonCard instance if found
     * @param name searched pokemon name
     * @return ArrayList of found pokemon
     */
    public ArrayList findPokemonCard(String name) {
        // Search for pokemonCard
        ArrayList<Integer> searchResult = new ArrayList<Integer>(); 
        for (int pokemonCardId : Collection.keySet()) {
            if (Collection.get(pokemonCardId)
                .getName().toLowerCase().contains(name)) {
                currPokemonCard = Collection.get(pokemonCardId);
                searchResult.add(pokemonCardId);
            }
        }
        return searchResult;
    }
    
    /**
     * Print detail of all pokemon cards
     */
    public void printAll() {
        // clears graphics and text
        UI.clearText();
        UI.clearGraphics();
        UI.println("----Pokemon----");
        // Traverse Map
        for (int pokemonCardId : Collection.keySet()) {
            UI.println(pokemonCardId + ") " 
                        + Collection.get(pokemonCardId).getName());
            UI.println("Value: $ " + Collection.get(pokemonCardId).getValue());
            UI.println("");
        }
    }
    /**
     * Display image with set location
     */
    public void displayAll () {
        int locY = 20;  // y location
        int locX = 20;  // x location of first card
        int buffer = 10;    // gap inbetween cards
        for (int pokemonCardId : Collection.keySet()) {
            currPokemonCard = Collection.get(pokemonCardId);
            currPokemonCard.displayImage(locX, locY);
            // move next image over with a buffer inbetween;
            locX += currPokemonCard.WIDTH + buffer;
        }
    }
    
    /**
     * Defines which profile mouse clicks on
     * @param x coordinate of mouse
     * @param y coordinate of mouse
     * @return boolean true if image is clicked on, false if not
     */
    public boolean imageClick (double x, double y) {
        for (int pokemonCardId : Collection.keySet()) {
            currPokemonCard = Collection.get(pokemonCardId);
            // checks if click was on the image
            if (currPokemonCard.onProfile(x, y)) {    
                return true;
            }
        }
        return false;
    }
}
