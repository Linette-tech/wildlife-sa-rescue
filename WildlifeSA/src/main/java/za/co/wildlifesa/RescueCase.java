package za.co.wildlifesa;

// Stores the information shared by all rescue types.
public abstract class RescueCase implements Rescuable {

    private String caseId;
    private String animalName;
    private String species;
    private String location;
    private String ranger;
    private int rescueDays;
    private double dailyCareCost;
    private String status;

    // Constructor: sets the starting values for a rescue case.
    public RescueCase(String caseId, String animalName, String species,
            String location, String ranger, int rescueDays,
            double dailyCareCost) {

        if (caseId == null) {
    throw new IllegalArgumentException("Rescue Case ID cannot be blank.");
}

if (caseId.trim().isEmpty()) {
    throw new IllegalArgumentException("Rescue Case ID cannot be blank.");
}

this.caseId = caseId.trim();
        this.animalName = animalName;
        this.species = species;
        this.location = location;
        this.ranger = ranger;
        this.rescueDays = rescueDays;
        this.dailyCareCost = dailyCareCost;
        this.status = "Reported";
    }

    // Getters allow other classes to read the private fields.
    public String getCaseId() {
        return caseId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }

    public int getRescueDays() {
        return rescueDays;
    }

    public double getDailyCareCost() {
        return dailyCareCost;
    }

    public String getStatus() {
        return status;
    }

    // Only accept one of the three supported statuses.
    public void setStatus(String newStatus) {
        if ("Reported".equals(newStatus)
                || "In Progress".equals(newStatus)
                || "Completed".equals(newStatus)) {

            status = newStatus;
        } else {
            throw new IllegalArgumentException("Invalid rescue status.");
        }
    }

    // Calculates the care cost shared by all rescue types.
    public double calculateBaseCost() {
        return rescueDays * dailyCareCost;
    }

    // Each subclass must provide its own version of these methods.
    public abstract String getRescueType();

    public abstract double calculateTotalCost();

    public abstract String determinePriority();

    public abstract String getSpecificDetails();

    @Override
    public void startRescue() {
        if (status.equals("Reported")) {
            status = "In Progress";
        }
    }

    @Override
    public void completeRescue() {
        if (status.equals("In Progress")) {
            status = "Completed";
        }
    }

    @Override
    public String generateSummary() {
        return "Rescue Case ID: " + caseId
                + "\nRescue Type: " + getRescueType()
                + "\nSpecies: " + species
                + "\nAssigned Ranger: " + ranger
                + "\nPriority: " + determinePriority()
                + "\nStatus: " + status
                + "\nTotal Rescue Cost: R"
                + String.format("%.2f", calculateTotalCost());
    }

    // Includes all common fields and the details of the rescue type.
    public String displayDetails() {
        return generateSummary()
                + "\nAnimal Name: " + animalName
                + "\nRescue Location: " + location
                + "\nNumber of Rescue Days: " + rescueDays
                + "\nDaily Care Cost: R"
                + String.format("%.2f", dailyCareCost)
                + "\n" + getSpecificDetails();
    }
}
