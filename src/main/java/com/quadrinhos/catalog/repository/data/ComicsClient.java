package com.quadrinhos.catalog.repository.data;

import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "comics", url = "https://gateway.marvel.com:443/v1/public/comics?")
@Validated
public interface ComicsClient {

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = MarvelComicsResponse.class) })
    @GetMapping()
    MarvelComicsResponse getAllMarvel(@RequestParam(value = "ts") String ts, @RequestParam(value = "apikey") String apikey, @RequestParam(value = "hash") String hash);

    @ApiOperation(value = "Comic by Id", nickname = "comicId", notes = "Fetches a single comic by id.", response = Object.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class) })
    @GetMapping("/{comicId}")
    MarvelComicsResponse getMarvelById(@RequestParam(value = "ts") String ts, @RequestParam(value = "apikey") String apikey, @RequestParam(value = "hash") String hash,
                                  @ApiParam(value = "A single comic id.", required = true) @PathVariable("comicId") Long comicId);
}
