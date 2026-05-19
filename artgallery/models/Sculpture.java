package artgallery.models;   
//import because ArtworkException is in a different package
import artgallery.exceptions.ArtworkException;

// CONCEPT: Hierarchical Inheritance 
// Both Painting and Sculpture extend Artwork = same parent, two children.
// That's called Hierarchical Inheritance.
// Also covers: super keyword, method overriding, constructor overloading

public class Sculpture extends Artwork implements Purchasable {

    //instance variables specific to Sculpture
    private String material;   // "Marble", "Bronze", "Wood", "Clay"
    private double height;     // height in centimeters (double data type)
    private double weight;     // weight in kilograms (double data type)

    //CONSTRUCTOR 1 - Full Parameterized Constructor
    public Sculpture(int artworkId, String title, String artistName,
                     double price, int yearCreated,
                     String material, double height, double weight) {
        super(artworkId, title, artistName, price, yearCreated, 'S'); //calls Artwork()
        this.material = material;
        this.height   = height;
        this.weight   = weight;
    }

    //CONSTRUCTOR 2 - Constructor Overloading 
    //when weight is not known, default to 0.0
    public Sculpture(int artworkId, String title, String artistName,
                     double price, int yearCreated, String material, double height) {
        this(artworkId, title, artistName, price, yearCreated, material, height, 0.0);
    }

    //OVERRIDING abstract methods from Artwork 
    @Override
    public void displayDetails() {
        System.out.println("Material   : " + material);
        System.out.printf("Height     : %.1f cm%n", height);
        System.out.printf("Weight     : %.1f kg%n", weight);
    }

    @Override
    public String getType() {
        return "Sculpture";
    }

    //implementing Purchasable interface 
    @Override
    public boolean purchase(Customer customer) throws ArtworkException {
        if (isSold()) {
            throw new ArtworkException(
                "Sculpture '" + getTitle() + "' is already sold!", 102); // throw 
        }
        markAsSold();
        System.out.println("  >> Sculpture purchased by: " + customer.getName());
        return true;
    }

    @Override
    public double calculateDiscountedPrice(double discountPercent) {
        return price - (price * discountPercent / 100);  // price is protected in Artwork
    }

    //Getter and Setter methods
    public String getMaterial() { return material; }
    public double getHeight()   { return height;   }
    public double getWeight()   { return weight;   }

    public void setMaterial(String material) { this.material = material; }
    public void setHeight(double height)     { this.height = height;     }
    public void setWeight(double weight)     { this.weight = weight;     }
}
