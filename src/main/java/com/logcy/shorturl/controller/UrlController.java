package com.logcy.shorturl.controller;

import com.logcy.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/url")
    public String create(HttpServletRequest request, @RequestBody String originalUrl){
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        return baseUrl + "/" + urlService.createShortUrl(originalUrl);
    }

    @GetMapping("/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url){
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlService.getOriginalUrl(url))).build();
    }
}
