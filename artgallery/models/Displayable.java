package artgallery.models;   

//CONCEPT: Interface (Abstraction & Multiple Inheritance)
//An interface is a contract - any class that "implements" it
//MUST provide the body of all methods declared here.

public interface Displayable {

    //abstract method (no body) - the implementing class MUST write the body
    void display();

    //default method (Java 8+): Has a body, can be used as-is or overridden
    default void showBorder() {
        System.out.println("============================================");
    }
}
