package com.texoit.texoitapi.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.texoit.texoitapi.entity.Movie;
import com.texoit.texoitapi.exception.MovieNotFoundException;
import com.texoit.texoitapi.model.CsvMovie;
import com.texoit.texoitapi.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;

@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    private static final String FILE_NAME = "movielist.csv";

    private static final char FILE_SEPARATOR = ';';

    private static final String PRODUCER_SEPARATOR_COMMA_PATTERN = ", ";

    private static final String PRODUCER_SEPARATOR_AND_PATTERN = " and ";

    private static final int SKIP_LINES = 1;

    private Logger logger = LoggerFactory.getLogger(MovieService.class);

    @PostConstruct
    public void loadMovies() throws Exception {
        File file = new File(
                Thread.currentThread().getContextClassLoader().getResource(FILE_NAME).toURI());
        CSVParser csvParser = new CSVParserBuilder().withSeparator(FILE_SEPARATOR).build();
        CSVReader csvReader =
                new CSVReaderBuilder(new FileReader(file)).withSkipLines(SKIP_LINES)
                        .withCSVParser(csvParser)
                        .build();

        logger.info("Loading CSV file " + FILE_NAME);
        List<CsvMovie> csvMovieList =
                new CsvToBeanBuilder<CsvMovie>(csvReader).withType(CsvMovie.class).build().parse();
        csvMovieList.forEach(
                csvMovie -> getMoviesByCsvMovie(csvMovie).forEach(movie -> create(movie))
        );
    }

    public List<Movie> getWinnerMoviesOrderedByProducerAndYear() {
        return movieRepository.findByWinnerTrueOrderByProducerAscYearAsc();
    }

    public List<Movie> getMoviesByCsvMovie(CsvMovie csvMovie) {
        String[] producers = csvMovie.getProducers()
                .replaceAll(PRODUCER_SEPARATOR_AND_PATTERN, PRODUCER_SEPARATOR_COMMA_PATTERN)
                .split(PRODUCER_SEPARATOR_COMMA_PATTERN);
        List<Movie> movieList = new ArrayList<>();

        Arrays.stream(producers).forEach(producer -> {
            Movie movie = new Movie(csvMovie);
            movie.setProducer(producer.trim());
            movieList.add(movie);
        });

        return movieList;
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(Movie movie) throws RuntimeException {
        Optional<Movie> movieDb = movieRepository.findById(movie.getId());

        movieDb.ifPresentOrElse(
                m -> {
                    m.setTitle(movie.getTitle());
                    m.setStudios(movie.getStudios());
                    m.setYear(movie.getYear());
                    m.setWinner(movie.getWinner());
                    m.setProducer(movie.getProducer());
                    movieRepository.save(m);
                },
                () -> {
                    throw new MovieNotFoundException(movie.getId());
                });
        return movieDb.get();
    }

    public void delete(long id) {
        movieRepository.findById(id).ifPresentOrElse(
                p -> movieRepository.delete(p),
                () -> {
                    throw new MovieNotFoundException(id);
                });
    }

    public Movie getById(long id) {
        Optional<Movie> movieDb = movieRepository.findById(id);
        if (movieDb.isPresent()) {
            return movieDb.get();
        } else {
            throw new MovieNotFoundException(id);
        }
    }

    public List<Movie> getAll() {
        return movieRepository.findByOrderByIdAsc();
    }

}
