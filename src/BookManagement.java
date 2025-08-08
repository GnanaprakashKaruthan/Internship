

import java.util.Scanner;

import com.library.Book;
import com.library.Library;
import com.library.Person;

public class BookManagement {
    public static void main(String[] args) {
        Library library = new Library();

        // Seed data
        library.addItem(new Book("1984", "George Orwell"));
        library.addItem(new Book("The Alchemist", "Paulo Coelho"));
        library.registerMember("Alice", "Student");
        library.registerMember("Bob", "Teacher");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Show all items");
            System.out.println("2. Show all members");
            System.out.println("3. Issue an item");
            System.out.println("4. Return an item");
            System.out.println("5. Register the User");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    library.showAllItems();
                    break;

                case 2:
                    library.showAllMembers();
                    break;

                case 3:
                    System.out.print("Enter member name: ");
                    String issuer = scanner.nextLine().trim();
                    Person person = library.findMember(issuer);
                    if(person == null) {
                    	System.out.println("Please register the user before issuing an item.");
                    	break;
                    }
                    library.listAvailableItems();

                    System.out.print("Select item number to issue: ");
                    int issueIdx = Integer.parseInt(scanner.nextLine());
                    library.issueItem(issueIdx, issuer);
                    break;

                case 4:
                    System.out.print("Enter member name: ");
                    String returner = scanner.nextLine().trim();

                    int borrowedCount = library.listBorrowedItems(returner);
                    if (borrowedCount > 0) {
                        System.out.print("Select item number to return: ");
                        int returnIdx = Integer.parseInt(scanner.nextLine());
                        library.returnItem(returnIdx, returner);
                    }
                    break;
                    
                case 5:
                	System.out.print("Enter the user name: ");
                    String userName = scanner.nextLine().trim();
                    // 1. Prevent duplicate registrations
                    if (library.findMember(userName) != null) {
                        System.out.printf("Registration failed: \"%s\" is already registered.%n", userName);
                        break;
                    }

                    // 2. Normalize role and create appropriate Person
                    System.out.print("Enter the user role: ");
                    String role = scanner.nextLine().trim();

                    library.registerMember(userName, role);
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
