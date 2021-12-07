package com.logcy.shorturl.controller;

import com.logcy.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/short-url")
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping()
    public String create(@RequestParam String originalUrl){
        return urlService.createShortUrl(originalUrl);
    }

    @GetMapping(value = "/redirect")
    public ResponseEntity<Void> redirect(@RequestParam String shortUrl){
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlService.getOriginalUrl(shortUrl))).build();
    }
}
