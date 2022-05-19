import java.util.HashMap;
import ecs100.*;
/**
 * Write a description of class Collection here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Collection
{
    // instance variables - replace the example below with your own
    private HashMap<Integer, pokemonCard> Collection;     //declaring the hashmap
    private int currpokemonCardId;                     // store the current if of pokemonCard being added
    private pokemonCard currpokemonCard;                      // store the instance of the current pokemonCard
    /**
     * Constructor for objects of class Collection
     */
    public Collection()
    {
        // initialise instance variables
        Collection = new HashMap<Integer, pokemonCard>();
        
        // create Collection
        pokemonCard c1 = new pokemonCard("Lucy Harris", "027882282", "lucyHarris.jpg");
        pokemonCard c2 = new pokemonCard("Bella Domaneschi", "020666666");
        pokemonCard c3 = new pokemonCard("Lucy Domaneschi", "020666666");
        
        Collection.put(1, c1);
        Collection.put(2, c2);
        Collection.put(3, c3);
        
        this.currpokemonCardId = 3;     //Stores the current pokemonCard id
    }

    /**
     * Set pokemonCardId
     */
    public void setpokemonCardId(){
        this.currpokemonCardId += 1;
    }
    
    /**
     * Add a pokemonCard to the map
     */
    public void addpokemonCard(String name, String number, String img) {
        Collection.put(currpokemonCardId, new pokemonCard(name, number, img));
    }
    
    /** 
     * Getter for the current pokemonCard instance
     */
    public pokemonCard getpokemonCard(){
        return this.currpokemonCard;
    }
    
    /**
     * Finds a pokemonCard based on name
     * Sets the current pokemonCard instance if found
     * @return boolean false if not found
     */
    public boolean findpokemonCard(String name){
        // Search for pokemonCard
        for (int pokemonCardId: Collection.keySet()){
            if (Collection.get(pokemonCardId).getName().toLowerCase().contains(name)){
                currpokemonCard = Collection.get(pokemonCardId);
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
            UI.println("Number: " + Collection.get(pokemonCardId).getNumber());
        }
    }
    
}
