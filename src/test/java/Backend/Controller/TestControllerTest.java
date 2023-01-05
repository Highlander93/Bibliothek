package Backend.Controller;

import Backend.Services.Interfaces.ITestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import static org.mockito.Mockito.when;

/**
 * UnitTest for TestController to show basic controller tests.
 * See f.e. https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/ for setup of this (Point 3)
 */
@ExtendWith(MockitoExtension.class)
public class TestControllerTest {

    @InjectMocks
    TestController testController;

    @Mock
    ITestService testService;

    @Test
    public void getIndex() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var response = testController.index();
        assertThat(response.equals("Greetings from Spring Boot!"));
    }

    @Test
    public void getHelloDefault() {
        var response = testController.hello("");
        assertThat(response.equals("Hello World!"));
    }

    @Test
    public void getHelloParameter() {
        var response = testController.hello("test");
        assertThat(response.equals("Hello test!"));
    }

    @Test
    public void incrementCall() {
        when(testService.increment()).thenReturn(10);
        var response = testController.increment();
        assertThat(response == 10);
    }


    @Test
    public void decrementCall() {
        when(testService.getValue()).thenReturn(10);
        when(testService.decrement()).thenReturn(9);
        var response = testController.decrement();
        assertThat(response == 9);
    }


    @Test
    public void decrementCallTooLowValueReturns400() {
        when(testService.getValue()).thenReturn(0);
        var excetpionThrown = false;
        try {
            var response = testController.decrement();
        }
        catch(Exception e)
        {
            excetpionThrown = true;
            assertThat(e.getMessage().toLowerCase().contains("too low"));
            assertThat(e.getMessage().toLowerCase().contains("0"));
        }
        if (!excetpionThrown)
        {
            fail("No exception thrown even tho value is 0!");
        }
    }



}