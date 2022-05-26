import java.util.HashMap;
import ecs100.*;
/**
 * Collection of pokemon cards
 *
 * @author AL
 * @version 26/05/22
 */
public class Collection
{
    private HashMap<Integer, pokemonCard> Collection;     //declaring the hashmap
    private int currPokemonCardId;                        // store the id of current pokemonCard
    private pokemonCard currPokemonCard;                  // store the instance of the current pokemonCard
    /**
     * Constructor for objects of class Collection
     */
    public Collection()
    {
        // initialise instance variables
        Collection = new HashMap<Integer, pokemonCard>();
        
        // create Collection
        pokemonCard p1 = new pokemonCard("WOOPER", 0.34, "wooper.png");
        pokemonCard p2 = new pokemonCard("WOOPER", 7.45, "shinyWooper.png");
        pokemonCard p3 = new pokemonCard("GALARIAN PONYTA", 7.76, "galarianPonyta.png");
        
        Collection.put(1, p1);
        Collection.put(2, p2);
        Collection.put(3, p3);
        
        this.currPokemonCardId = 3;     //Stores the current id
    }

    /**
     * Set pokemon Card Id
     */
    public void setPokemonCardId(){
        this.currPokemonCardId += 1;
    }  
    
    /**
     * Add a pokemon Card to the map
     */
    public void addPokemonCard(String name, double val, String img) {
        double value = Math.round(val*100.0)/100.0;     // rounds value to 2dp
        Collection.put(currPokemonCardId, new pokemonCard(name, value, img));
        currPokemonCard = Collection.get(currPokemonCardId);    // sets current pokemon card to the one just added
    }
    
    /** 
     * Getter for the current pokemonCard instance
     */
    public pokemonCard getPokemonCard(){
        return this.currPokemonCard;
    }
    
    /**
     * Finds a pokemonCard based on name
     * Sets the current pokemonCard instance if found
     * @return boolean false if not found
     */
    public boolean findPokemonCard(String name){
        // Search for pokemonCard
        for (int pokemonCardId: Collection.keySet()){
            if (Collection.get(pokemonCardId).getName().toLowerCase().contains(name)){
                currPokemonCard = Collection.get(pokemonCardId);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Print detail of all pokemon cards
     */
    public void printAll(){
        // clears graphics and text
        UI.clearText();
        UI.clearGraphics();
        UI.println("----Pokemon----");
        // Traverse Map
        for (int pokemonCardId : Collection.keySet()){
            UI.println(pokemonCardId+") " + Collection.get(pokemonCardId).getName());
            UI.println("Value: $ " + Collection.get(pokemonCardId).getValue());
            UI.println("");
        }
        // print all pokemon card images
        this:displayAll();
    }
    /**
     * Display image with set location
     */
    public void displayAll (){
        int locY = 20;  // y location
        int locX = 20;  // x location of first card
        int buffer = 10;    // gap inbetween cards
        for (int pokemonCardId : Collection.keySet()){
                currPokemonCard = Collection.get(pokemonCardId);
                currPokemonCard.displayImage(locX, locY);
                locX += currPokemonCard.WIDTH + buffer;    // moves next image over with a buffer inbetween;
            }
        }
        }
