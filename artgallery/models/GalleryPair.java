package artgallery.models;   // CHANGED: was 'package artgallery'

// CONCEPT: Generic Class 
// <K, V> are type parameters - like placeholders for any data type.
// This class works with ANY two types: String+Integer, String+Double, etc.
// Without generics, we'd need a separate class for every type combination.

public class GalleryPair<K, V> {

    //K and V are type parameters (decided at object creation time)
    private K key;    // e.g., could be String (like "Total Artworks")
    private V value;  // e.g., could be Integer (like 10)

    //constructor
    public GalleryPair(K key, V value) {
        this.key   = key;
        this.value = value;
    }

    //Getter methods
    public K getKey()   { return key;   }
    public V getValue() { return value; }

    //Setter methods
    public void setKey(K key)     { this.key = key;     }
    public void setValue(V value) { this.value = value; }

    //display the pair nicely formatted
    public void display() {
        System.out.printf("  %-25s: %s%n", key, value);
    }
}
