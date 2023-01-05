package Backend.Services.Interfaces;

/**
 * Simple service to test DI, Tests and stuff
 */
public interface ITestService {
    /**
     * Increment by 1
     * @return new incremented value
     */
    int increment();

    /**
     * get current value
     * @return value
     */
    int getValue();

    /**
     * Decrement by 1
     * @return new decremented value
     */
    int decrement();
}
