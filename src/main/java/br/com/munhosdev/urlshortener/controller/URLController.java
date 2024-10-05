package br.com.munhosdev.urlshortener.controller;

import br.com.munhosdev.urlshortener.domain.URLEntity;
import br.com.munhosdev.urlshortener.domain.dto.UrlDTO;
import br.com.munhosdev.urlshortener.service.ShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class URLController {

    @Autowired
    private ShortenerService service;

    @PostMapping("/shorter-url")
    public ResponseEntity<UrlDTO> encurtar (@RequestBody UrlDTO url, HttpServletRequest request) {
        var id = service.create(url);
        var redirectUrl = request.getRequestURL().toString().replace("shorter-url",id);
        return ResponseEntity.ok(new UrlDTO(redirectUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirect (@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.FOUND).headers(service.redirect(id)).build();
    }

    @GetMapping("/getALlUrls")
    public ResponseEntity<List<URLEntity>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
