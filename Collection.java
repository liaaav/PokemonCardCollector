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
    //declaring the array
    /**
     * Decided to use an arraylist rather than a hashmap because the keys of
     * the hashmap were redundant. They were not unique to the value and were
     * the same as if it were just an arraylist. Changing it to an arraylist 
     * made it a lot easier when working with ids because removing ids would
     * just change the other pokemons ids.
     */
    private ArrayList<PokemonCard> collection;
    // store the instance of the current pokemonCard
    private PokemonCard currPokemonCard;
    /**
     * Constructor for objects of class Collection
     */
    public Collection()
    {
        // initialise instance variables
        collection = new ArrayList<PokemonCard>();
        
        // create Collection
        PokemonCard p1 = new PokemonCard("WOOPER", 0.34, "wooper.png");
        PokemonCard p2 = new PokemonCard("WOOPER", 7.45, "shinyWooper.png");
        PokemonCard p3 = new PokemonCard
        ("GALARIAN PONYTA", 7.76, "galarianPonyta.png");
        
        collection.add(p1);
        collection.add(p2);
        collection.add(p3);
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
        collection.add(new PokemonCard(name, value, img));
        // sets current pokemon card to the one just added
        // minus one because id starts at 0
        currPokemonCard = collection.get(collection.size() - 1);
    }
    
    /**
     * Size of collection getter
     * @return int the collection size
     */
    public int getSize() {
        int size = collection.size();
        return size;
    }
    /**
     * delete pokemon card
     * @param id of the pokemon card
     */
    public void deletePokemonCard(int id) {
        collection.remove(id);
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
        for (int  id = 0; id < collection.size(); id++) {
            if (collection.get(id)
                .getName().toLowerCase().contains(name)) {
                currPokemonCard = collection.get(id);
                searchResult.add(id);
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
        if (collection.size() < 1) {
            UI.println("Please add some pokemon first");
        }
        else {
            UI.println("----Pokemon----");
            // Traverse Map
            for (int  id = 0; id < collection.size(); id++) {
                UI.println(id + 1 + ") " // id + 1 to make list start from 1
                            + collection.get(id).getName());
                UI.println("Value: $ " + collection.get(id).getValue());
                UI.println("");
            }
        }
    }
    /**
     * Display image with set location
     */
    public void displayAll () {
        int locY = 20;  // y location
        int locX = 20;  // x location of first card
        int buffer = 10;    // gap inbetween cards
        for (int  id = 0; id < collection.size(); id++) {
            currPokemonCard = collection.get(id);
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
        for (int  id = 0; id < collection.size(); id++) {
            currPokemonCard = collection.get(id);
            // checks if click was on the image
            if (currPokemonCard.onProfile(x, y)) {    
                return true;
            }
        }
        return false;
    }
}
