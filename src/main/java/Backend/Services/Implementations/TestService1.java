package Backend.Services.Implementations;

import Backend.Services.Interfaces.ITestService;
import org.springframework.stereotype.Service;

/**
 * Implementation of ITestService.
 * {@inheritDoc}
 */
@Service
public class TestService1 implements ITestService {

    private int counter = 0;

    @Override
    public int increment() {
        counter++;
        return counter;
    }

    @Override
    public int getValue() {
        return counter;
    }

    @Override
    public int decrement() {
        counter--;
        return counter;
    }
}
