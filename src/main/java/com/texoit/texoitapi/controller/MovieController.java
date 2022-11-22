package com.texoit.texoitapi.controller;

import com.texoit.texoitapi.entity.Movie;
import com.texoit.texoitapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok().body(movieService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(movieService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Movie> create(@RequestBody Movie Movie) {
        return ResponseEntity.ok().body(movieService.create(Movie));
    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> update(@PathVariable long id, @RequestBody Movie Movie) {
        Movie.setId(id);
        try {
            return ResponseEntity.ok().body(movieService.update(Movie));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable long id) {
        movieService.delete(id);
        return HttpStatus.OK;
    }
    
}

