package com.quadrinhos.catalog.service.serviceimpl;

import com.quadrinhos.catalog.model.Comics;
import com.quadrinhos.catalog.model.DCComics.DCComicsResults;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResults;
import com.quadrinhos.catalog.repository.ComicsRepository;
import com.quadrinhos.catalog.repository.exception.NotFoundException;
import com.quadrinhos.catalog.service.ComicsService;
import com.quadrinhos.catalog.service.exception.ComicsInvalidException;
import com.quadrinhos.catalog.service.exception.ElementoVazioException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import java.net.URI;

@Service
public class ComicsServiceImpl implements ComicsService {
    private final ComicsRepository comicsRepository;
    private final ComicsValidator comicsValidator;
    private String ts = "Coloque seu ts aqui";
    private String apikey = "Coloque sua apiKey aqui";
    private String hash = "Coloque o seu hash aqui";

    private String apikeydc = "coloque sua apiKey";
    public ComicsServiceImpl(ComicsRepository comicsRepository,
                             Validator validator,
                             ComicsValidator comicsValidator){
        this.comicsRepository = comicsRepository;
        this.comicsValidator = comicsValidator;
    }

    @Override
    public Optional<List<MarvelComicsResults>> getAllMarvel(){
        var comicsList = comicsRepository.getAllMarvel(ts, apikey, hash).get().getData().getResults();

        List<MarvelComicsResults> validComics = new ArrayList<>();

        for (int i = 0; i < comicsList.size(); i++) {
            MarvelComicsResults comic = comicsList.get(i);
            Errors errors = new BeanPropertyBindingResult(comic, "comic");
            comicsValidator.validate(comic, errors);
            if(!errors.hasErrors()){
                validComics.add(comic);
            }
        }

        return Optional.ofNullable(validComics);
    }

    @Override
    public Optional<MarvelComicsResults> getMarvelById(Long id) throws NotFoundException {
        var comicsMarvel = comicsRepository.getMarvelById(ts, apikey, hash, id).get().getData().getResults().get(0);
        Errors errors = new BeanPropertyBindingResult(comicsMarvel, "comic");
        comicsValidator.validate(comicsMarvel, errors);
        if(errors.hasErrors()){
            //talvez eu tenha que fazer uma condição caso tenha mais de um erro na lista
            throw new ComicsInvalidException("Comics is invalid!", errors.getAllErrors().get(0).getDefaultMessage());
        }
        if(comicsMarvel == null){
            throw new ElementoVazioException("Elemento Vazio!");
        }
        return Optional.of(comicsMarvel);
    }

    @Override
    public Optional<List<DCComicsResults>> getAllDc(){
        var comicsList = comicsRepository.getAllDc(apikeydc).getResults();

        List<DCComicsResults> validComics = new ArrayList<>();

        for (int i = 0; i < comicsList.size(); i++) {
            DCComicsResults comic = comicsList.get(i);
            Errors errors = new BeanPropertyBindingResult(comic, "comic");
            comicsValidator.validate(comic, errors);
            if(!errors.hasErrors()){
                validComics.add(comic);
            }
        }

        return Optional.ofNullable(validComics);

    }

    @Override
    public Optional<DCComicsResults> getDcById(Long id) throws NotFoundException{

        var comicsDc = comicsRepository.getDcById(apikeydc, id).getResults();
        Errors errors = new BeanPropertyBindingResult(comicsDc, "comic");
        comicsValidator.validate(comicsDc, errors);
        if(errors.hasErrors()){
            throw new ComicsInvalidException("Comics is invalid!");
        }
        if(comicsDc == null){
            throw new ElementoVazioException("Elemento Vazio!");
        }
        return Optional.of(comicsDc);
    }

    @Override
    public Optional<List<Comics>> getAll() throws NotFoundException{
        List<Comics> comicsList = new ArrayList<>();

        var getMarvel = getAllMarvel().orElse(Collections.emptyList());
        var getDc = getAllDc().orElse(Collections.emptyList());

        comicsList = Stream.concat(getMarvel.stream(), getDc.stream()).collect(Collectors.toList());

        return Optional.of(comicsList);

    }
}
