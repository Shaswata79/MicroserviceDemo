package shaswata.microservices.moviecatalog.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import shaswata.microservices.moviecatalog.model.CatalogItem;
import shaswata.microservices.moviecatalog.model.Movie;
import shaswata.microservices.moviecatalog.model.Rating;
import shaswata.microservices.moviecatalog.model.UserRating;
import shaswata.microservices.moviecatalog.service.MovieInfo;
import shaswata.microservices.moviecatalog.service.UserRatingInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = userRatingInfo.getUserRating(userId);


        return userRating.getRatings().stream()
                .map(rating -> {
                    return movieInfo.getCatalogItem(rating);
                })
                .collect(Collectors.toList());

    }









}