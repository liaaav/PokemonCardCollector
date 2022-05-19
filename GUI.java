import ecs100.*;
/**
 * Write a description of class GUI here.
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
        collection = new Collection();       //instantiate the books object
        UI.initialise();
        UI.addButton("Print All", collection::printAll);
        UI.addButton("Add", this::addpokemonCard);
        UI.addButton("Find", this::findpokemonCard);
        UI.addButton("Hide Details", this::clear);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::mouse);
    }
    
    /**
     * Add a book to collection
     */
    public void addpokemonCard(){
        final int INCREMENT = 1;
        UI.clearText();
        String number;
        String name = UI.askString("Name: ");
        do {
            number = UI.askString("Number: ");
        }while(isNumeric(number) != true);
        
        // add a book image for display in the GUI
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        collection.setpokemonCardId(); // Increment the ID by one
        collection.addpokemonCard(name, number, imgFileName); 
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
     * Finds book based on name
     * Prints out the author and qty if found
     */
    public void findpokemonCard(){
        String pokemonCardName = UI.askString("Enter name of pokemonCard");
        if (collection.findpokemonCard(pokemonCardName.toLowerCase())){
            UI.clearText();
            UI.clearGraphics();
            UI.println("Found pokemonCard!");
            pokemonCard = collection.getpokemonCard();
            UI.println("Name: " + pokemonCard.getName());
            UI.println("Number: " + pokemonCard.getNumber());
            pokemonCard.displayProfile();  //Show profile on canvas
        }else{
            UI.println("pokemonCard not found");
        };
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
