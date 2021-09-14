import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class DataSystem{

    private final Scanner scanner = new Scanner(System.in);

    private String[] firstName = new String[9999];
    private String[] familyName = new String[9999];
    private int[] age = new int[9999];
    private String[] gender = new String[9999];
    private String[] uniqueID = new String[9999];
    private int index;

    public DataSystem() {

    }
    //setter & getter
    public void setUniqueID(String uniqueID){
        this.uniqueID[index] = uniqueID;
    }
    public String getUniqueID(int index){
        return uniqueID[index];
    }
    public void setFirstName(String firstName){
        this.firstName[index] = firstName;
    }
    public String getFirstName(int index){
        return firstName[index];
    }
    public void setFamilyName(String familyName){
        this.familyName[index] = familyName;
    }
    public String getFamilyName(int index){
        return familyName[index];
    }
    public void setAge(int age){
        this.age[index] = age;
    }
    public int getAge(int index){
        return this.age[index];
    }
    public void setGender(String gender){
        this.gender[index] = gender;
        index++;
    }
    public String getGender(int index){
        return gender[index];
    }

    //Sorting selection
    public void sortSelection(int selectionChoice, String errorMessage){

        if (selectionChoice == 1) {
            sortStrings(firstName,familyName,age,gender,uniqueID,"\nFirst Name sorted alphabetically...\n");
        } else if (selectionChoice == 2) {
            if (Arrays.equals(familyName, familyName)) {
                sortStrings(familyName, firstName, age, gender, uniqueID, "");
                sortStrings(firstName, familyName, age, gender, uniqueID, "");
            }
            sortStrings(familyName,firstName,age,gender,uniqueID,"\nFamily Name sorted alphabetically...\n");
        } else if (selectionChoice == 3) {
            sortNumbers(firstName,familyName,age,gender,uniqueID);
        } else if (selectionChoice == 4) {
            sortStrings(gender,familyName,age,firstName,uniqueID,"\nList sorted by Gender...\n");
        } else if (selectionChoice == 5) {
            sortStrings(uniqueID,familyName,age,gender,firstName,"\nListed sorted by Unique ID...\n");
        } else {
            System.out.println(errorMessage);
        }

    }
    //When user search for specific data this method will refer which choice the user may choose.
    public void searchSelection(String searchChoice,int selectionChoice, String errorMessage){

        if (selectionChoice == 1) {
            searchSpecificData(uniqueID,searchChoice,"Unique ID not found...");
        } else if (selectionChoice == 2) {
            searchSpecificData(firstName,searchChoice,"First Name not found...");
        } else if (selectionChoice == 3) {
            searchSpecificData(familyName,searchChoice,"Family Name not found...");
        } else if (selectionChoice == 4){
            searchCategory(searchChoice);
        } else if (selectionChoice == 5) {
            searchSpecificData(gender,searchChoice,"Gender not found...");
        } else {
            System.out.println(errorMessage);
        }

    }
    //this method is to identify if age is young, adult or senior.
    public String categoryIdentifier(String ageValidate, int index, String errorMessage){

        if (age[index] < 18) {
            ageValidate = "YOUNG";
        } else if (age[index] > 18 && age[index] < 65) {
            ageValidate = "ADULT";
        } else if (age[index] >= 65) {
            ageValidate = "SENIOR";
        } else {
            System.out.println(errorMessage);
        }
        return ageValidate;
    }
    //Sort method for String variables or arrays.
    private void sortStrings(String[] parameterA, String[] parameterB, int[] age, String[] parameterC, String[] parameterD, String sortMessage){

        int i;
        int j;
        String temporary;
        int temporaryIndex;

        for (i = 0; i < index; i++){
            for (j = i+1; j < index; j++){
                if(parameterA[i].compareTo(parameterA[j]) > 0){
                    temporaryIndex = j;
                    temporary = parameterA[i];
                    parameterA[i]=parameterA[j];
                    parameterA[j]=temporary;

                    String tempFamilyName = parameterB[temporaryIndex];
                    parameterB[temporaryIndex] = parameterB[i];
                    parameterB[i] = tempFamilyName;

                    String tempGender = parameterC[temporaryIndex];
                    parameterC[temporaryIndex] = parameterC[i];
                    parameterC[i] = tempGender;

                    int tempAge = age[temporaryIndex];
                    age[temporaryIndex] = age[i];
                    age[i] = tempAge;

                    String tempUniqueID = parameterD[temporaryIndex];
                    parameterD[temporaryIndex] = parameterD[i];
                    parameterD[i] = tempUniqueID;

                }
            }
        }
        System.out.print(sortMessage);
    }
    //Sort for number variables or arrays.
    private void sortNumbers(String[] firstName, String[] familyName, int[] age, String[] gender, String[] uniqueID){

        int i;
        int j;
        int temporaryIndex;

        for (i = 0; i < index; i++) {
            temporaryIndex = i;
            for (j = i + 1; j < index; j++ ){
                if (age[j] > age[temporaryIndex]) {
                    temporaryIndex = j;

                    String tempFirstName = firstName[temporaryIndex];
                    firstName[temporaryIndex] = firstName[i];
                    firstName[i] = tempFirstName;

                    String tempFamilyName = familyName[temporaryIndex];
                    familyName[temporaryIndex] = familyName[i];
                    familyName[i] = tempFamilyName;

                    String tempGender = gender[temporaryIndex];
                    gender[temporaryIndex] = gender[i];
                    gender[i] = tempGender;

                    int tempAge = age[temporaryIndex];
                    age[temporaryIndex] = age[i];
                    age[i] = tempAge;

                    String tempUniqueID = uniqueID[temporaryIndex];
                    uniqueID[temporaryIndex] = uniqueID[i];
                    uniqueID[i] = tempUniqueID;
                }
            }
        }
        System.out.print("\nAge sorted in descending form...\n");
    }
    //this method handles the search method
    private void searchSpecificData(String[] parameterA, String parameterB, String errorMessage) {
        String categoryContainer = "";
        boolean correction = false;
        System.out.print("\nRESULTS: \n");
        ui();
        for (int i = 0; i < parameterA.length; i++) {
            if (Objects.equals(parameterA[i], parameterB)) {
                String category = categoryIdentifier(categoryContainer, i, "UNKNOWN");
                System.out.println(String.format("%-15s",this.uniqueID[i])+ String.format("%-20s",this.familyName[i]+",")+ String.format("%-25s",this.firstName[i])+ String.format("%-10s",this.age[i])+ String.format("%-10s",this.gender[i])+ String.format("%-12s",category));
                correction = true;
            }
        }
        if (!correction) {
            System.out.println(errorMessage);
        }
    }
    private void searchCategory(String ageValidate){
        String categoryContainer = "";
        int loop = 0;
        boolean correct = false;
        System.out.print("\nRESULTS: \n");
        ui();
        while (!correct){
            if (getAge(loop) != 0){
                if (ageValidate.equals("YOUNG")){
                    if(age[loop] < 18){
                        String category = categoryIdentifier(categoryContainer, loop, "UNKNOWN");
                        System.out.println(String.format("%-15s",this.uniqueID[loop])+ String.format("%-20s",this.familyName[loop]+",")+ String.format("%-25s",this.firstName[loop])+ String.format("%-10s",this.age[loop])+ String.format("%-10s",this.gender[loop])+ String.format("%-12s",category));
                    }
                } else if (ageValidate.equals("ADULT")){
                    if (age[loop] > 18 && age[loop] < 65){
                        String category = categoryIdentifier(categoryContainer, loop, "UNKNOWN");
                        System.out.println(String.format("%-15s",this.uniqueID[loop])+ String.format("%-20s",this.familyName[loop]+",")+ String.format("%-25s",this.firstName[loop])+ String.format("%-10s",this.age[loop])+ String.format("%-10s",this.gender[loop])+ String.format("%-12s",category));
                    }
                } else if (ageValidate.equals("SENIOR")){
                    if (age[loop] >= 65) {
                        String category = categoryIdentifier(categoryContainer, loop, "UNKNOWN");
                        System.out.println(String.format("%-15s",this.uniqueID[loop])+ String.format("%-20s",this.familyName[loop]+",")+ String.format("%-25s",this.firstName[loop])+ String.format("%-10s",this.age[loop])+ String.format("%-10s",this.gender[loop])+ String.format("%-12s",category));
                    }
                } else {
                    System.out.println("Category not Found...");
                }
                loop++;
            } else {
                correct = true;
            }
        }
    }
    public void ui(){
        System.out.println(String.format("\n%-15s","----------")+ String.format("%-20s","-----------")+ String.format("%-25s","------------")+ String.format("%-10s","---")+ String.format("%-10s","-----")+ String.format("%-12s","--------"));
        System.out.println(String.format("%-15s","Unique ID")+ String.format("%-20s","Family Name")+ String.format("%-25s","First Name")+ String.format("%-10s","age")+ String.format("%-10s","Gender")+ String.format("%-12s","CATEGORY"));
        System.out.println(String.format("%-15s","----------")+ String.format("%-20s","-----------")+ String.format("%-25s","------------")+ String.format("%-10s","---")+ String.format("%-10s","-----")+ String.format("%-12s","--------"));
    }
    public void duplicateCheckerForID(){
        setUniqueID(scanner.nextLine().trim().toUpperCase());
        while (true) {
            if (checkDuplicate(uniqueID, uniqueID[index], index) || Objects.equals(uniqueID[index], "")) {
                System.out.println("ID already exist or blank try again...");
                uniqueID[index] = "";
                System.out.print("Enter Unique ID: ");
                uniqueID[index] = scanner.nextLine().trim().toUpperCase();
            } else {
                break;
            }
        }
    }
    public boolean checkDuplicate (String [] product, String productName, int container) {
        for (int i=0; i < container; i++) {
            if (productName.equals(product[i]))
                return true;
        }
        return false;
    }
}