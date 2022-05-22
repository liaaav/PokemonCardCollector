import java.util.HashMap;
import ecs100.*;
/**
 * Collection of pokemon cards
 *
 * @author AL
 * @version 20/05/22
 */
public class Collection
{
    // instance variables - replace the example below with your own
    private HashMap<Integer, pokemonCard> Collection;     //declaring the hashmap
    private int currPokemonCardId;                     // store the current if of pokemonCard being added
    private pokemonCard currPokemonCard;                      // store the instance of the current pokemonCard
    /**
     * Constructor for objects of class Collection
     */
    public Collection()
    {
        // initialise instance variables
        Collection = new HashMap<Integer, pokemonCard>();
        
        // create Collection
        pokemonCard p1 = new pokemonCard("wooper", 0.34, "wooper.png");
        pokemonCard p2 = new pokemonCard("wooper", 7.45);
        pokemonCard p3 = new pokemonCard("galarian ponyta", 7.76);
        
        Collection.put(1, p1);
        Collection.put(2, p2);
        Collection.put(3, p3);
        
        this.currPokemonCardId = 3;     //Stores the current pokemonCard id
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
        
        Collection.put(currPokemonCardId, new pokemonCard(name, val, img));
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
     * Print detail of all pokemonCard
     */
    public void printAll(){
        // Traverse Map
        UI.clearText();
        for (int pokemonCardId : Collection.keySet()){
            UI.println(pokemonCardId+") " + Collection.get(pokemonCardId).getName());
            UI.println("Value: $ " + Collection.get(pokemonCardId).getValue());
        }
    }
    
}
