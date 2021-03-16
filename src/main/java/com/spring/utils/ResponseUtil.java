package com.spring.utils;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

/**
 * Utility class for ResponseEntity creation.
 */
public interface ResponseUtil {

    static <X> ResponseEntity<X> wrap(X response, HttpStatus status) {
        return new ResponseEntity<>(response, status);
    }
    
    static <X> ResponseEntity<X> wrapOrNotFound(X maybeResponse) {
        return wrapOrNotFound(Optional.ofNullable(maybeResponse), null);
    }
    
    static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null);
    }

    static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
