package com.logcy.shorturl.controller;

import com.logcy.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/url")
    public String create(HttpServletRequest request, @RequestBody String originalUrl){
        return urlService.baseUrl(request) + "/" + urlService.createShortUrl(originalUrl);
    }

    @GetMapping("/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url){
        return urlService.redirect(url);
    }
}
