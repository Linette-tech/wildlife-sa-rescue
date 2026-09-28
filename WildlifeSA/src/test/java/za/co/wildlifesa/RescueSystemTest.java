package za.co.wildlifesa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RescueSystemTest {

    @Test
    public void testInjuredRescueCostWithSurgery() {
        // Arrange: create a rescue with known costs.
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR101", "Nala", "Lion", "Kruger National Park",
                "Thabo", 5, 500,
                "Injured leg", 3000, true);

        // Act: calculate the total.
        double actualCost = rescue.calculateTotalCost();

        // Assert: check the result against our calculation.
        // (5 * 500) + 3000 + 5000 = 10500
        assertEquals(10500.0, actualCost, 0.01);
    }
}