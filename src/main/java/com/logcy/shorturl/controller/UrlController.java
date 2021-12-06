package com.logcy.shorturl.controller;

import com.logcy.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/short-url")
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping()
    public String create(@RequestParam String originalUrl){
        return urlService.createShortUrl(originalUrl);
    }
}
