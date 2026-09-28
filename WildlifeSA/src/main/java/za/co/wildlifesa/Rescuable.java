package za.co.wildlifesa;

// Defines the operations that all rescue cases must support.
public interface Rescuable {

    void startRescue();

    void completeRescue();

    String generateSummary();
}