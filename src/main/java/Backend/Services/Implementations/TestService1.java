package Backend.Services.Implementations;

import Backend.Services.Interfaces.ITestService;
import org.springframework.stereotype.Service;

@Service
public class TestService1 implements ITestService {

    private int counter = 0;

    @Override
    public void printMessage() {
        System.out.println("TestService1: " + counter++);
    }
}
