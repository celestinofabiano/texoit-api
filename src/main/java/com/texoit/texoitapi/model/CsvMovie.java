package com.texoit.texoitapi.model;

import com.opencsv.bean.CsvBindByPosition;

public class CsvMovie {

    @CsvBindByPosition(position = 0)
    private int year;

    @CsvBindByPosition(position = 1)
    private String title;

    @CsvBindByPosition(position = 2)
    private String studios;
    @CsvBindByPosition(position = 3)
    private String producers;

    @CsvBindByPosition(position = 4)
    private String winner;

    public CsvMovie() {
    }

    public CsvMovie(int year, String title, String studios, String producers, String winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
