package artgallery.models;  

public abstract class Artwork implements Displayable {

    //STATIC VARIABLES - shared across ALL objects
    //only one copy exists in memory for all artworks
    private static int totalArtworks = 0;    //counts every artwork ever created
    public static String galleryName;        //package-level access (used in GalleryManager)

    //STATIC BLOCK 
    //runs ONCE automatically when the class is first loaded.
    //used to initialize static variables.
    static {
        galleryName = "The Grand Art Gallery";
        System.out.println("[System] Gallery initialized: " + galleryName);
    }

    //private  = only accessible inside this class
    //protected = accessible in this class + child classes
    private final int artworkId;       //final: ID cannot change once set 
    private String title;              
    private String artistName;         
    protected double price;            //protected: child classes can access directly
    private int yearCreated;           
    private boolean isSold;            
    private char category;             //char: 'P' = Painting, 'S' = Sculpture

    //PARAMETERIZED CONSTRUCTOR 
    //'this' keyword: distinguishes instance variable from parameter
    public Artwork(int artworkId, String title, String artistName,
                   double price, int yearCreated, char category) {
        this.artworkId   = artworkId;    //'this' refers to current object's field
        this.title       = title;
        this.artistName  = artistName;
        this.price       = price;
        this.yearCreated = yearCreated;
        this.isSold      = false;        //default value
        this.category    = category;
        totalArtworks++;                 //every new artwork increments static count
    }

    //COPY CONSTRUCTOR 
    //creates a new Artwork by copying data from an existing one
    public Artwork(Artwork other) {
        this.artworkId   = other.artworkId;
        this.title       = other.title;
        this.artistName  = other.artistName;
        this.price       = other.price;
        this.yearCreated = other.yearCreated;
        this.isSold      = other.isSold;
        this.category    = other.category;
        totalArtworks++;
    }

    //ABSTRACT METHODS 
    //no body here! Child classes MUST override these.
    public abstract void displayDetails();  //each artwork type displays its specific info
    public abstract String getType();       //returns "Painting" or "Sculpture"

    //METHOD OVERLOADING - display() with no parameter 
    //implements the display() method from Displayable interface
    @Override
    public void display() {
        showBorder();                    //calls default method from Displayable interface
        System.out.println("ID         : " + artworkId);
        System.out.println("Type       : " + getType());  //runtime Polymorphism 
        System.out.println("Title      : " + title);
        System.out.println("Artist     : " + artistName);
        System.out.printf("Price      : Rs. %.2f%n", price);
        System.out.println("Year       : " + yearCreated);
        System.out.println("Age        : " + getArtworkAge() + " year(s)");
        //TERNARY OPERATOR : condition ? valueIfTrue : valueIfFalse
        System.out.println("Status     : " + (isSold ? "SOLD" : "Available"));
        displayDetails();                //calls child class method (polymorphism)
        showBorder();
    }

    //METHOD OVERLOADING - display() with parameter 
    //same method name, different parameters = overloading
    public void display(boolean brief) {
        if (brief) {
            //brief one-line display
            System.out.printf("[%d] %-30s | %-10s | Rs.%-10.2f | %s%n",
                artworkId, title, getType(), price, (isSold ? "SOLD" : "Available"));
        } else {
            display(); //calls the full display version
        }
    }

    //STATIC METHOD 
    //belongs to the class, not any specific object
    //called as: Artwork.getTotalArtworks()
    public static int getTotalArtworks() {
        return totalArtworks;
    }

    public static void changeGalleryName(String newName) {
        galleryName = newName;
    }

    //returns age of artwork
    public int getArtworkAge() {
        return 2026 - yearCreated;  //arithmetic operation
    }

    //marks artwork as sold
    public void markAsSold() {
        this.isSold = true;
    }

    //GETTER METHODS - Encapsulation 
    //private fields are accessed through public getters
    public int    getArtworkId()   { return artworkId;   }
    public String getTitle()       { return title;        }
    public String getArtistName()  { return artistName;   }
    public double getPrice()       { return price;        }
    public int    getYearCreated() { return yearCreated;  }
    public boolean isSold()        { return isSold;       }
    public char   getCategory()    { return category;     }

    //SETTER METHODS - Encapsulation with validation 
    public void setTitle(String title)           { this.title = title;             }
    public void setArtistName(String artistName) { this.artistName = artistName;   }

    public void setPrice(double price) {
        if (price < 0) {     
            System.out.println("Error: Price cannot be negative.");
        } else {
            this.price = price;
        }
    }
}
