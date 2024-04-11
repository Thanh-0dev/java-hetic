package fr.hetic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MainTest {
    private final OperationStrategy addition = OperationStrategyFactory.getStrategy("+");
    private final OperationStrategy subtraction = OperationStrategyFactory.getStrategy("-");
    private final OperationStrategy multiplication = OperationStrategyFactory.getStrategy("*");
    private final OperationStrategy division = OperationStrategyFactory.getStrategy("/");

    @Test
    public void getStrategy() {
        OperationStrategy strategy = OperationStrategyFactory.getStrategy("+");
        assertNotNull(strategy);
        assertInstanceOf(AdditionStrategy.class, strategy);
    }

    @Test
    public void testAddition() {
        assertNotNull(addition);
        assertEquals("3", addition.execute(1, 2));
    }

    @Test
    public void testSubtraction() {
        assertNotNull(subtraction);
        assertEquals("-1", subtraction.execute(1, 2));
    }

    @Test
    public void testMultiplication() {
        assertNotNull(multiplication);
        assertEquals("2", multiplication.execute(1, 2));
    }

    @Test
    public void testDivision() {
        assertNull(division);
    }
}
