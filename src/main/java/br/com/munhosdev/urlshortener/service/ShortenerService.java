package br.com.munhosdev.urlshortener.service;

import br.com.munhosdev.urlshortener.domain.URLEntity;
import br.com.munhosdev.urlshortener.domain.dto.UrlDTO;
import br.com.munhosdev.urlshortener.repository.URLRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ShortenerService {

    private final URLRepository repository;

    public String create(UrlDTO url){
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5,10);
        } while(repository.existsById(id));

       var shortUrl = repository.save(new URLEntity(id, url.URL(), LocalDateTime.now().plusMinutes(1)));
       return shortUrl.getId();
    }

    public HttpHeaders redirect(String id){

        var url = repository.findById(id).orElseThrow(() -> new RuntimeException("URL not found"));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getFullUrl()));

        return headers;
    }

    public List<URLEntity> findAll(){
        return repository.findAll();
    }
}
