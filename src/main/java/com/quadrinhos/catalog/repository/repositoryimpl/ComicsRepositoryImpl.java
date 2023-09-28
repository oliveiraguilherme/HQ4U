package com.quadrinhos.catalog.repository.repositoryimpl;

import com.quadrinhos.catalog.model.DCComics.DCComicsData;
import com.quadrinhos.catalog.model.DCComics.DCComicsResponse;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResponse;
import com.quadrinhos.catalog.repository.data.ComicsClient;
import com.quadrinhos.catalog.repository.ComicsRepository;
import com.quadrinhos.catalog.repository.data.DcComicsClient;
import com.quadrinhos.catalog.repository.exception.NotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ComicsRepositoryImpl implements ComicsRepository {

    private ComicsClient comicsClient;
    private DcComicsClient dcComicsClient;

    public ComicsRepositoryImpl(ComicsClient comicsClient, DcComicsClient dcComicsClient){
        this.comicsClient = comicsClient;
        this.dcComicsClient = dcComicsClient;
    }

    @Override
    public Optional<MarvelComicsResponse> getAllMarvel(String ts, String apikey, String hash) {
        try{
            return Optional.of(comicsClient.getAllMarvel(ts, apikey, hash));
        }catch (FeignException.NotFound feignException) {
            throw new NotFoundException(feignException.getMessage());
        }catch (FeignException feignException) {
            throw feignException;
        }

    }

    @Override
    public Optional<MarvelComicsResponse> getMarvelById(String ts, String apikey, String hash, Long id) throws NotFoundException{
        try{
            return Optional.of(comicsClient.getMarvelById(ts, apikey, hash, id));
        }catch (FeignException.NotFound feignException) {
            throw new NotFoundException(feignException.getMessage());
        }catch (FeignException feignException) {
            throw feignException;
        }
    }

    @Override
    public DCComicsResponse getAllDc(String apikeydc){
        return dcComicsClient.getAllDc("HQ4UStudyApplication", apikeydc, "json", "DC COMICS");
    }

    @Override
    public DCComicsData getDcById(String apikeydc, Long Id) throws NotFoundException{
        try{
            var teste = dcComicsClient.getDcById("HQ4UStudyApplication", apikeydc, Id, "json");
            return teste;
        }catch (FeignException.NotFound feignException) {
            throw new NotFoundException(feignException.getMessage());
        }catch (FeignException feignException) {
            throw feignException;
        }

    }
}
