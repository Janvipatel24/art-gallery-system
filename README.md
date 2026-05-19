# 🎨 Art Gallery Management System

A comprehensive Java application demonstrating Object-Oriented Programming (OOP) concepts and design patterns.

## 📋 Overview

The **Art Gallery Management System** is a menu-driven Java application that simulates the operations of a real-world art gallery. The system allows gallery staff to manage artworks (Paintings, Sculptures, Signed Artworks), customers, and purchases through an interactive text-based interface.

This project is built as an academic assignment for the course **"Principles of Programming Languages"** (Jan-May 2026) and covers all fundamental OOP concepts from Units 1 & 2.

---

## ✨ Features

### Artwork Management
- ✅ Add new artworks (Paintings, Sculptures, Signed/Certified Artworks)
- ✅ Display all artworks with full or brief details
- ✅ Search artwork by ID with type detection
- ✅ Filter artworks by availability or category
- ✅ View artwork-specific information

### Purchase System
- ✅ Complete purchase transactions
- ✅ Automatic sold status tracking
- ✅ Error handling for already-sold artworks
- ✅ Customer purchase history recording
- ✅ Custom exception handling

### Customer Management
- ✅ Register new customers with full details
- ✅ Track customer purchase history
- ✅ View customer information and spending
- ✅ Address aggregation for location tracking

### Gallery Reports
- ✅ Full gallery report (all artworks + customers)
- ✅ Sold artworks report with revenue totals
- ✅ Artworks breakdown by type
- ✅ Quick summary reports

### Statistics & Analytics
- ✅ Total artwork count and breakdown
- ✅ Gallery value calculations
- ✅ Sales revenue tracking
- ✅ Customer statistics

---

## 🏗️ Project Architecture

### 4-Package Design

```
artgallery/                          Main Package
├── GalleryManager.java              Entry point
│
├── models/                          Entity & Interface Classes
│   ├── Artwork.java                 Abstract base class
│   ├── Painting.java                Single inheritance demo
│   ├── SignedArtwork.java           Multilevel inheritance demo
│   ├── Sculpture.java               Hierarchical inheritance demo
│   ├── Customer.java                Aggregation pattern
│   ├── Address.java                 Aggregation helper
│   ├── GalleryPair.java             Generic class <K, V>
│   ├── Displayable.java             Interface 1
│   └── Purchasable.java             Interface 2
│
├── exceptions/                      Custom Exceptions
│   └── ArtworkException.java        Custom checked exception
│
└── reports/                         Report Generation
    └── GalleryReport.java           Report utilities
```

---

## 🎓 OOP Concepts Covered

### Unit 1: Java Fundamentals
- Data types (int, double, boolean, char, long, String)
- Variables (local, instance, static)
- Operators (arithmetic, relational, logical, ternary)
- Control structures (if-else, switch, for, while, do-while)
- Arrays and array of objects
- Input with Scanner class

