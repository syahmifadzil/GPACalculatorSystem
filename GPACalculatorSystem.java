/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dsaassignment2;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Scanner;

// Class representing a single Course object 
class Course {
    String name;
    int creditHour;
    double score;
    String grade;
    double point;

    public Course(String name, int creditHour, double score) {
        this.name = name;
        this.creditHour = creditHour;
        this.score = score;
        calculateGradeAndPoint();
    }

    // Algorithm to assign grades and points based on score 
    private void calculateGradeAndPoint() {
        if (score >= 85) { grade = "A"; point = 4.0; }
        else if (score >= 75) { grade = "A-"; point = 3.67; }
        else if (score >= 70) { grade = "B+"; point = 3.33; }
        else if (score >= 65) { grade = "B"; point = 3.0; }
        else if (score >= 60) { grade = "B-"; point = 2.67; }
        else if (score >= 55) { grade = "C"; point = 2.33; }
        else if (score >= 50) { grade = "C-"; point = 2.0; }
        else if (score >= 45) { grade = "D"; point = 1.67; }
        else if (score >= 40) { grade = "D-"; point = 1.33; }
        else if (score >= 35) { grade = "E"; point = 1.00; }
        else { grade = "F"; point = 0.0; }
    }
    
    // Update method to modify score and re-calculate grade automatically
    public void setScore(double newScore) {
        this.score = newScore;
        calculateGradeAndPoint();
    }
  public void displayCourse(int index) {
        System.out.printf("| %-3d | %-15s | %-6d | %-5.1f | %-4s | %-4.2f |\n", 
            (index + 1), name, creditHour, score, grade, point);
    }
}
public class GPACalculatorSystem {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("==========================================");
        System.out.println("   IIUM GPA/CGPA CALCULATOR PROTOTYPE     ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add Course (Insert)");
            System.out.println("2. Remove Course (Delete)");
            System.out.println("3. Update Score (Update)");
            System.out.println("4. Search Course (Search)");
            System.out.println("5. View All & Calculate GPA");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            // Validation to prevent crashing if user enters text instead of number
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline buffer

                switch (choice) {
                    case 1: addCourse(); break;
                    case 2: removeCourse(); break;
                    case 3: updateCourse(); break;
                    case 4: searchCourse(); break;
                    case 5: calculateAndDisplay(); break;
                    case 6: 
                        running = false;
                        System.out.println("Exiting system... Have a nice day!");
                        break;
                    default: System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
        }
    }

    // Feature: Add Course (Demonstrates Insertion)
    private static void addCourse() {
        System.out.print("Enter Subject Name : ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Credit Hours : ");
        int credit = scanner.nextInt();
        
        System.out.print("Enter Score (0-100): ");
        double score = scanner.nextDouble();

        Course newCourse = new Course(name, credit, score);
        courseList.add(newCourse); 
        System.out.println("Course added successfully!");
    }

    // Feature: Remove Course (Demonstrates Deletion)
    private static void removeCourse() {
        System.out.println("\n[ Remove Course ]");
        if (courseList.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        
        // Show list first so user knows what to delete
        printTable(); 
        
        System.out.print("Enter the ID/Number to remove: ");
        int index = scanner.nextInt() - 1;
        
        if (index >= 0 && index < courseList.size()) {
            Course removed = courseList.remove(index); // Removal from ArrayList
            System.out.println("Removed: " + removed.name);
        } else {
            System.out.println("Error: Invalid ID.");
        }
    }
    private static void updateCourse() {
        System.out.println("\n[ Update Course Score ]");
        if (courseList.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        printTable();
        System.out.print("Enter the ID/Number to update: ");
        int index = scanner.nextInt() - 1;
        
        if (index >= 0 && index < courseList.size()) {
            System.out.print("Enter NEW Score for " + courseList.get(index).name + ": ");
            double newScore = scanner.nextDouble();
            
            // Update the object and recalculate grade
            courseList.get(index).setScore(newScore); 
            System.out.println("Success: Score updated and Grade recalculated.");
        } else {
            System.out.println("Error: Invalid ID.");
        }
    }
    private static void searchCourse() {
        System.out.println("\n[ Search Course ]");
        System.out.print("Enter Subject Name to Search: ");
        String keyword = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        System.out.println("\n--- Search Results ---");
        
        // Linear Search Algorithm
        for (int i = 0; i < courseList.size(); i++) {
            Course c = courseList.get(i);
            if (c.name.toLowerCase().contains(keyword)) {
                c.displayCourse(i);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No subject found matching: " + keyword);
        }
    }

    // Feature: Calculate GPA (Demonstrates Traversal/Access) 
    private static void calculateAndDisplay() {
       if (courseList.isEmpty()) {
            System.out.println("No data to calculate.");
            return;
        }

        System.out.println("\n--- Academic Summary ---");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("| %-3s | %-15s | %-6s | %-5s | %-4s | %-4s |\n", "ID", "Subject", "Credit", "Score", "Grd", "Pnt");
        System.out.println("-----------------------------------------------------------");

        double totalPoints = 0;
        double totalCredits = 0;

        // Loop through ArrayList (Traversal)
        for (int i = 0; i < courseList.size(); i++) {
            Course c = courseList.get(i);
            c.displayCourse(i);
            
            totalPoints += (c.point * c.creditHour);
            totalCredits += c.creditHour;
        }
        System.out.println("-----------------------------------------------------------");

        if (totalCredits > 0) {
            double gpa = totalPoints / totalCredits;
            System.out.printf("Total Credits : %.0f\n", totalCredits);
            System.out.printf("Final GPA     : %.2f\n", gpa);
            
            // Simple status check
            if (gpa >= 3.5) System.out.println("Status        : Dean's List ");
            else if (gpa >= 2.0) System.out.println("Status        : Pass");
            else System.out.println("Status        : Probation Warning");
        }
    }
        private static void printTable() {
        System.out.println("---------------------------------");
        System.out.printf("| %-3s | %-15s | %-5s |\n", "ID", "Subject", "Score");
        System.out.println("---------------------------------");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("| %-3d | %-15s | %-5.1f |\n", (i+1), courseList.get(i).name, courseList.get(i).score);
        }
        System.out.println("---------------------------------");
    }
}

