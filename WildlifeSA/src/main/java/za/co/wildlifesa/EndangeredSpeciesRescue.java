package za.co.wildlifesa;

public class EndangeredSpeciesRescue extends RescueCase {

    private String classification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String caseId, String animalName,
            String species, String location, String ranger,
            int rescueDays, double dailyCareCost,
            String classification, double securityCost,
            boolean specialistTeamRequired) {

        super(caseId, animalName, species, location, ranger,
                rescueDays, dailyCareCost);

        this.classification = classification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public String getRescueType() {
        return "Endangered Species Rescue";
    }

    @Override
    public double calculateTotalCost() {
        double totalCost = calculateBaseCost() + securityCost;

        if (specialistTeamRequired) {
            totalCost = totalCost + 8000;
        }

        return totalCost;
    }

    // Design choice: specialist support or critical classification
    // gives the rescue Critical priority.
    @Override
    public String determinePriority() {
        if (specialistTeamRequired) {
            return "Critical";
        } else if ("Critically Endangered".equalsIgnoreCase(classification)) {
            return "Critical";
        } else {
            return "High";
        }
    }

    @Override
    public String getSpecificDetails() {
        String specialistAnswer;

        if (specialistTeamRequired) {
            specialistAnswer = "Yes";
        } else {
            specialistAnswer = "No";
        }

        return "Conservation Classification: " + classification
                + "\nSecurity Cost: R"
                + String.format("%.2f", securityCost)
                + "\nSpecialist Team Required: " + specialistAnswer;
    }
}