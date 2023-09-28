package com.quadrinhos.catalog.repository.data;

import com.quadrinhos.catalog.model.DCComics.DCComicsData;
import com.quadrinhos.catalog.model.DCComics.DCComicsResponse;
import com.quadrinhos.catalog.model.MarvelComics.MarvelComicsResponse;
import feign.Headers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "dccomics", url = "https://comicvine.gamespot.com/api")
@Validated
public interface DcComicsClient {
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class) })
    @GetMapping("/issues")
    DCComicsResponse getAllDc(@RequestHeader("User-Agent") String token, @RequestParam(value = "api_key") String ts, @RequestParam(value = "format") String format, @RequestParam(value = "filter") String filter);

    @ApiOperation(value = "Comic by Id", nickname = "comicId", notes = "Fetches a single comic by id.", response = Object.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class) })
    @GetMapping("/issue/4000-{comicId}")
    DCComicsData getDcById(@RequestHeader("User-Agent") String token, @RequestParam(value = "api_key") String ts, @ApiParam(value = "A single comic id.", required = true) @PathVariable("comicId") Long comicId, @RequestParam(value = "format") String format);
}
