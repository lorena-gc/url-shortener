package com.logcy.shorturl.repository;

import com.logcy.shorturl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {

    public Url findByShortUrl(String shortUrl);

}
