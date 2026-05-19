package artgallery.models;

public class Address {

    //ENCAPSULATION 
    //making variables private so they can't be directly accessed outside
    private String street;
    private String city;
    private String state;

    //---- PARAMETERIZED CONSTRUCTOR ----
    //'this' keyword: used when parameter name is same as field name
    public Address(String street, String city, String state) {
        this.street = street;  //this.street = class field, street = parameter
        this.city   = city;
        this.state  = state;
    }

    //---- DEFAULT CONSTRUCTOR (Constructor Overloading) ----
    //calls the parameterized constructor using this()
    public Address() {
        this("Not Provided", "Not Provided", "Not Provided");
    }

    //---- GETTER METHODS (Encapsulation) ----
    public String getStreet() { return street; }
    public String getCity()   { return city;   }
    public String getState()  { return state;  }

    //---- SETTER METHODS (Encapsulation) ----
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city)     { this.city = city;     }
    public void setState(String state)   { this.state = state;   }

    //returns a formatted address string
    public String getFormattedAddress() {
        return street + ", " + city + ", " + state;
    }
}
