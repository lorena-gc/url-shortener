package com.logcy.shorturl.service;

import com.logcy.shorturl.model.Url;
import com.logcy.shorturl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public String createShortUrl(String originalUrl) {
        String shortUrl = genereteShortUrl();
        Url url = new Url(originalUrl, shortUrl);
        urlRepository.save(url);
        return shortUrl;
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

    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).getOriginalUrl();
    }
}
