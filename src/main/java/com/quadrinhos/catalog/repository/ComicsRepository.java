package com.quadrinhos.catalog.repository;

import com.quadrinhos.catalog.model.DCComics.DCComicsData;
import com.quadrinhos.catalog.model.DCComics.DCComicsResponse;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResponse;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicsRepository {

    Optional<MarvelComicsResponse> getAllMarvel(String ts, String apikey, String hash);
    Optional<MarvelComicsResponse> getMarvelById(String ts, String apikey, String hash, Long id);

    DCComicsResponse getAllDc(String apikeydc);

    DCComicsData getDcById(String apikeydc, Long id);
}
