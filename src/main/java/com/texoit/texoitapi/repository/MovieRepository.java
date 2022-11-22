package com.texoit.texoitapi.repository;

import com.texoit.texoitapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByOrderByIdAsc();
    List<Movie> findByWinnerTrueOrderByProducerAscYearAsc();

}
