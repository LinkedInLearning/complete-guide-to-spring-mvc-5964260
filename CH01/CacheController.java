package com.springmvctutorial.springboot_springmvc_first_app;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

    @org.springframework.web.bind.annotation.GetMapping("/cached-resource")
    public ResponseEntity<String> getCachedResource() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // Cache for 1 hour
                .body("This is a cached response.");
    }

    @GetMapping("/no-cache")
    public ResponseEntity<String> getNoCacheResource() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore()) // No caching
                .body("This response is not cached.");
    }
}
