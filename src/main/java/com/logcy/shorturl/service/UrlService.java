package com.logcy.shorturl.service;

import com.logcy.shorturl.model.Url;
import com.logcy.shorturl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public String createShortUrl(String originalUrl) {
        Url url = new Url(originalUrl, genereteShortUrl());
        return checkAndReturnShortUrl(url);
    }

    private String genereteShortUrl() {
        return createSequenceAleatoryChar();
    }

    private String createSequenceAleatoryChar() {
        final String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvYyWwXxZz";
        final int N = alphabet.length();

        Random r = new Random();
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sequence.append(alphabet.charAt(r.nextInt(N)));
        }
        return sequence.toString();
    }

    private String checkAndReturnShortUrl(Url url) {
        Url existingUrl = urlRepository.findByOriginalUrl(url.getOriginalUrl());
        if( existingUrl == null){
            urlRepository.save(url);
            return url.getShortUrl();
        } else {
            return existingUrl.getShortUrl();
        }
    }

    public String getOriginalUrl(String shortUrl) {
        Url existingUrl = urlRepository.findByShortUrl(shortUrl);
        if(existingUrl != null){
            return existingUrl.getOriginalUrl();
        }
        return null;
    }

    public String baseUrl(HttpServletRequest request) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
    }

    public ResponseEntity<Void> redirect(String url){
        String originalUrl = getOriginalUrl(url);
        if (originalUrl != null){
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
