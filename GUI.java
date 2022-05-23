import java.util.HashMap;
import ecs100.*;

/**
 * code of GUI
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    // instance variables
    private Collection collection;        // declare Collection instance
    private pokemonCard pokemonCard;          // declare pokemonCard sinstance


    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        collection = new Collection();       //instantiate the collection
        UI.initialise();
        UI.addButton("Print All", collection::printAll);
        UI.addButton("Add", this::addPokemonCard);
        UI.addButton("Find", this::findPokemonCard);
        UI.addButton("Hide Details", this::clear);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::mouse);
    }
    
    /**
     * Add a pokemon card to collection
     */
    public void addPokemonCard(){
        clear();
        String name;
        do{
            name = UI.askString("Name: ").toLowerCase();
        }while(this.isEmpty(name) == true);
        
        double value = UI.askDouble("Value: $");
        
        // add a book image for display in the GUI
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        collection.setPokemonCardId(); // Increment the ID by one
        collection.addPokemonCard(name, value, imgFileName);
        
        clear();
        UI.println(name + " added");
        displayPokemon();   //displays just added pokemon
        
    }
    
    /**
     * Checks if string is numeric
     */
    public static boolean isNumeric(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch (NumberFormatException e){
            UI.println("Please enter a number");
            return false;
        }
    }
    
    /**
     * check if string is empty
     */
    public static boolean isEmpty(String string){
        if(string != null && !string.trim().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Finds book based on name
     * Prints out the author and qty if found
     */
    public void findPokemonCard(){
        String pokemonCardName = UI.askString("Enter name of Pokemon");
        if (collection.findPokemonCard(pokemonCardName.toLowerCase())){
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
        pokemonCard.displayImage(10, 10);  //Show image on canvas
    }
    /**
     * manages mouse
     */
    public void mouse(String action, double x, double y){
        if (action.equals("released")){
            if (pokemonCard==null) {
                UI.printMessage("Create some Collection");
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
