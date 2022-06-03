import java.util.HashMap;
import ecs100.*;
import java.util.ArrayList;
/**
 * class for interface
 *
 * @author AL
 * @version 26/05/22
 */
public class GUI
{
    // instance variables
    private Collection collection;          // declare Collection instance
    private PokemonCard pokemonCard;          // declare pokemonCard instance
    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        collection = new Collection();       //instantiate the collection
        UI.initialise();
        // buttons
        UI.addButton("Print All", this::printAll);
        UI.addButton("Add Card", this::addPokemonCard);
        UI.addButton("Find Card", this::findPokemonCard);
        UI.addButton("Delete Card", this::deletePokemonCard);
        UI.addButton("Clear", this::clear);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * prints all pokemon cards
     */
    public void printAll() {
        collection.printAll();
        collection.displayAll();
        UI.println("Click on a card to view it's details");
        UI.setMouseListener(this::printAllMouse);   // sets mouse listener
    }
    
    /**
     * Add a pokemon card to collection
     */
    public void addPokemonCard() {
        clear();
        UI.setMouseListener(this::defaultMouse);
        String name;
        double value;
        
        /**
         * Decided that numbers should be allowed as the user may
         * want to be specific when naming the pokemon card, 
         * like with pack names or with further ID
         * 
         * Decided symbols should be allowed because some pokemon 
         * have hyphens and dots in their name.
         */
        do {
            name = UI.askString("Name: ").toUpperCase();
            if (this.isEmpty(name) == true) {
                UI.println("Please enter a pokemon name");
            }
        } while(this.isEmpty(name) == true); // checks name is not empty
        
        /**
         * Decided there should be no upper cap on how much 
         * a pokemon card can be. As the most expensive card 
         * currently is $900,000 USD which translates to different 
         * amounts in different currencies.
         */
        do {
            value = UI.askDouble("Value: $");
            if (this.isNegative(value) == true) {
                UI.println("Please enter a value above 0");
            }
        } while(this.isNegative(value) == true); // checks if value < 0
        
        // add a image for display in the GUI
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        collection.addPokemonCard(name, value, imgFileName);
        
        clear();
        UI.println(name + " added");
        displayPokemon();   //displays just added pokemon
        
    }
    
    /**
     * Check if value is negative or 0
     * @param val value to check
     * @return boolean
     */
    public static boolean isNegative(double val) {
        if (val <= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if string is empty
     * @param string string to check
     * @return boolean
     */
    public static boolean isEmpty(String string) {
        if (string != null && !string.trim().isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Finds pokemon based on name
     * Prints pokemon details if 1 is found
     * if multiple are found, creates an array list of pokemon
     */
    public void findPokemonCard() {
        clear();
        UI.setMouseListener(this::defaultMouse);
        String name;
        do {
            name = UI.askString("Enter name of Pokemon");
            if (this.isEmpty(name) == true) {
                UI.println("Please enter a pokemon name");
            }
        } while(this.isEmpty(name) == true); // checks name is not empty
        
        ArrayList<Integer> searchResult = collection.findPokemonCard(name);
        if (searchResult.size() < 1) {
            UI.println("Card not found :C");
        }
        else if (searchResult.size() == 1) {
            // sets id to the only one in the search array
            int id = searchResult.get(0);
            // sets the pokemon
            pokemonCard = collection.getPokemonCard();
            clear();
            UI.println("Found Pokemon!");
            displayPokemon();
        }
        else {
            clear();
            UI.println("Click on the card to see details!");
            // set mouse listener
            UI.setMouseListener(this::printAllMouse);
            int locY = 20;  // y location
            int locX = 20;  // x location of first card
            int buffer = 10;    // gap inbetween cards
            int numberBullet = 1;   // number bullet point
            /**
             * cycles through search results and prints them
             */
            for (int  i = 0; i < searchResult.size(); i++) { 
                // sets id 
                int id = searchResult.get(i);
                // sets pokemon card
                pokemonCard = collection.getPokemonCard();
                UI.println(numberBullet + ") " + pokemonCard.getName());
                numberBullet++;        // increment number
                UI.println("");
                pokemonCard.displayImage(locX, locY);
                // moves next image over with a buffer inbetween
                locX += pokemonCard.WIDTH + buffer;
            } 
        }
    }
    
    /**
     * Delete pokemon card
     */
    public void deletePokemonCard() {
        collection.printAll();
        UI.setMouseListener(this::defaultMouse);
        int id;
        do {
            id = UI.askInt("Enter id of the Pokemon \nyou wish to delete: ");
            if (id > collection.getSize() || id < 1) {
                UI.println("Please enter a valid id\n");
            }
        } while (id > collection.getSize() || id < 1);
        
        String confirmation;
        do {
            confirmation = UI.askString("Are you sure? (y/n)\n").toLowerCase();
            if (confirmation.equals("y")) {
                // because the list id is increased by 1
                // so that the list starts from 1
                id = id - 1;
                collection.deletePokemonCard(id);
                UI.println("Pokemon deleted");
                collection.printAll();      // prints updated collection
            }
            else if (confirmation.equals("n")) {
                UI.println("aight then");
            }
            else {
                UI.println("Please enter y or n");
            }
        } while (!confirmation.equals("y") && !confirmation.equals("n"));
        
    }
    
    /**
     * Display pokemon information
     */
    public void displayPokemon() {
        pokemonCard = collection.getPokemonCard();
        UI.println("Name: " + pokemonCard.getName());
        UI.println("Value: $" + pokemonCard.getValue());
        pokemonCard.displayImage(20, 20);  //Show image on canvas
        UI.setMouseListener(this::pokemonMouse);    // sets mouse listener
    }
    
    /**
     * mouse settings when all pokemon are displayed
     * @param action of the mouse
     * @param x location of the mouse
     * @param y location of the mouse
     */
    public void printAllMouse(String action, double x, double y) {
        if (action.equals("released")) {
            if (collection.imageClick(x, y) == true) {
                clear();
                // if image is pressed on, details of pokemon are displayed
                displayPokemon();
            }
        }
    }
    
    /**
     * mouse settings when 1 pokemon details are displayed
     * @param action of the mouse
     * @param x location of the mouse
     * @param y location of the mouse
     */
    public void pokemonMouse(String action, double x, double y) {
        if (action.equals("released")) {
            if (pokemonCard.onProfile(x, y)) {
                clear();    // if image is pressed on, details are cleared
            }
        }
    }
    
    /**
     * default mouse settings
     * @param action of the mouse
     * @param x location of the mouse
     * @param y location of the mouse
     */
    public void defaultMouse(String action, double x, double y) {
    }
    
    /**
     * clear all
     */
    public void clear() {
        UI.clearText();
        UI.clearGraphics();
    }
    
    
}
