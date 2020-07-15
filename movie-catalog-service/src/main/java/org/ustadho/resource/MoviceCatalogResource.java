package org.ustadho.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.ustadho.model.CatalogItem;
import org.ustadho.model.Movie;
import org.ustadho.model.Rating;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog")
public class MoviceCatalogResource {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1", 4),
                new Rating("2", 3)
        );

        return ratings.stream().map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(), "Desc", rating.getRating());

        })
                .collect(Collectors.toList());


    }
}
