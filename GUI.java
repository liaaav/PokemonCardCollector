import java.util.HashMap;
import ecs100.*;

/**
 * code of GUI
 *
 * @author AL
 * @version 26/05/22
 */
public class GUI
{
    // instance variables
    private Collection collection;          // declare Collection instance
    private pokemonCard pokemonCard;          // declare pokemonCard instance


    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        collection = new Collection();       //instantiate the collection
        UI.initialise();
        // buttons
        UI.addButton("Print All", collection::printAll);
        UI.addButton("Add", this::addPokemonCard);
        UI.addButton("Find", this::findPokemonCard);
        UI.addButton("Clear", this::clear);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::mouse);
    }
    
    /**
     * Add a pokemon card to collection
     */
    public void addPokemonCard(){
        clear();
        String name;
        double value;
        do{
            name = UI.askString("Name: ").toUpperCase();
            if(this.isEmpty(name) == true){
                UI.println("Please enter a pokemon name");
            }
        }while(this.isEmpty(name) == true); // checks name is not empty
        
        do{
            value = UI.askDouble("Value: $");
            if(this.isNegative(value) == true){
                UI.println("Please enter a value above 0");
            }
        }while(this.isNegative(value) == true); // checks if value is negative or 0
        
        // add a image for display in the GUI
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        collection.setPokemonCardId(); // Increment the ID by one
        collection.addPokemonCard(name, value, imgFileName);
        
        clear();
        UI.println(name + " added");
        displayPokemon();   //displays just added pokemon
        
    }
    
    /**
     * Check if value is negative or 0
     */
    public static boolean isNegative(double val){
        if(val <= 0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Check if string is empty
     */
    public static boolean isEmpty(String string){
        if(string != null && !string.trim().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Finds pokemon based on name
     * Prints pokemon details if found
     */
    public void findPokemonCard(){
        String name;
        do{
            name = UI.askString("Enter name of Pokemon");
            if(this.isEmpty(name) == true){
                UI.println("Please enter a pokemon name");
            }
        }while(this.isEmpty(name) == true); // checks name is not empty
        
        if (collection.findPokemonCard(name.toLowerCase())){
            clear();
            UI.println("Found Pokemon!");
            displayPokemon();
        }else{
            UI.println("Card not found :C");
        };
    }
    
    /**
     * Display pokemon information
     */
    public void displayPokemon(){
        pokemonCard = collection.getPokemonCard();
        UI.println("Name: " + pokemonCard.getName());
        UI.println("Value: $" + pokemonCard.getValue());
        pokemonCard.displayImage(20, 20);  //Show image on canvas
    }
    /**
     * manages mouse
     */
    public void mouse(String action, double x, double y){
        if (action.equals("released")){
            if (pokemonCard==null) {
                return;
            }
            if (pokemonCard.onProfile(x,y)){
                clear();
            }
        }
    }
    
    /**
     * clear all
     */
    public void clear(){
        UI.clearText();
        UI.clearGraphics();
    }
    
    
}
