package com.quadrinhos.catalog.service;

import com.quadrinhos.catalog.model.Comics;
import com.quadrinhos.catalog.model.DCComics.DCComicsResults;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResults;
import com.quadrinhos.catalog.repository.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ComicsService {

    Optional<List<MarvelComicsResults>> getAllMarvel();

    Optional<MarvelComicsResults> getMarvelById(Long id) throws NotFoundException;

    Optional<List<DCComicsResults>> getAllDc();

    Optional<DCComicsResults> getDcById(Long id) throws NotFoundException;

    Optional<List<Comics>> getAll();
}
