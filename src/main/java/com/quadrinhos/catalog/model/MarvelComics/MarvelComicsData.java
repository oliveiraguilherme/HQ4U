package com.quadrinhos.catalog.model.MarvelComics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelComicsData {
    private List<MarvelComicsResults> results;

    public List<MarvelComicsResults> getResults(){
        return results;
    }

    public void setResults(List<MarvelComicsResults> results){
        this.results = results;
    }
}