### Unit 2: Object-Oriented Programming
- **Classes & Objects** - Entity design
- **Encapsulation** - Private fields + getters/setters
- **Inheritance** - Single, Hierarchical, Multilevel
- **Polymorphism** - Runtime method dispatch, method overloading, method overriding
- **Abstraction** - Abstract classes and methods
- **Interfaces** - Multiple inheritance, default methods
- **Exception Handling** - try-catch-finally, custom exceptions
- **Aggregation** - HAS-A relationship (Customer HAS-A Address)
- **Generics** - Generic class GalleryPair<K, V>
- **Static Members** - Static variables, methods, blocks
- **Access Modifiers** - public, private, protected, default
- **instanceof Operator** - Type checking and downcasting

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command Prompt / Terminal
- 5 minutes of your time

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YourUsername/art-gallery-system.git
   cd art-gallery-system
   ```

2. **Compile the project**
   ```bash
   javac artgallery/exceptions/*.java artgallery/models/*.java artgallery/reports/*.java artgallery/*.java
   ```

3. **Run the application**
   ```bash
   java artgallery.GalleryManager
   ```

### Your First Interaction

When you run the program, you'll see:
```
[System] Gallery initialized: The Grand Art Gallery
[System] Loading sample gallery data...
[System] Sample data loaded. 5 artworks, 2 customers.

Welcome to The Grand Art Gallery
Type a number and press Enter to navigate the menu.

+------------------------------------------+
|    ART GALLERY MANAGEMENT SYSTEM         |
|    The Grand Art Gallery                 |
+------------------------------------------+
|  1. Add New Artwork                       |
|  2. Display ALL Artworks (Full Details)   |
|  3. Display ALL Artworks (Brief List)     |
|  4. Search Artwork by ID                  |
|  5. Purchase Artwork                      |
|  6. Display Available Artworks Only       |
|  7. Display Artworks by Type              |
|  8. Gallery Statistics                    |
|  9. Add New Customer                      |
| 10. Display Customer Details              |
| 11. Generate Reports                      |
|  0. Exit                                  |
+------------------------------------------+
Enter your choice: 
```

Try option **3** to see a list of pre-loaded artworks!

---

## 📊 Sample Data

The system comes pre-loaded with:

**Artworks:**
- 2 Paintings (Ravi Verma's "Sunrise in India", Meera Shah's "Abstract Dreams")
- 1 Signed Artwork (Aryan Kapoor's "The Golden Hour" with certificate)
- 2 Sculptures (Arjun Pillai's "The Thinker", Priya Nair's "Goddess of Peace")

**Customers:**
- Amit Sharma from Mumbai
- Sunita Patel from Ahmedabad

---

## 💡 Usage Examples

### Adding a Painting
1. Select option **1**
2. Choose **1** for Painting
3. Enter details (ID, Title, Artist, Price, Year, Medium, Style, Dimensions)
4. New painting is added to the gallery

### Purchasing an Artwork
1. Select option **5**
2. Enter Artwork ID (e.g., 101)
3. Enter Customer ID (e.g., C001)
4. Purchase is processed with exception handling

### Viewing Statistics
1. Select option **8**
2. See total artworks, breakdown by type, revenue, and more

### Generating Reports
1. Select option **11**
2. Choose report type:
   - Full Report
   - Sold Artworks Report
   - By Type Report
   - Quick Summary

---

## 📁 File Structure

```
art-gallery-system/
├── README.md                  ← You are here
├── LICENSE                    ← MIT License
├── .gitignore                 ← Files to exclude from git
├── SETUP.md                   ← Setup instructions
├── DOCUMENTATION.md           ← Detailed documentation
│
└── artgallery/
    ├── GalleryManager.java
    ├── models/
    │   ├── Artwork.java
    │   ├── Painting.java
    │   ├── SignedArtwork.java
    │   ├── Sculpture.java
    │   ├── Customer.java
    │   ├── Address.java
    │   ├── Displayable.java
    │   ├── Purchasable.java
    │   └── GalleryPair.java
    ├── exceptions/
    │   └── ArtworkException.java
    └── reports/
        └── GalleryReport.java
```

---

## 🎯 Learning Outcomes

After studying this project, you will understand:

1. ✅ How to organize code into packages
2. ✅ Inheritance hierarchy and polymorphism
3. ✅ Abstract classes vs Interfaces
4. ✅ Exception handling in real applications
5. ✅ Generic classes and type parameters
6. ✅ Aggregation and composition
7. ✅ Encapsulation and access modifiers
8. ✅ Static members and blocks
9. ✅ Method overloading and overriding
10. ✅ Arrays of objects and runtime type checking

---

## 📚 Concepts Breakdown

### Inheritance Examples
```
Artwork (abstract base)
├── Painting (single inheritance)
│   └── SignedArtwork (multilevel inheritance)
└── Sculpture (hierarchical inheritance)
```

### Interfaces
- `Displayable` - Requires display() method
- `Purchasable` - Requires purchase() and calculateDiscountedPrice()

### Generic Class
```java
GalleryPair<K, V>  // Works with any types
GalleryPair<String, Integer>  // Example: <"Total Artworks", 5>
```

---

## 🐛 Troubleshooting

### Issue: `javac is not recognized`
**Solution:** Java is not installed or not in PATH. [Download Java](https://www.oracle.com/java/technologies/downloads/)

### Issue: `cannot find symbol`
**Solution:** Check that all files are in correct folders. Verify package names match folder structure.

### Issue: `ClassCastException`
**Solution:** Using `instanceof` before downcasting prevents this error. Already done in the code!

---

## 🤝 Contributing

This is an academic project. To contribute:
1. Fork the repository
2. Create a feature branch
3. Make improvements
4. Submit a pull request with clear descriptions

---

## 📄 License

This project is licensed under the **MIT License** - see [LICENSE](LICENSE) file for details.

The MIT License allows:
- ✅ Commercial use
- ✅ Modification
- ✅ Distribution
- ✅ Private use

Conditions:
- ⚠️ Must include license and copyright notice

---

## 👨‍💼 Author

**Janvi Patel**  
Student | Programmer | Java Enthusiast

- 📧 Email: janvipatel24071@gmail.com
- 🐙 GitHub: [@Janvipatel24](https://github.com/Janvipatel24)
- 💼 LinkedIn: [Janvi Patel](https://www.linkedin.com/in/janvi-patel-342519319/)

---

## 🎓 Course Information

- Course: Principles of Programming Languages (24IC208L)
- Institution: Pandit Deendayal Enery University
- Semester: 4 (Jan-May 2026)


---

## 📖 Documentation

- [Detailed Documentation](DOCUMENTATION.md) - Line-by-line code explanation
- [Setup Guide](SETUP.md) - Installation and compilation steps
- [Concept Reference](CONCEPTS.md) - OOP concepts with examples

---

## ⭐ If You Found This Helpful

Please consider giving this repository a **⭐ Star** on GitHub!

---

## 🙏 Acknowledgments

- Thanks to my Programming Languages course instructor
- Java documentation and community
- All developers who learned from open source projects

---

## 📞 Support

Have questions? Create an issue on GitHub or reach out directly.

---

**Last Updated:** April 2026  
**Status:** ✅ Complete & Production Ready
