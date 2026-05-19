package artgallery.models;   

//import because ArtworkException is in a different package
import artgallery.exceptions.ArtworkException;

// CONCEPT: Second Interface 
// A class can implement MULTIPLE interfaces - this is how Java
// achieves multiple inheritance (since classes can't extend
// more than one class).

public interface Purchasable {

    //'throws ArtworkException' means this method can throw a custom exception
    //the caller must handle it : Exception Handling
    boolean purchase(Customer customer) throws ArtworkException;//abstract method - no body, must be implemented by child classes

    //method to calculate price after discount
    double calculateDiscountedPrice(double discountPercent);
}