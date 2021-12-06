package com.logcy.shorturl.model;

import javax.persistence.*;

@Entity
@Table(name="url")
public class Url {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "originalUrl")
    private String originalUrl;

    @Column(name = "shortUrl", unique = true)
    private String shortUrl;

    public Url(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
