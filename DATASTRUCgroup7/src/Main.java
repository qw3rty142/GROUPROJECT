import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    final static DataSystem dataSystem = new DataSystem();

    public static void main (String [] args) {
        int counter = 0;
        menu(counter);
    }

    public static void menu(int counter){
        short menuChoice = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                do {
                    System.out.println("\n[ (1) Add Member ]");
                    System.out.println("[ (2) Display All ]");
                    System.out.println("[ (3) Sort ]");
                    System.out.println("[ (4) Search Specific Data ]");
                    System.out.println("[ (0) Exit ]");
                    System.out.print("Select: ");
                    menuChoice = scanner.nextShort();
                    scanner.nextLine();
                    if (menuChoice == 1){
                        addMember();
                    } else if (menuChoice == 2){
                        displayAll(counter);
                    } else if (menuChoice == 3){
                        System.out.println("\nSelect (1) to sort FIRST NAMES\nSelect (2) to sort FAMILY NAMES\nSelect (3) to sort AGE\nSelect (4) to sort GENDER\nSelect (5) to sort UNIQUE ID");
                        System.out.print("Select: ");
                        short sortChoice = Short.parseShort(scanner.nextLine());
                        dataSystem.sortSelection(sortChoice, "Selection not found...");
                    } else if (menuChoice == 4){
                        System.out.println("\nSelect (1) to search a UNIQUE ID\nSelect (2) to search a FIRST NAME\nSelect (3) to search a FAMILY NAME\nSelect (4) to list specific CATEGORY\nSelect (5) to list specific GENDERS");
                        System.out.print("Select: ");
                        short searchChoice = Short.parseShort(scanner.nextLine());
                        System.out.print("\nInput here to search: ");
                        dataSystem.searchSelection(scanner.nextLine().trim().toUpperCase(), searchChoice, "Selection not found...");
                    } else if (menuChoice == 0){
                        System.out.println("Program Terminated...");
                    } else {
                        System.out.println("Selection not found...");
                    }
                } while (menuChoice != 0);
                validInput = true;
            } catch (InputMismatchException e){
                scanner.next();
            }
        }
    }
    public static void addMember(){
        boolean validInput = false;

        System.out.print("\nEnter Unique ID: ");
        dataSystem.duplicateCheckerForID();
        System.out.print("Enter First Name: ");
        dataSystem.setFirstName(scanner.nextLine().trim().toUpperCase());
        System.out.print("Enter Family Name: ");
        dataSystem.setFamilyName(scanner.nextLine().trim().toUpperCase());
        System.out.print("Enter Age: ");
        while (!validInput) {
            try {
                dataSystem.setAge(scanner.nextInt());
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input try again...");
                System.out.print("Enter Age: ");
                scanner.next();
            }
        }
        validInput = false;
        System.out.print("Select Gender [ (1) MALE ] [ (2) FEMALE ]: ");
        while (!validInput) {
            try {
                int genderValidator = scanner.nextInt();
                scanner.nextLine();
                String genderOutput = genderSelect(genderValidator);
                dataSystem.setGender(genderOutput);
                validInput = true;
            } catch (InputMismatchException e){
                System.out.println("Invalid input try again...");
                System.out.print("Select Gender [ (1) MALE ] [ (2) FEMALE ]: ");
                scanner.next();
            }
        }
    }
    public static void displayAll(int counter){
        String categoryContainer = "";
        boolean loop = false;
        dataSystem.ui();
        while (!loop) {
            if (dataSystem.getUniqueID(counter) != null){
                String category = dataSystem.categoryIdentifier(categoryContainer, counter, "UNKNOWN");
                System.out.println(String.format("%-15s",dataSystem.getUniqueID(counter))+ String.format("%-20s",dataSystem.getFamilyName(counter)+",")+ String.format("%-25s",dataSystem.getFirstName(counter))+ String.format("%-10s",dataSystem.getAge(counter))+ String.format("%-10s",dataSystem.getGender(counter))+String.format("%-12s",category));
                counter++;
            } else {
                loop = true;
            }
        }
    }
    public static String genderSelect(int select){
        String gender = "";
        if (select == 1) {
            gender = "MALE";
        } else if (select == 2) {
            gender = "FEMALE";
        } else {
            throw new InputMismatchException("Invalid Input...");
        }
        return gender;
    }
}
