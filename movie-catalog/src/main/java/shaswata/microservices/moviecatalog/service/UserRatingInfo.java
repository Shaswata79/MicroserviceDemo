package shaswata.microservices.moviecatalog.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shaswata.microservices.moviecatalog.model.Rating;
import shaswata.microservices.moviecatalog.model.UserRating;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
                    commandProperties = {
                            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
                    })
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);     //call rating-data api (localhost:8083)
    }

    public UserRating getFallbackUserRating(String userId){
        //must be simple to reduce the possibility of error when fallback executes
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Arrays.asList(new Rating("0", 0)));
        return userRating;
    }

}
