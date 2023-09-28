package com.quadrinhos.catalog.model.MarvelComics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quadrinhos.catalog.model.Comics;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelComicsResponse {

    public MarvelComicsData getData() {
        return data;
    }

    public void setData(MarvelComicsData data) {
        this.data = data;
    }

    private MarvelComicsData data;
}
