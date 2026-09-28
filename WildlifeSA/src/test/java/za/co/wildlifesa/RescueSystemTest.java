package za.co.wildlifesa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RescueSystemTest {

    // COST CALCULATIONS

    @Test
    public void testInjuredRescueCostWithSurgery() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR101", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        // (5 * 500) + 3000 + 5000
        assertEquals(10500.0, rescue.calculateTotalCost(), 0.01);
    }

    @Test
    public void testInjuredRescueCostWithoutSurgery() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR102", "Leo", "Lion", "Kruger",
                "Thabo", 5, 500, "Minor wound", 3000, false);

        // (5 * 500) + 3000
        assertEquals(5500.0, rescue.calculateTotalCost(), 0.01);
    }

    @Test
    public void testOrphanedRescueCostWithFosterCare() {
        OrphanedAnimalRescue rescue = new OrphanedAnimalRescue(
                "WR103", "Bongi", "Rhino", "Kruger",
                "Lerato", 10, 400, 2, 1500, true);

        // (10 * 400) + 1500 + 2500
        assertEquals(8000.0, rescue.calculateTotalCost(), 0.01);
    }

    @Test
    public void testOrphanedRescueCostWithoutFosterCare() {
        OrphanedAnimalRescue rescue = new OrphanedAnimalRescue(
                "WR104", "Bongi", "Rhino", "Kruger",
                "Lerato", 10, 400, 8, 1500, false);

        // (10 * 400) + 1500
        assertEquals(5500.0, rescue.calculateTotalCost(), 0.01);
    }

    @Test
    public void testEndangeredRescueCostWithSpecialistTeam() {
        EndangeredSpeciesRescue rescue = new EndangeredSpeciesRescue(
                "WR105", "Themba", "Rhino", "Kruger",
                "Sipho", 5, 600, "Endangered", 4000, true);

        // (5 * 600) + 4000 + 8000
        assertEquals(15000.0, rescue.calculateTotalCost(), 0.01);
    }

    @Test
    public void testEndangeredRescueCostWithoutSpecialistTeam() {
        EndangeredSpeciesRescue rescue = new EndangeredSpeciesRescue(
                "WR106", "Themba", "Rhino", "Kruger",
                "Sipho", 5, 600, "Endangered", 4000, false);

        // (5 * 600) + 4000
        assertEquals(7000.0, rescue.calculateTotalCost(), 0.01);
    }

    // PRIORITY RULES

    @Test
    public void testSurgeryGivesCriticalPriority() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR107", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        assertEquals("Critical", rescue.determinePriority());
    }

    @Test
    public void testVetCostAtThresholdGivesHighPriority() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR108", "Leo", "Lion", "Kruger",
                "Thabo", 5, 500, "Infection", 10000, false);

        assertEquals("High", rescue.determinePriority());
    }

    @Test
    public void testVetCostBelowThresholdGivesMediumPriority() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR109", "Leo", "Lion", "Kruger",
                "Thabo", 5, 500, "Infection", 9999, false);

        assertEquals("Medium", rescue.determinePriority());
    }

    @Test
    public void testOrphanAgePriorityBoundaries() {
        OrphanedAnimalRescue threeMonths = new OrphanedAnimalRescue(
                "WR110", "A", "Rhino", "Kruger",
                "Lerato", 10, 400, 3, 1500, true);

        OrphanedAnimalRescue fourMonths = new OrphanedAnimalRescue(
                "WR111", "B", "Rhino", "Kruger",
                "Lerato", 10, 400, 4, 1500, true);

        OrphanedAnimalRescue twelveMonths = new OrphanedAnimalRescue(
                "WR112", "C", "Rhino", "Kruger",
                "Lerato", 10, 400, 12, 1500, false);

        OrphanedAnimalRescue thirteenMonths = new OrphanedAnimalRescue(
                "WR113", "D", "Rhino", "Kruger",
                "Lerato", 10, 400, 13, 1500, false);

        assertEquals("High", threeMonths.determinePriority());
        assertEquals("Medium", fourMonths.determinePriority());
        assertEquals("Medium", twelveMonths.determinePriority());
        assertEquals("Low", thirteenMonths.determinePriority());
    }

    @Test
    public void testSpecialistTeamGivesCriticalPriority() {
        EndangeredSpeciesRescue rescue = new EndangeredSpeciesRescue(
                "WR114", "Themba", "Rhino", "Kruger",
                "Sipho", 5, 600, "Endangered", 4000, true);

        assertEquals("Critical", rescue.determinePriority());
    }

    @Test
    public void testCriticalClassificationGivesCriticalPriority() {
        EndangeredSpeciesRescue rescue = new EndangeredSpeciesRescue(
                "WR115", "Themba", "Rhino", "Kruger",
                "Sipho", 5, 600, "critically endangered", 4000, false);

        assertEquals("Critical", rescue.determinePriority());
    }

    @Test
    public void testOtherEndangeredRescueGivesHighPriority() {
        EndangeredSpeciesRescue rescue = new EndangeredSpeciesRescue(
                "WR116", "Themba", "Rhino", "Kruger",
                "Sipho", 5, 600, "Endangered", 4000, false);

        assertEquals("High", rescue.determinePriority());
    }

    // RESCUE STATUS

    @Test
    public void testRescueStatusLifecycle() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR117", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        assertEquals("Reported", rescue.getStatus());

        rescue.startRescue();
        assertEquals("In Progress", rescue.getStatus());

        rescue.completeRescue();
        assertEquals("Completed", rescue.getStatus());
    }

    @Test
    public void testCannotCompleteBeforeStarting() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR118", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        rescue.completeRescue();

        assertEquals("Reported", rescue.getStatus());
    }

    @Test
    public void testCannotStartCompletedRescue() {
        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR119", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        rescue.startRescue();
        rescue.completeRescue();
        rescue.startRescue();

        assertEquals("Completed", rescue.getStatus());
    }

    @Test
    public void testManagerUpdatesStatus() {
        RescueManager manager = new RescueManager();

        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR120", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        manager.addCase(rescue);

        assertTrue(manager.updateStatus("WR120", "In Progress"));
        assertEquals("In Progress", rescue.getStatus());
    }

    @Test
    public void testManagerRejectsInvalidStatus() {
        RescueManager manager = new RescueManager();

        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR121", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        manager.addCase(rescue);

        assertFalse(manager.updateStatus("WR121", "Unknown"));
        assertEquals("Reported", rescue.getStatus());
    }

    // SEARCHING AND DUPLICATE IDS

    @Test
    public void testSearchFindsExistingCase() {
        RescueManager manager = new RescueManager();

        InjuredAnimalRescue rescue = new InjuredAnimalRescue(
                "WR122", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        manager.addCase(rescue);

        assertSame(rescue, manager.findById("WR122"));
        assertSame(rescue, manager.findById(" wr122 "));
    }

    @Test
    public void testSearchForMissingCaseReturnsNull() {
        RescueManager manager = new RescueManager();

        assertNull(manager.findById("MISSING"));
    }

    @Test
    public void testDuplicateIdsAreRejected() {
        RescueManager manager = new RescueManager();

        InjuredAnimalRescue first = new InjuredAnimalRescue(
                "WR123", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true);

        OrphanedAnimalRescue duplicate = new OrphanedAnimalRescue(
                " wr123 ", "Bongi", "Rhino", "Kruger",
                "Lerato", 10, 400, 2, 1500, true);

        assertTrue(manager.addCase(first));
        assertFalse(manager.addCase(duplicate));
        assertEquals(1, manager.getCaseCount());
        assertSame(first, manager.findById("WR123"));
    }

    // TOTALS ACROSS DIFFERENT RESCUE TYPES

    @Test
    public void testTotalCostForMixedRescueTypes() {
        RescueManager manager = new RescueManager();

        manager.addCase(new InjuredAnimalRescue(
                "WR124", "Nala", "Lion", "Kruger",
                "Thabo", 5, 500, "Injured leg", 3000, true));

        manager.addCase(new OrphanedAnimalRescue(
                "WR125", "Bongi", "Rhino", "Kruger",
                "Lerato", 10, 400, 2, 1500, true));

        manager.addCase(new EndangeredSpeciesRescue(
                "WR126", "Themba", "Rhino", "Kruger",
                "Sipho", 5, 600, "Endangered", 4000, true));

        // 10500 + 8000 + 15000
        assertEquals(3, manager.getCaseCount());
        assertEquals(33500.0, manager.getTotalEstimatedCost(), 0.01);
    }
}