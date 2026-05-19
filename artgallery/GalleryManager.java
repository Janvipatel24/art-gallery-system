package artgallery;   

// ============================================================
// MAIN CLASS: Art Gallery Management System
// This is the entry point of the program (has main() method)
//
// Concepts covered here:
// - Static variables, static block
// - Arrays of objects (Artwork[], Customer[])
// - Scanner for input
// - Switch-case, if-else, for loop, while loop, do-while
// - Exception handling: try-catch-finally, multiple catch
// - instanceof operator
// - Method overloading calls
// - Runtime Polymorphism
// - Generic class usage
// ============================================================

import artgallery.models.*;        //imports ALL classes from artgallery.models
import artgallery.exceptions.*;    //imports ArtworkException
import artgallery.reports.*;       //imports GalleryReport (new package)
import java.util.Scanner;          //built-in Java Scanner class

public class GalleryManager {

    //STATIC FINAL VARIABLES 
    //'final' means these values cannot be changed after assignment
    private static final int MAX_ARTWORKS=50;
    private static final int MAX_CUSTOMERS = 20;

    //ARRAY OF OBJECTS 
    //artworks[] can hold Painting, Sculpture, SignedArtwork objects
    //this is possible because of Inheritance (all extend Artwork)
    private static Artwork[]  artworks  = new Artwork[MAX_ARTWORKS];
    private static Customer[] customers = new Customer[MAX_CUSTOMERS];
    private static int artworkCount  = 0;
    private static int customerCount = 0;

    // Single Scanner object for all input
    private static Scanner sc = new Scanner(System.in);

    // STATIC BLOCK - Runs before main()
    // Loads some sample data so the program has artworks from the start
    static {
        System.out.println("[System] Loading sample gallery data...");
        loadSampleData();
        System.out.println("[System] Sample data loaded. " +
                           artworkCount + " artworks, " + customerCount + " customers.\n");
    }

    // Load sample artworks and customers into arrays
    private static void loadSampleData() {
        // Paintings
        artworks[artworkCount++] = new Painting(
            101, "Sunrise in India", "Ravi Verma",
            150000, 1995, "Oil", "Realism", "36 x 48 inches");

        artworks[artworkCount++] = new Painting(
            102, "Abstract Dreams", "Meera Shah",
            85000, 2010, "Acrylic", "Abstract", "24 x 24 inches");

        // Signed/Certified Painting (Multilevel Inheritance demo)
        artworks[artworkCount++] = new SignedArtwork(
            103, "The Golden Hour", "Aryan Kapoor",
            320000, 2001, "Oil", "Impressionism", "48 x 60 inches",
            "CERT-2001-GH", "National Gallery of India");

        // Sculptures
        artworks[artworkCount++] = new Sculpture(
            104, "The Thinker - Modern", "Arjun Pillai",
            250000, 2005, "Bronze", 45.5, 12.3);

        artworks[artworkCount++] = new Sculpture(
            105, "Goddess of Peace", "Priya Nair",
            180000, 2018, "Marble", 60.0, 25.0);

        // Customers with Address (Aggregation demo)
        customers[customerCount++] = new Customer(
            "C001", "Amit Sharma", "amit@gmail.com", 9876543210L,
            new Address("12 MG Road", "Mumbai", "Maharashtra"));

        customers[customerCount++] = new Customer(
            "C002", "Sunita Patel", "sunita@gmail.com", 9123456789L,
            new Address("45 SG Highway", "Ahmedabad", "Gujarat"));
    }

    // ===========================================================
    // MAIN METHOD - Program starts here
    // ===========================================================
    public static void main(String[] args) {
        System.out.println("Welcome to " + Artwork.galleryName);
        System.out.println("Type a number and press Enter to navigate the menu.\n");

        int choice;

        // DO-WHILE LOOP - runs at least once, keeps looping until user exits
        do {
            displayMainMenu();

            try {
                // Read user input and parse it to integer
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                // EXCEPTION HANDLING: if user types a letter instead of a number
                System.out.println("  [Error] Please enter a valid number!\n");
                choice = -1; // invalid choice - will show "invalid option"
            }

            processMenuChoice(choice); // handle the selected option

        } while (true); // loop forever; exit is done in case 0 using System.exit(0)
    }

