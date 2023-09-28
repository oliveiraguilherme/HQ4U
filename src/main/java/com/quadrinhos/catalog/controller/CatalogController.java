package com.quadrinhos.catalog.controller;

import com.quadrinhos.catalog.controller.exception.ComicNotFoundException;
import com.quadrinhos.catalog.model.Comics;
import com.quadrinhos.catalog.model.DCComics.DCComicsResults;
import com.quadrinhos.catalog.service.ComicsService;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResults;
import com.quadrinhos.catalog.service.UsuarioService;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping(value = {"/api/v1/comics"})
@PreAuthorize("hasRole('ADMIN')")
public class CatalogController {
    private ComicsService comicsInteractors;
    private UsuarioService usuarioService;

    @Autowired
    public CatalogController(ComicsService comicsInteractors, UsuarioService usuarioService)
    {
        this.comicsInteractors = comicsInteractors;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('USER')")
    public String getHello(){
        return "Hello, World!";
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Comics>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sort
    ){
        var comicsList = comicsInteractors.getAll().orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista não encontrada"));

        return paginacao(page, size, sort, comicsList);

    }

    @GetMapping("/marvel")
    public ResponseEntity<Page<Comics>> getAllMarvel(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sort
    ){
        var comicsList = comicsInteractors.getAllMarvel()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista não encontrada"));
        return paginacao(page, size, sort, comicsList);
    }

    @GetMapping("/marvel/{id}")
    public ResponseEntity<MarvelComicsResults> getMarvelById(@PathVariable("id") @NotNull @Range(min = 1) @NumberFormat Long id) {
        return comicsInteractors.getMarvelById(id).map(ResponseEntity::ok)
               .orElseThrow(() -> new ComicNotFoundException("Hq não encontrada!"));
    }

    @GetMapping("/dc")
    public ResponseEntity<Page<DCComicsResults>> getAllDc(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sort
    ){
        var comicsList = comicsInteractors.getAllDc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista não encontrada"));

        return paginacao(page, size, sort, comicsList);
    }

    @GetMapping("/dc/{id}")
    public ResponseEntity<DCComicsResults> getDcComicsId(@PathVariable("id") @NotNull @Range(min = 1) @NumberFormat Long id){
        return comicsInteractors.getDcById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista não encontrada"));
    }

    public <T extends Comics> ResponseEntity<Page<T>> paginacao(Integer page, Integer size, String sort, List comicsList){
        if(page < 0){
            throw new IllegalArgumentException("Page index must not be less than zero");
        }else if(size < 6){
            throw  new IllegalArgumentException("Size index must not be less than six");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        PagedListHolder<T> pageHolder = new PagedListHolder<T>(comicsList);
        pageHolder.setPageSize(size);
        pageHolder.setPage(page);

        Page<T> comicsPage = new PageImpl<>(pageHolder.getPageList(), pageable, comicsList.stream().toList().size());
        return ResponseEntity.ok(comicsPage);
    }
}
