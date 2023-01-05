package Backend.Services;

import Backend.Services.Implementations.TestService1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

/**
 * Example test class for service tests
 */
public class TestServiceTest {

    /**
     * Test behaviour for single call on increment
     */
    @Test
    public void incrementOneRun()
    {
        var testService = new TestService1();
        Assertions.assertEquals(1, testService.increment());
    }

    /**
     * Test behaviour for few consecutive increment calls
     */
    @Test
    public void incrementMultipleRuns()
    {
        var testService = new TestService1();
        var random = new Random();
        var max = random.nextInt(10) + 4;
        var current = 0;
        while (current++ < max-1)
        {
            testService.increment();
        }
        var lastCount = testService.increment();
        Assertions.assertEquals(max, lastCount);
    }

    /**
     * Test behaviour for first call on decrement
     */
    @Test
    public void decrementOneRunWithPreviousIncrement()
    {
        var testService = new TestService1();
        testService.increment();
        Assertions.assertEquals(0, testService.decrement());
    }


    /**
     * Test behaviour for first call on decrement
     */
    @Test
    public void decrementOneRunWithoutPreviousIncrement()
    {
        var testService = new TestService1();
        Assertions.assertEquals(-1, testService.decrement());
    }

    /**
     * Test behaviour for few consecutive decrement calls after previous increments
     */
    @Test
    public void decrementMultipleRunsWithPreviousIncrements()
    {
        var testService = new TestService1();
        // need to increment first
        testService.increment();
        testService.increment();
        // now decrement
        testService.decrement();
        var lastCount = testService.decrement();
        Assertions.assertEquals(0, lastCount);
    }



}
