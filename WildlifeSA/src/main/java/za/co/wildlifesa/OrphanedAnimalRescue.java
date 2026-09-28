package za.co.wildlifesa;

public class OrphanedAnimalRescue extends RescueCase {

    private int ageInMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String caseId, String animalName,
            String species, String location, String ranger,
            int rescueDays, double dailyCareCost,
            int ageInMonths, double feedingCost,
            boolean fosterCareRequired) {

        super(caseId, animalName, species, location, ranger,
                rescueDays, dailyCareCost);

        this.ageInMonths = ageInMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    @Override
    public String getRescueType() {
        return "Orphaned Animal Rescue";
    }

    @Override
    public double calculateTotalCost() {
        double totalCost = calculateBaseCost() + feedingCost;

        if (fosterCareRequired) {
            totalCost = totalCost + 2500;
        }

        return totalCost;
    }

    // Design choice: younger animals receive a higher priority.
    @Override
    public String determinePriority() {
        if (ageInMonths <= 3) {
            return "High";
        } else if (ageInMonths <= 12) {
            return "Medium";
        } else {
            return "Low";
        }
    }

    @Override
    public String getSpecificDetails() {
        String fosterCareAnswer;

        if (fosterCareRequired) {
            fosterCareAnswer = "Yes";
        } else {
            fosterCareAnswer = "No";
        }

        return "Estimated Age (Months): " + ageInMonths
                + "\nFeeding Cost: R"
                + String.format("%.2f", feedingCost)
                + "\nFoster Care Required: " + fosterCareAnswer;
    }
}