    // -------------------------------------------------------
    // Display the menu
    // -------------------------------------------------------
    private static void displayMainMenu() {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|    ART GALLERY MANAGEMENT SYSTEM         |");
        System.out.println("|    " + Artwork.galleryName + "    |");
        System.out.println("+------------------------------------------+");
        System.out.println("|  1. Add New Artwork                       |");
        System.out.println("|  2. Display ALL Artworks (Full Details)   |");
        System.out.println("|  3. Display ALL Artworks (Brief List)     |");
        System.out.println("|  4. Search Artwork by ID                  |");
        System.out.println("|  5. Purchase Artwork                      |");
        System.out.println("|  6. Display Available Artworks Only       |");
        System.out.println("|  7. Display Artworks by Type              |");
        System.out.println("|  8. Gallery Statistics                    |");
        System.out.println("|  9. Add New Customer                      |");
        System.out.println("| 10. Display Customer Details              |");
        System.out.println("| 11. Generate Reports                      |");
        System.out.println("|  0. Exit                                  |");
        System.out.println("+------------------------------------------+");
        System.out.print("Enter your choice: ");
    }

    // SWITCH STATEMENT - routes to correct function
    private static void processMenuChoice(int choice) {
        switch (choice) {
            case 1:  addArtwork();              break;
            case 2:  displayAllArtworksFull();  break;
            case 3:  displayAllArtworksBrief(); break;
            case 4:  searchArtwork();           break;
            case 5:  purchaseArtwork();         break;
            case 6:  displayAvailableArtworks();break;
            case 7:  displayByType();           break;
            case 8:  displayStatistics();       break;
            case 9:  addCustomer();             break;
            case 10: displayCustomerDetails();  break;
            case 11: generateReports();         break;   
            case 0:
                System.out.println("\nThank you for visiting " + Artwork.galleryName + ". Goodbye!");
                sc.close();
                System.exit(0);  // terminates the program
            default:
                System.out.println("  [Invalid] Please choose a number from 0 to 10.");
        }
    }

