package com.springmvctutorial.springboot_springmvc_first_app;

import ch.qos.logback.core.model.Model;
import com.springmvctutorial.springboot_springmvc_first_app.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.function.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class WelcomeController {

    // Annotate with @GetMapping and add endpoint of /welcome
    // This REST API with handle a http GET Request with rest endpoint /welcome
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring MVC Course";
    }

    // Endpoint to return today's date in custom format
    @GetMapping("/today")
    public String today(@DateTimeFormat(pattern = "yyyy/MM/dd") Date date) {
        return "Today's date is: " + new SimpleDateFormat("dd-MM-yyyy").format(date != null ? date : new Date());
    }

    // Using @InitBinder to register a custom date format globally for this controller
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false); // Enforce strict parsing
        binder.registerCustomEditor(Date.class, new org.springframework.beans.propertyeditors.CustomDateEditor(dateFormat, true));
    }

    // Exception handler for invalid date format
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInvalidDateFormat(Exception ex) {
        if (ex.getCause() instanceof IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid date format. Please use 'yyyy/MM/dd'.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }

    //Redirecting and Relative Servlet Requests video
    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectToGreeting(HttpServletRequest request) {
        URI greetingUri = ServletUriComponentsBuilder.fromContextPath(request)
                .path("/greeting")
                .queryParam("name", "Spring")
                .build()
                .toUri();

        return ResponseEntity.status(HttpStatus.FOUND).location(greetingUri).build();
    }






//    //Adding Post request for form
//    @PostMapping("/welcome")
//    public String signupFormSubmit() {
//        return signupFormSubmit(null);
//    }
//
//    //Adding Post request for form
//    @PostMapping("/welcome")
//    public String signupFormSubmit(@ModelAttribute User user) {
//        return "signup-result";
//    }
//
//    //handle HTTP GET request
//    //map to /signup and return the name of the view
//    public String signupForm("/signup"){
//        return "signup";
//    }


//@CrossOrigin(origins = "http://localhost:3000")
//@ApiOperation(value = "Get name")
//@RequestMapping(value="/corsTesting", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity‹?> getTestDataForCORSRequest () {
//    Response response = Employee.generateMockResponse();
//    return new ResponseEntity<>(response, HttpStatus.OK);






//    @GetMapping("/greeting")
//    public String greeting(HttpServletRequest request, Model model) {
//        String   originalHost = request.getHeader("X-Forwarded-Host");
//        int originalPort = Integer.parseInt(request.getHeader("X-Forwarded-Port"));
//        String originalProtocol = request.getHeader("X-Forwarded-Proto");
//
//        //  model.addAttribute("originalHost", originalHost);
//        //  model.addAttribute("originalPort", originalPort);
//       //   model.addAttribute("originalProtocol", originalProtocol);
//
//        // ... other logic
//        return "greeting";
//    }}
}



















