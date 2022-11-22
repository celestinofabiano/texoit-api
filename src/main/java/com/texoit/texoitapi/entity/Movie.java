package com.texoit.texoitapi.entity;

import com.texoit.texoitapi.model.CsvMovie;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="production_year", nullable=false)
	private Integer year;
	
	@Column(name="title", nullable=false)
	private String title;

    @Column(name="studios", nullable=false)
    private String studios;

    @Column(name="producer", nullable=false)
    private String producer;
	@Column(name="winner", nullable=false)
	private Boolean winner;

	public Movie() {}

    public Movie(CsvMovie csvMovie) {
        this.year = csvMovie.getYear();
        this.title = csvMovie.getTitle();
        this.studios = csvMovie.getStudios();
        this.winner = csvMovie.getWinner() != null && csvMovie.getWinner().equalsIgnoreCase("yes");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{"
                + "title='" + title + '\'' +
                ", studios='" + studios + '\'' +
                ", producer='" + producer + '\'' +
                ", year=" + year +
                ", winner=" + winner +
                '}';
    }
}