    // ===========================================================
    //                    MENU OPTION 1: ADD ARTWORK
    // ===========================================================
    private static void addArtwork() {
        System.out.println("\n--- ADD NEW ARTWORK ---");

        // Check if gallery is full
        if (artworkCount >= MAX_ARTWORKS) {
            System.out.println("Gallery is full! Cannot add more artworks.");
            return; // exit the method
        }

        // Choose type
        System.out.println("Select Artwork Type:");
        System.out.println("  1. Painting");
        System.out.println("  2. Sculpture");
        System.out.println("  3. Signed/Certified Painting");
        System.out.print("Enter type: ");

        int typeChoice;
        try {
            typeChoice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid type selection!");
            return;
        }

        // Get Artwork ID
        System.out.print("Enter Artwork ID (number): ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
            return;
        }

        // Check if ID already exists (using a helper method)
        if (findArtworkById(id) != null) {
            System.out.println("Artwork with ID " + id + " already exists!");
            return;
        }

        // Collect common fields
        System.out.print("Enter Title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine().trim();

        double price;
        try {
            System.out.print("Enter Price (Rs): ");
            price = Double.parseDouble(sc.nextLine().trim());

            // Validation using if-else 
            if (price < 0) {
                System.out.println("Price cannot be negative!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price!");
            return;
        }

        int year;
        try {
            System.out.print("Enter Year Created (1000-2026): ");
            year = Integer.parseInt(sc.nextLine().trim());
            if (year < 1000 || year > 2026) {
                System.out.println("Please enter a valid year (1000-2026).");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid year!");
            return;
        }

        // IF-ELSE to decide which type to create
        if (typeChoice == 1) {
            // ADD PAINTING
            System.out.print("Enter Medium (Oil / Acrylic / Watercolor): ");
            String medium = sc.nextLine().trim();

            System.out.print("Enter Style (Realism / Abstract / etc.): ");
            String style = sc.nextLine().trim();

            System.out.print("Enter Dimensions (e.g., 24 x 36 inches): ");
            String dimensions = sc.nextLine().trim();

            artworks[artworkCount++] = new Painting(
                id, title, artist, price, year, medium, style, dimensions);
            System.out.println("\n[SUCCESS] Painting added successfully!");

        } else if (typeChoice == 2) {
            // ADD SCULPTURE
            System.out.print("Enter Material (Marble / Bronze / Wood): ");
            String material = sc.nextLine().trim();

            double height, weight;
            try {
                System.out.print("Enter Height (cm): ");
                height = Double.parseDouble(sc.nextLine().trim());
                System.out.print("Enter Weight (kg): ");
                weight = Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid height or weight!");
                return;
            }

            artworks[artworkCount++] = new Sculpture(
                id, title, artist, price, year, material, height, weight);
            System.out.println("\n[SUCCESS] Sculpture added successfully!");

        } else if (typeChoice == 3) {
            // ADD SIGNED ARTWORK
            System.out.print("Enter Medium: ");
            String medium = sc.nextLine().trim();
            System.out.print("Enter Style: ");
            String style = sc.nextLine().trim();
            System.out.print("Enter Dimensions: ");
            String dimensions = sc.nextLine().trim();
            System.out.print("Enter Certificate Number: ");
            String certNum = sc.nextLine().trim();
            System.out.print("Enter Signed/Authenticated By: ");
            String signedBy = sc.nextLine().trim();

            artworks[artworkCount++] = new SignedArtwork(
                id, title, artist, price, year, medium, style, dimensions, certNum, signedBy);
            System.out.println("\n[SUCCESS] Signed Artwork added successfully!");

        } else {
            System.out.println("Invalid artwork type! No artwork was added.");
        }
    }

    // ===========================================================
    //         MENU OPTION 2: DISPLAY ALL ARTWORKS (FULL)
    // ===========================================================
    private static void displayAllArtworksFull() {
        System.out.println("\n--- ALL ARTWORKS IN " + Artwork.galleryName.toUpperCase() + " ---");

        if (artworkCount == 0) {
            System.out.println("No artworks in the gallery.");
            return;
        }

        // FOR LOOP  - iterate through all artworks
        for (int i = 0; i < artworkCount; i++) {
            artworks[i].display(); // RUNTIME POLYMORPHISM 
            // The correct display() is called based on actual object type
            // (Painting's display, Sculpture's display, etc.)
        }

        System.out.println("Total: " + artworkCount + " artworks");
    }

    // ===========================================================
    //         MENU OPTION 3: DISPLAY ALL ARTWORKS (BRIEF)
    // ===========================================================
    private static void displayAllArtworksBrief() {
        System.out.println("\n--- ARTWORK LIST (BRIEF) ---");
        System.out.printf("  %-5s %-32s %-18s %-12s %s%n",
            "ID", "Title", "Type", "Price(Rs)", "Status");
        System.out.println("  " + "-".repeat(80));

        // FOR-EACH LOOP 
        // We can iterate from 0 to artworkCount, or use a temporary copy
        for (int i = 0; i < artworkCount; i++) {
            artworks[i].display(true); // calls overloaded display(boolean brief) 
        }
        System.out.println("Total Artworks: " + artworkCount);
    }

    // ===========================================================
    //         MENU OPTION 4: SEARCH BY ID
    // ===========================================================
    private static void searchArtwork() {
        System.out.print("\nEnter Artwork ID to search: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Artwork found = findArtworkById(id);  // helper method

            if (found != null) {
                System.out.println("\n[FOUND] Artwork Details:");
                found.display();

                // INSTANCEOF OPERATOR - check what type the object actually is
                if (found instanceof SignedArtwork) {
                    // Downcasting: Artwork -> SignedArtwork to access specific methods
                    SignedArtwork sa = (SignedArtwork) found;
                    System.out.println("[INFO] This is a Certified Artwork.");
                    System.out.println("[INFO] Certificate: " + sa.getCertificateNumber());

                } else if (found instanceof Painting) {
                    // instanceof checks before casting
                    Painting p = (Painting) found;
                    System.out.println("[INFO] Medium: " + p.getMedium() +
                                       " | Style: " + p.getStyle());

                } else if (found instanceof Sculpture) {
                    Sculpture s = (Sculpture) found;
                    System.out.println("[INFO] Material: " + s.getMaterial() +
                                       " | Height: " + s.getHeight() + " cm");
                }

                // Show discounted price as bonus info
                if (found instanceof Purchasable) {
                    Purchasable p = (Purchasable) found;
                    double discounted = p.calculateDiscountedPrice(10); // 10% discount
                    System.out.printf("[INFO] Price with 10%% discount: Rs. %.2f%n", discounted);
                }

            } else {
                System.out.println("[NOT FOUND] No artwork found with ID: " + id);
            }

        } catch (NumberFormatException e) {
            System.out.println("[Error] ID must be a number!");
        }
    }

    // ===========================================================
    //         MENU OPTION 5: PURCHASE ARTWORK
    // ===========================================================
    private static void purchaseArtwork() {
        System.out.println("\n--- PURCHASE ARTWORK ---");

        if (customerCount == 0) {
            System.out.println("No customers registered. Please add a customer first (option 9).");
            return;
        }

        System.out.print("Enter Artwork ID to purchase: ");
        int artworkId;
        try {
            artworkId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Artwork ID!");
            return;
        }

        System.out.print("Enter Customer ID (e.g., C001): ");
        String customerId = sc.nextLine().trim();

        Artwork artwork   = findArtworkById(artworkId);
        Customer customer = findCustomerById(customerId);

        // Validate both exist
        if (artwork == null) {
            System.out.println("[Error] Artwork with ID " + artworkId + " not found.");
            return;
        }
        if (customer == null) {
            System.out.println("[Error] Customer with ID " + customerId + " not found.");
            return;
        }

        // EXCEPTION HANDLING: try-catch-finally 
        try {
            System.out.println("\nProcessing purchase...");
            System.out.println("  Artwork : " + artwork.getTitle());
            System.out.println("  Customer: " + customer.getName());

            // Check if it's purchasable (using instanceof)
            if (artwork instanceof Purchasable) {
                Purchasable purchasable = (Purchasable) artwork; // downcasting

                // This line can THROW ArtworkException (if already sold)
                boolean success = purchasable.purchase(customer);

                if (success) {
                    customer.recordPurchase(artwork.getPrice()); // update customer record
                    System.out.println("\n[SUCCESS] Purchase Successful!");
                    System.out.printf("  Amount Paid: Rs. %.2f%n", artwork.getPrice());
                }
            } else {
                System.out.println("[Error] This artwork cannot be purchased.");
            }

        } catch (ArtworkException e) {
            // CATCHING CUSTOM EXCEPTION 
            System.out.println("\n[PURCHASE FAILED]");
            System.out.println("  Error Code: " + e.getErrorCode());
            System.out.println("  Reason    : " + e.getMessage());

        } finally {
            // FINALLY BLOCK: always executes, whether exception occurred or not (Unit 2)
            System.out.println("  [Transaction Ended]");
        }
    }

    // ===========================================================
    //         MENU OPTION 6: DISPLAY AVAILABLE ARTWORKS
    // ===========================================================
    private static void displayAvailableArtworks() {
        System.out.println("\n--- AVAILABLE ARTWORKS (Not Sold) ---");
        System.out.printf("  %-5s %-32s %-18s %s%n", "ID", "Title", "Type", "Price (Rs)");
        System.out.println("  " + "-".repeat(65));

        int count = 0;

        // FOR LOOP with IF condition 
        for (int i = 0; i < artworkCount; i++) {
            if (!artworks[i].isSold()) {     // if NOT sold
                artworks[i].display(true);   // brief display
                count++;
            }
        }

        // Ternary operator in output
        System.out.println("\n  Total Available: " + count +
            (count == 0 ? " (All artworks are sold!)" : ""));
    }

    // ===========================================================
    //         MENU OPTION 7: DISPLAY BY TYPE
    // ===========================================================
    private static void displayByType() {
        System.out.println("\n--- DISPLAY BY TYPE ---");
        System.out.println("  1. Paintings (including Signed)");
        System.out.println("  2. Sculptures");
        System.out.println("  3. Signed Artworks Only");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }

        boolean found = false;

        // WHILE LOOP - using loop variable
        int i = 0;
        while (i < artworkCount) {
            Artwork a = artworks[i];

            // INSTANCEOF operator to check type 
            boolean shouldDisplay = false;

            switch (choice) {
                case 1: shouldDisplay = (a instanceof Painting);       break; // includes SignedArtwork
                case 2: shouldDisplay = (a instanceof Sculpture);      break;
                case 3: shouldDisplay = (a instanceof SignedArtwork);  break;
                default: break;
            }

            if (shouldDisplay) {
                a.display();  // Polymorphic call
                found = true;
            }

            i++; // increment while loop variable
        }

        if (!found) {
            System.out.println("No artworks of that type found.");
        }
    }

    // ===========================================================
    //         MENU OPTION 8: GALLERY STATISTICS
    // ===========================================================
    private static void displayStatistics() {
        System.out.println("\n--- GALLERY STATISTICS ---");

        // Variables to collect statistics
        int soldCount     = 0;
        int paintingCount = 0;
        int sculptureCount = 0;
        int signedCount   = 0;
        double totalValue = 0;
        double soldValue  = 0;

        // FOR LOOP to calculate stats 
        for (int i = 0; i < artworkCount; i++) {
            Artwork a = artworks[i];
            totalValue += a.getPrice();

            if (a.isSold()) {
                soldCount++;
                soldValue += a.getPrice();
            }

            // instanceof 
            if (a instanceof SignedArtwork)    signedCount++;
            else if (a instanceof Painting)    paintingCount++;
            else if (a instanceof Sculpture)   sculptureCount++;
        }

        // USING GENERIC CLASS GalleryPair<K, V> 
        System.out.println("\n  Gallery: " + Artwork.galleryName);
        System.out.println("  " + "=".repeat(40));

        new GalleryPair<>("Total Artworks",       artworkCount).display();
        new GalleryPair<>("Paintings",             paintingCount).display();
        new GalleryPair<>("Signed Artworks",       signedCount).display();
        new GalleryPair<>("Sculptures",            sculptureCount).display();
        new GalleryPair<>("Artworks Sold",         soldCount).display();
        new GalleryPair<>("Artworks Available",    artworkCount - soldCount).display();
        new GalleryPair<>("Registered Customers",  customerCount).display();

        System.out.println("  " + "=".repeat(40));
        System.out.printf("  %-25s: Rs. %.2f%n", "Total Gallery Value", totalValue);
        System.out.printf("  %-25s: Rs. %.2f%n", "Total Sales Revenue", soldValue);
        System.out.printf("  %-25s: Rs. %.2f%n", "Unsold Value", totalValue - soldValue);
        System.out.println("  " + "=".repeat(40));

        // Static method call
        System.out.println("  Total Artwork Objects Created: " + Artwork.getTotalArtworks());
    }

    // ===========================================================
    //         MENU OPTION 9: ADD CUSTOMER
    // ===========================================================
    private static void addCustomer() {
        System.out.println("\n--- ADD NEW CUSTOMER ---");

        if (customerCount >= MAX_CUSTOMERS) {
            System.out.println("Customer list is full!");
            return;
        }

        System.out.print("Enter Customer ID (e.g., C003): ");
        String id = sc.nextLine().trim();

        if (findCustomerById(id) != null) {
            System.out.println("Customer with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Full Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        long phone;
        try {
            System.out.print("Enter 10-digit Phone Number: ");
            phone = Long.parseLong(sc.nextLine().trim()); // long data type parsing
        } catch (NumberFormatException e) {
            System.out.println("Invalid phone number! Must be numeric.");
            return;
        }

        System.out.print("Enter Street: ");
        String street = sc.nextLine().trim();
        System.out.print("Enter City: ");
        String city = sc.nextLine().trim();
        System.out.print("Enter State: ");
        String state = sc.nextLine().trim();

        Address address = new Address(street, city, state); // creating Address object
        customers[customerCount++] = new Customer(id, name, email, phone, address);
        System.out.println("\n[SUCCESS] Customer '" + name + "' added successfully!");
    }

    // ===========================================================
    //         MENU OPTION 10: DISPLAY CUSTOMER DETAILS
    // ===========================================================
    private static void displayCustomerDetails() {
        System.out.println("\n--- CUSTOMER DETAILS ---");

        // First, show list of all customers
        System.out.println("  Registered Customers:");
        for (int i = 0; i < customerCount; i++) {
            System.out.println("  - " + customers[i].getCustomerId() +
                               " | " + customers[i].getName());
        }

        System.out.print("\nEnter Customer ID: ");
        String id = sc.nextLine().trim();

        Customer customer = findCustomerById(id);
        if (customer != null) {
            System.out.println("\n  -- Customer Information --");
            customer.displayInfo();
        } else {
            System.out.println("[Not Found] No customer with ID: " + id);
        }
    }

    // ===========================================================
    //         MENU OPTION 11: GENERATE REPORTS (NEW)
    //         Uses the brand new artgallery.reports package
    // ===========================================================
    private static void generateReports() {
        System.out.println("\n--- GENERATE REPORTS ---");
        System.out.println("  1. Full Report (All Artworks + Customers)");
        System.out.println("  2. Sold Artworks Report");
        System.out.println("  3. Artworks by Type Report");
        System.out.println("  4. Quick Summary Report");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }

        // Calling static methods from the new artgallery.reports.GalleryReport class
        switch (choice) {
            case 1:
                GalleryReport.generateFullReport(artworks, artworkCount, customers, customerCount);
                break;
            case 2:
                GalleryReport.generateSoldReport(artworks, artworkCount);
                break;
            case 3:
                GalleryReport.generateTypeReport(artworks, artworkCount);
                break;
            case 4:
                GalleryReport.generateReport(artworks, artworkCount);
                break;
            default:
                System.out.println("Invalid report choice.");
        }
    }
    
    // ===========================================================
    //                    HELPER METHODS
    // ===========================================================

    // Find artwork by ID - returns null if not found
    private static Artwork findArtworkById(int id) {
        for (int i = 0; i < artworkCount; i++) {
            if (artworks[i].getArtworkId() == id) {
                return artworks[i]; // found
            }
        }
        return null; // not found
    }

    // Find customer by ID - returns null if not found
    private static Customer findCustomerById(String id) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equals(id)) {
                return customers[i]; // found
            }
        }
        return null; // not found
    }
}
