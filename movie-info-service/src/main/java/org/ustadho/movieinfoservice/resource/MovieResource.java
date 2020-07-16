package org.ustadho.movieinfoservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.ustadho.movieinfoservice.model.Movie;
import org.ustadho.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("movies")
public class MovieResource {
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject(
            "https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+this.apiKey,
                MovieSummary.class
        );
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
