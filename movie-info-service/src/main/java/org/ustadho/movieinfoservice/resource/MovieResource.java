package org.ustadho.movieinfoservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ustadho.movieinfoservice.model.Movie;

@RestController
@RequestMapping("movies")
public class MovieResource {

    @GetMapping("{id}")
    public Movie getMovieInfo(@PathVariable("id") String id) {
        return new Movie(id, "Test name");
    }
}
