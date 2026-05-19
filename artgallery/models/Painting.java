package artgallery.models;  

//import because ArtworkException is now in a different package
import artgallery.exceptions.ArtworkException;

// CONCEPT: Single Inheritance 
// Painting extends Artwork = Painting IS-A Artwork
// Also: Multiple Interface Implementation (Purchasable + Displayable via Artwork)
// Also covers: super keyword, constructor overloading, method overriding

public class Painting extends Artwork implements Purchasable {

    //additional attributes specific to Paintings
    private String medium;      // "Oil", "Watercolor", "Acrylic"
    private String style;       // "Realism", "Abstract", "Impressionism"
    private String dimensions;  // "24 x 36 inches"

    //CONSTRUCTOR 1 - Full Parameterized Constructor
    //'super()' calls parent class (Artwork) constructor 
    public Painting(int artworkId, String title, String artistName,
                    double price, int yearCreated,
                    String medium, String style, String dimensions) {
        //super() MUST be the first statement in a constructor
        super(artworkId, title, artistName, price, yearCreated, 'P');
        this.medium     = medium;
        this.style      = style;
        this.dimensions = dimensions;
    }

    //CONSTRUCTOR 2 - Overloaded Constructor 
    //when dimensions is not known, we use a default value.
    //this() calls Constructor 1 - must be first statement.
    public Painting(int artworkId, String title, String artistName,
                    double price, int yearCreated, String medium) {
        this(artworkId, title, artistName, price, yearCreated,
             medium, "Mixed", "Not Specified");  //calls Constructor 1
    }

    //METHOD OVERRIDING
    //@Override: tells Java we are intentionally overriding parent's method
    //provides specific implementation of abstract method from Artwork
    @Override
    public void displayDetails() {
        System.out.println("Medium     : " + medium);
        System.out.println("Style      : " + style);
        System.out.println("Dimensions : " + dimensions);
    }

    @Override
    public String getType() {
        return "Painting";
    }

    //IMPLEMENTING Purchasable interface method 
    //'throws ArtworkException': declares this can throw our custom exception
    @Override
    public boolean purchase(Customer customer) throws ArtworkException {
        if (isSold()) {
            //'throw' keyword: explicitly throws an exception 
            throw new ArtworkException(
                "Painting '" + getTitle() + "' is already sold!", 101);
        }
        markAsSold();  //calls parent class method
        System.out.println("  >> Painting purchased by: " + customer.getName());
        return true;
    }

    @Override
    public double calculateDiscountedPrice(double discountPercent) {
        //to calculate discount
        double discount = price * discountPercent / 100;
        return price - discount;  //'price' is accessible (protected in Artwork)
    }

    //Getter methods
    public String getMedium()     { return medium;     }
    public String getStyle()      { return style;      }
    public String getDimensions() { return dimensions; }
}
