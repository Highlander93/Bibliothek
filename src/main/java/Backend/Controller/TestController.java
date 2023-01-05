package Backend.Controller;

import Backend.Services.Interfaces.ITestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * TestController to show basic controller layout.
 * Sources:
 * https://www.baeldung.com/spring-response-entity
 * https://stackoverflow.com/questions/16232833/how-to-respond-with-an-http-400-error-in-a-spring-mvc-responsebody-method-retur
 *
 */
@RestController
public class TestController {

    @Autowired
    private ITestService _testService;

    @Operation(
            summary = "Get Index",
            description = "Return greeting message",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @GetMapping("/")
    public String index()
    {
        return "Greetings from Spring Boot!";
    }

    @Operation(
            summary = "Greet user",
            description = "Greet user, if parameter set by name",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return String.format("Hello %s!", name);
    }

    @Operation(
            summary = "Increment counter",
            description = "Increment counter by 1 and return value",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))
                    )
            }
    )
    @GetMapping("/increment")
    public int increment()
    {
       var incrementedCounter = _testService.increment();
       return incrementedCounter;
    }


    @GetMapping("/decrement")
    @Operation(
            summary = "Decrement counter",
            description = "Decrement counter by 1 and return value",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))
                    ),
                    @ApiResponse(description = "Value too low", responseCode = "400", content = @Content)
            }
    )
    public Integer decrement()
    {
        var currentValue = _testService.getValue();
        if (currentValue <= 0)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value too low, current value: " + currentValue);
        }
        var decremented = _testService.decrement();
        return decremented;
    }
}
