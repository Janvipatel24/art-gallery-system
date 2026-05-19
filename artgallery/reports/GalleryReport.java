package artgallery.reports;   //NEW PACKAGE

//import all model classes from artgallery.models package
import artgallery.models.Artwork;
import artgallery.models.Painting;
import artgallery.models.Sculpture;
import artgallery.models.SignedArtwork;
import artgallery.models.Customer;

// ============================================================
// NEW CLASS: GalleryReport
// This is a brand new class in the brand new 'artgallery.reports' package.
// Its job is to generate different types of summary reports
// from the gallery data.
//
// Concepts demonstrated here:
// - New package (artgallery.reports)
// - import statements (importing from artgallery.models)
// - Static methods (all report methods are static)
// - instanceof operator
// - for loops + if-else
// - printf formatting
// - Method overloading (generateReport with and without parameter)
// ============================================================

public class GalleryReport {
    //////////////////////////////////
    // Generates a complete full report of all artworks
    /////////////////////////////////
    public static void generateFullReport(Artwork[] artworks, int count,
                                          Customer[] customers, int custCount) {
        printHeader("FULL GALLERY REPORT");

        System.out.println("  Gallery : " + Artwork.galleryName);
        System.out.println("  " + "=".repeat(60));

        //section 1: All Artworks
        System.out.println("\n  ARTWORKS (" + count + " total)");
        System.out.println("  " + "-".repeat(60));
        System.out.printf("  %-5s %-28s %-16s %-12s %s%n",
            "ID", "Title", "Type", "Price(Rs)", "Status");
        System.out.println("  " + "-".repeat(60));

        for (int i = 0; i < count; i++) {
            System.out.printf("  %-5d %-28s %-16s %-12.0f %s%n",
                artworks[i].getArtworkId(),
                artworks[i].getTitle(),
                artworks[i].getType(),
                artworks[i].getPrice(),
                artworks[i].isSold() ? "SOLD" : "Available");
        }

        //section 2: Customer Summary
        System.out.println("\n  CUSTOMERS (" + custCount + " registered)");
        System.out.println("  " + "-".repeat(60));
        System.out.printf("  %-8s %-20s %-10s %s%n",
            "ID", "Name", "Purchases", "Total Spent(Rs)");
        System.out.println("  " + "-".repeat(60));

        for (int i = 0; i < custCount; i++) {
            System.out.printf("  %-8s %-20s %-10d %.2f%n",
                customers[i].getCustomerId(),
                customers[i].getName(),
                customers[i].getTotalPurchases(),
                customers[i].getTotalAmountSpent());
        }

        printFooter();
    }

    //------------------------------------------
    // Generates a report of ONLY sold artworks
    //------------------------------------------
    public static void generateSoldReport(Artwork[] artworks, int count) {
        printHeader("SOLD ARTWORKS REPORT");

        int soldCount  = 0;
        double revenue = 0;

        System.out.printf("  %-5s %-30s %-16s %s%n",
            "ID", "Title", "Type", "Price (Rs)");
        System.out.println("  " + "-".repeat(60));

        for (int i = 0; i < count; i++) {
            if (artworks[i].isSold()) {
                System.out.printf("  %-5d %-30s %-16s %.2f%n",
                    artworks[i].getArtworkId(),
                    artworks[i].getTitle(),
                    artworks[i].getType(),
                    artworks[i].getPrice());
                soldCount++;
                revenue += artworks[i].getPrice();
            }
        }

        System.out.println("  " + "-".repeat(60));

        //ternary operator: if nothing sold show a message
        if (soldCount == 0) {
            System.out.println("  No artworks have been sold yet.");
        } else {
            System.out.println("  Total Sold   : " + soldCount);
            System.out.printf("  Total Revenue: Rs. %.2f%n", revenue);
        }

        printFooter();
    }

    //------------------------------------------------
    //generates a report broken down by artwork type
    //uses instanceof to identify type 
    // -----------------------------------------------
    public static void generateTypeReport(Artwork[] artworks, int count) {
        printHeader("ARTWORKS BY TYPE REPORT");

        int paintingCount = 0, sculptureCount = 0, signedCount = 0;
        double paintingVal = 0, sculptureVal = 0, signedVal = 0;

        //count and total value per type using instanceof
        for (int i = 0; i < count; i++) {
            if (artworks[i] instanceof SignedArtwork) {
                signedCount++;
                signedVal += artworks[i].getPrice();
            } else if (artworks[i] instanceof Painting) {
                paintingCount++;
                paintingVal += artworks[i].getPrice();
            } else if (artworks[i] instanceof Sculpture) {
                sculptureCount++;
                sculptureVal += artworks[i].getPrice();
            }
        }

        System.out.printf("  %-18s %-10s %s%n", "Type", "Count", "Total Value (Rs)");
        System.out.println("  " + "-".repeat(45));
        System.out.printf("  %-18s %-10d %.2f%n", "Paintings",       paintingCount, paintingVal);
        System.out.printf("  %-18s %-10d %.2f%n", "Signed Artworks", signedCount,   signedVal);
        System.out.printf("  %-18s %-10d %.2f%n", "Sculptures",      sculptureCount,sculptureVal);
        System.out.println("  " + "-".repeat(45));
        System.out.printf("  %-18s %-10d %.2f%n", "TOTAL",
            paintingCount + signedCount + sculptureCount,
            paintingVal + signedVal + sculptureVal);

        printFooter();
    }

    //-------------------------------------------------------
    //METHOD OVERLOADING: generateReport() with no params
    //shows a quick one-liner summary (different from full report)
    //-------------------------------------------------------
    public static void generateReport(Artwork[] artworks, int count) {
        generateReport(artworks, count, true); //calls overloaded version
    }

    //Overloaded: generateReport() with showPrices flag
    public static void generateReport(Artwork[] artworks, int count, boolean showPrices) {
        printHeader("QUICK SUMMARY REPORT");

        for (int i = 0; i < count; i++) {
            if (showPrices) {
                System.out.printf("  [%d] %s | %s | Rs.%.2f | %s%n",
                    artworks[i].getArtworkId(),
                    artworks[i].getTitle(),
                    artworks[i].getType(),
                    artworks[i].getPrice(),
                    artworks[i].isSold() ? "SOLD" : "Available");
            } else {
                System.out.printf("  [%d] %s | %s | %s%n",
                    artworks[i].getArtworkId(),
                    artworks[i].getTitle(),
                    artworks[i].getType(),
                    artworks[i].isSold() ? "SOLD" : "Available");
            }
        }

        printFooter();
    }

    //------------------------------------------
    //PRIVATE HELPER METHODS
    //private = only usable inside this class
    //------------------------------------------
    private static void printHeader(String title) {
        System.out.println("\n  " + "=".repeat(60));
        System.out.println("  " + title);
        System.out.println("  " + "=".repeat(60));
    }

    private static void printFooter() {
        System.out.println("  " + "=".repeat(60));
        System.out.println("  [End of Report]");
    }
}
