package br.com.munhosdev.urlshortener.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "urls")
public class URLEntity {

    @Id
    private String id;
    private String fullUrl;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAt;
}
