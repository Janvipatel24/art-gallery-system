package artgallery.models;  

//CONCEPT: Encapsulation + Aggregation 
//Encapsulation: all fields are private, accessed through getters/setters
//Aggregation: Customer HAS-A Address (Customer uses an Address object)

public class Customer {

    //private data members - ENCAPSULATION 
    private String customerId;
    private String name;
    private String email;
    private long   phoneNumber;      
    private Address address;         //AGGREGATION: Customer HAS-A Address 
    private int    totalPurchases;   
    private double totalAmountSpent; 

    //CONSTRUCTOR 1 - Full parameterized constructor
    public Customer(String customerId, String name, String email,
                    long phoneNumber, Address address) {
        this.customerId      = customerId;    
        this.name            = name;
        this.email           = email;
        this.phoneNumber     = phoneNumber;
        this.address         = address;       //aggregated object
        this.totalPurchases  = 0;
        this.totalAmountSpent = 0.0;
    }

    //CONSTRUCTOR 2 - Constructor Overloading 
    //if address is not given, create a default one
    public Customer(String customerId, String name, String email, long phoneNumber) {
        this(customerId, name, email, phoneNumber, new Address()); //calls Constructor 1
    }

    //business method - records a purchase
    public void recordPurchase(double amount) {
        totalPurchases++;        
        totalAmountSpent += amount; 
    }

    //display customer information
    public void displayInfo() {
        System.out.println("Customer ID    : " + customerId);
        System.out.println("Name           : " + name);
        System.out.println("Email          : " + email);
        System.out.println("Phone          : " + phoneNumber);
        //accessing aggregated Address object's method
        System.out.println("Address        : " + address.getFormattedAddress());
        System.out.println("Total Purchases: " + totalPurchases);
        System.out.printf("Total Spent    : Rs. %.2f%n", totalAmountSpent);
    }

    //GETTER METHODS - Encapsulation 
    public String getCustomerId()      { return customerId;       }
    public String getName()            { return name;             }
    public String getEmail()           { return email;            }
    public long   getPhoneNumber()     { return phoneNumber;      }
    public Address getAddress()        { return address;          }
    public int    getTotalPurchases()  { return totalPurchases;   }
    public double getTotalAmountSpent(){ return totalAmountSpent; }

    //SETTER METHODS - Encapsulation 
    public void setEmail(String email)     { this.email = email;     }
    public void setAddress(Address address){ this.address = address; }
    public void setPhoneNumber(long phone) { this.phoneNumber = phone;}
}
