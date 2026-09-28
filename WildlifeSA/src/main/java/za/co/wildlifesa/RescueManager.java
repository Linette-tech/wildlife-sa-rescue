package za.co.wildlifesa;

import java.util.ArrayList;

public class RescueManager {

    private ArrayList<RescueCase> rescueCases = new ArrayList<>();

    // Returns the matching case, or null if no case is found.
    public RescueCase findById(String caseId) {
        if (caseId == null) {
            return null;
        }

        for (RescueCase rescueCase : rescueCases) {
            if (rescueCase.getCaseId().equalsIgnoreCase(caseId.trim())) {
                return rescueCase;
            }
        }

        return null;
    }

    // Adds a case only if its ID is valid and unique.
    public boolean addCase(RescueCase rescueCase) {
        if (rescueCase == null) {
            return false;
        }

        String caseId = rescueCase.getCaseId();

        if (caseId == null || caseId.trim().isEmpty()) {
            return false;
        }

        if (findById(caseId) != null) {
            return false;
        }

        rescueCases.add(rescueCase);
        return true;
    }

    // Updates the status of an existing case.
    public boolean updateStatus(String caseId, String newStatus) {
        RescueCase rescueCase = findById(caseId);

        if (rescueCase == null) {
            return false;
        }

        if ("Reported".equals(newStatus)
                || "In Progress".equals(newStatus)
                || "Completed".equals(newStatus)) {

            rescueCase.setStatus(newStatus);
            return true;
        }

        return false;
    }

    public int getCaseCount() {
        return rescueCases.size();
    }

    public double getTotalEstimatedCost() {
        double total = 0;

        for (RescueCase rescueCase : rescueCases) {
            total = total + rescueCase.calculateTotalCost();
        }

        return total;
    }

    // Returns full details of every stored case.
    public String displayAllCases() {
        if (rescueCases.isEmpty()) {
            return "No rescue cases recorded.";
        }

        String details = "";

        for (RescueCase rescueCase : rescueCases) {
            details = details + rescueCase.displayDetails()
                    + "\n----------------------------------------\n";
        }

        return details;
    }

    // Returns a report containing the required fields and totals.
    public String generateReport() {
        String report = "WILDLIFE SA RESCUE REPORT\n";
        report = report + "========================================\n";

        if (rescueCases.isEmpty()) {
            report = report + "No rescue cases recorded.\n";
        }

        for (RescueCase rescueCase : rescueCases) {
            report = report
                    + "Rescue Case ID: " + rescueCase.getCaseId()
                    + "\nRescue Type: " + rescueCase.getRescueType()
                    + "\nSpecies: " + rescueCase.getSpecies()
                    + "\nRescue Location: " + rescueCase.getLocation()
                    + "\nAssigned Ranger: " + rescueCase.getRanger()
                    + "\nPriority: " + rescueCase.determinePriority()
                    + "\nStatus: " + rescueCase.getStatus()
                    + "\nTotal Rescue Cost: R"
                    + String.format("%.2f", rescueCase.calculateTotalCost())
                    + "\n----------------------------------------\n";
        }

        report = report
                + "Total Rescue Cases: " + getCaseCount()
                + "\nTotal Estimated Rescue Cost: R"
                + String.format("%.2f", getTotalEstimatedCost());

        return report;
    }
}