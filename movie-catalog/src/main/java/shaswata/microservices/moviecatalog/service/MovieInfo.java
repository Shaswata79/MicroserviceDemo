package shaswata.microservices.moviecatalog.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shaswata.microservices.moviecatalog.model.CatalogItem;
import shaswata.microservices.moviecatalog.model.Movie;
import shaswata.microservices.moviecatalog.model.Rating;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
                    commandProperties = {
                        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
                    })
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);    //call the movie-info api (localhost:8082)
        //put them all together
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating){
        //must be simple to reduce the possibility of error when fallback executes
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }

}
