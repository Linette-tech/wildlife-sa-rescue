package za.co.wildlifesa;

public class InjuredAnimalRescue extends RescueCase {

    private String injuryDescription;
    private double veterinaryCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String caseId, String animalName,
            String species, String location, String ranger,
            int rescueDays, double dailyCareCost,
            String injuryDescription, double veterinaryCost,
            boolean surgeryRequired) {

        super(caseId, animalName, species, location, ranger,
                rescueDays, dailyCareCost);

        this.injuryDescription = injuryDescription;
        this.veterinaryCost = veterinaryCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public String getRescueType() {
        return "Injured Animal Rescue";
    }

    @Override
    public double calculateTotalCost() {
        double totalCost = calculateBaseCost() + veterinaryCost;

        if (surgeryRequired) {
            totalCost = totalCost + 5000;
        }

        return totalCost;
    }

    // Design choice: surgery is Critical; otherwise use treatment cost.
    @Override
    public String determinePriority() {
        if (surgeryRequired) {
            return "Critical";
        } else if (veterinaryCost >= 10000) {
            return "High";
        } else {
            return "Medium";
        }
    }

    @Override
    public String getSpecificDetails() {
        String surgeryAnswer;

        if (surgeryRequired) {
            surgeryAnswer = "Yes";
        } else {
            surgeryAnswer = "No";
        }

        return "Injury Description: " + injuryDescription
                + "\nVeterinary Treatment Cost: R"
                + String.format("%.2f", veterinaryCost)
                + "\nSurgery Required: " + surgeryAnswer;
    }
}
