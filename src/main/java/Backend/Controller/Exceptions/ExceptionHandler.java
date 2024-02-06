//package Backend.Controller.Exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(TestControllerNumberTooLowException.class)
//    public ResponseEntity<String> handleException(TestControllerNumberTooLowException e) {
//        return ResponseEntity
//                .badRequest()
//                .body(new MyError(HttpStatus.BAD_REQUEST, e.getDescription()));
//    }
//}