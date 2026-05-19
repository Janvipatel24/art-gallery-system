package artgallery.models; 

//import because ArtworkException is in a different package
import artgallery.exceptions.ArtworkException;

// CONCEPT: Multilevel Inheritance 
// Chain: SignedArtwork -> Painting -> Artwork
// (Grandchild -> Child -> Parent)
// Also shows: super keyword to call grandparent's constructor via parent

public class SignedArtwork extends Painting {

    //extra fields specific to signed/certificate artworks
    private String certificateNumber;   //authentication certificate ID
    private String signedBy;            //who authenticated/signed it
    private boolean isAuthenticated;    //boolean data type

    //CONSTRUCTOR
    //calls Painting's constructor using super() 
    public SignedArtwork(int artworkId, String title, String artistName,
                         double price, int yearCreated,
                         String medium, String style, String dimensions,
                         String certificateNumber, String signedBy) {
        //calls Painting's constructor (which in turn calls Artwork's constructor)
        super(artworkId, title, artistName, price, yearCreated, medium, style, dimensions);
        this.certificateNumber = certificateNumber;
        this.signedBy          = signedBy;
        this.isAuthenticated   = true;  //all signed artworks are authenticated
    }

    //METHOD OVERRIDING 
    //we override displayDetails() from Painting,
    //but also call Painting's version using super.displayDetails()
    @Override
    public void displayDetails() {
        super.displayDetails();  //'super' calls parent (Painting's) displayDetails 
        System.out.println("-- Certificate Info --");
        System.out.println("Certificate : " + certificateNumber);
        System.out.println("Signed By   : " + signedBy);
        //ternary operator 
        System.out.println("Authenticated: " + (isAuthenticated ? "YES" : "NO"));
    }

    @Override
    public String getType() {
        return "Signed Painting";
    }

    //override purchase to show it's a signed artwork being sold
    @Override
    public boolean purchase(Customer customer) throws ArtworkException {
        if (isSold()) {
            throw new ArtworkException(
                "Signed Artwork '" + getTitle() + "' is already sold!", 103);
        }
        markAsSold();
        System.out.println("  >> Signed Artwork purchased by: " + customer.getName());
        System.out.println("  >> Certificate: " + certificateNumber + " transferred.");
        return true;
    }

    public String getCertificateNumber() { return certificateNumber; }
    public String getSignedBy()          { return signedBy;          }
    public boolean isAuthenticated()     { return isAuthenticated;   }
}
