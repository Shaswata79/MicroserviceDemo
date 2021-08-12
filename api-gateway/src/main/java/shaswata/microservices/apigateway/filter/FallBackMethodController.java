package shaswata.microservices.apigateway.filter;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallBackMethodController {

    @GetMapping("/moviecatalogFallBack")
    public String moviecatalogBackMethod() {
        return "Movie Catalog Service is taking longer than Expected." +
                " Please try again later";
    }

}
