package br.com.munhosdev.urlshortener.repository;

import br.com.munhosdev.urlshortener.domain.URLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface URLRepository extends MongoRepository<URLEntity, String> {
}
