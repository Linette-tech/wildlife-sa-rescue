package za.co.wildlifesa;

import java.util.Scanner;

public class WildlifeSA {

    private static Scanner input = new Scanner(System.in);
    private static RescueManager manager = new RescueManager();

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = readInt("Choose an option: ", 1, 9);

            switch (choice) {
                case 1:
                    createCase();
                    break;
                case 2:
                    searchCase();
                    break;
                case 3:
                    updateStatus();
                    break;
                case 4:
                    System.out.println(manager.displayAllCases());
                    break;
                case 5:
                    startRescue();
                    break;
                case 6:
                    completeRescue();
                    break;
                case 7:
                    displaySummary();
                    break;
                case 8:
                    System.out.println(manager.generateReport());
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    break;
            }

        } while (choice != 9);
    }

    private static void displayMenu() {
        System.out.println("\nWILDLIFE SA RESCUE OPERATIONS SYSTEM");
        System.out.println("1. Create rescue case");
        System.out.println("2. Search rescue case");
        System.out.println("3. Update rescue status");
        System.out.println("4. Display all rescue cases");
        System.out.println("5. Start rescue operation");
        System.out.println("6. Complete rescue operation");
        System.out.println("7. Generate rescue summary");
        System.out.println("8. Generate rescue report");
        System.out.println("9. Exit");
    }

    private static void createCase() {
        System.out.println("\nRESCUE TYPES");
        System.out.println("1. Injured animal");
        System.out.println("2. Orphaned animal");
        System.out.println("3. Endangered species");

        int type = readInt("Choose a rescue type: ", 1, 3);

        String caseId = readText("Rescue Case ID: ");

        while (manager.findById(caseId) != null) {
            System.out.println("That ID already exists.");
            caseId = readText("Enter a different Rescue Case ID: ");
        }

        System.out.print("Animal Name (leave blank if unknown): ");
        String animalName = input.nextLine().trim();

        if (animalName.isEmpty()) {
            animalName = "Unnamed";
        }

        String species = readText("Species: ");
        String location = readText("Rescue Location: ");
        String ranger = readText("Assigned Ranger: ");

        int days = readInt("Number of Rescue Days: ",
                1, Integer.MAX_VALUE);

        double dailyCost = readPositiveDouble("Daily Care Cost (R): ");

        RescueCase rescueCase;

        if (type == 1) {
            String injury = readText("Injury Description: ");
            double vetCost = readPositiveDouble(
                    "Veterinary Treatment Cost (R): ");
            boolean surgery = readYesNo("Surgery required?");

            rescueCase = new InjuredAnimalRescue(
                    caseId, animalName, species, location, ranger,
                    days, dailyCost, injury, vetCost, surgery);

        } else if (type == 2) {
            int age = readInt("Estimated Age in Months: ",
                    1, Integer.MAX_VALUE);

            double feedingCost = readPositiveDouble("Feeding Cost (R): ");
            boolean fosterCare = readYesNo("Foster care required?");

            rescueCase = new OrphanedAnimalRescue(
                    caseId, animalName, species, location, ranger,
                    days, dailyCost, age, feedingCost, fosterCare);

        } else {
            String classification = readText(
                    "Conservation Classification: ");

            double securityCost = readPositiveDouble("Security Cost (R): ");
            boolean specialist = readYesNo("Specialist team required?");

            rescueCase = new EndangeredSpeciesRescue(
                    caseId, animalName, species, location, ranger,
                    days, dailyCost, classification,
                    securityCost, specialist);
        }

        if (manager.addCase(rescueCase)) {
            System.out.println("Rescue case created successfully.");
        } else {
            System.out.println("The rescue case could not be added.");
        }
    }

    private static void searchCase() {
        String caseId = readText("Enter Rescue Case ID: ");
        RescueCase rescueCase = manager.findById(caseId);

        if (rescueCase == null) {
            System.out.println("No rescue case found with that ID.");
        } else {
            System.out.println(rescueCase.displayDetails());
        }
    }

    private static void updateStatus() {
        String caseId = readText("Enter Rescue Case ID: ");
        RescueCase rescueCase = manager.findById(caseId);

        if (rescueCase == null) {
            System.out.println("No rescue case found with that ID.");
            return;
        }

        System.out.println("Current status: " + rescueCase.getStatus());
        System.out.println("1. Reported");
        System.out.println("2. In Progress");
        System.out.println("3. Completed");

        int choice = readInt("Choose the new status: ", 1, 3);
        String newStatus;

        if (choice == 1) {
            newStatus = "Reported";
        } else if (choice == 2) {
            newStatus = "In Progress";
        } else {
            newStatus = "Completed";
        }

        if (manager.updateStatus(caseId, newStatus)) {
            System.out.println("Status updated to: " + newStatus);
        } else {
            System.out.println("The status could not be updated.");
        }
    }

    private static void startRescue() {
        String caseId = readText("Enter Rescue Case ID: ");
        RescueCase rescueCase = manager.findById(caseId);

        if (rescueCase == null) {
            System.out.println("No rescue case found with that ID.");
        } else if (rescueCase.getStatus().equals("Reported")) {
            rescueCase.startRescue();
            System.out.println("Rescue started. Status: "
                    + rescueCase.getStatus());
        } else {
            System.out.println("Only a reported rescue can be started.");
        }
    }

    private static void completeRescue() {
        String caseId = readText("Enter Rescue Case ID: ");
        RescueCase rescueCase = manager.findById(caseId);

        if (rescueCase == null) {
            System.out.println("No rescue case found with that ID.");
        } else if (rescueCase.getStatus().equals("In Progress")) {
            rescueCase.completeRescue();
            System.out.println("Rescue completed. Status: "
                    + rescueCase.getStatus());
        } else {
            System.out.println("Only an in-progress rescue can be completed.");
        }
    }

    private static void displaySummary() {
        String caseId = readText("Enter Rescue Case ID: ");
        RescueCase rescueCase = manager.findById(caseId);

        if (rescueCase == null) {
            System.out.println("No rescue case found with that ID.");
        } else {
            System.out.println(rescueCase.generateSummary());
        }
    }

    // Keeps asking until the user enters non-blank text.
    private static String readText(String prompt) {
        System.out.print(prompt);
        String text = input.nextLine().trim();

        while (text.isEmpty()) {
            System.out.println("This field cannot be blank.");
            System.out.print(prompt);
            text = input.nextLine().trim();
        }

        return text;
    }

    // Keeps asking until a whole number within the range is entered.
    private static int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);

            try {
                int number = Integer.parseInt(input.nextLine().trim());

                if (number >= minimum && number <= maximum) {
                    return number;
                }

                System.out.println("Enter a whole number from "
                        + minimum + " to " + maximum + ".");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    // Keeps asking until a finite number greater than zero is entered.
    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                double number = Double.parseDouble(input.nextLine().trim());

                if (number > 0 && Double.isFinite(number)) {
                    return number;
                }

                System.out.println("Enter a number greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number, such as 500.50.");
            }
        }
    }

    // Accepts Y or N, in either uppercase or lowercase.
    private static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String answer = input.nextLine().trim();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }
}