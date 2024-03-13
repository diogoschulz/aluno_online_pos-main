package br.com.alunoonline.api.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    @Cacheable("CACHE PALAVRA")
    public String getPalavra(){
        log.info("GET PALAVRA ACIONADO");
        return "Texto Cacheado";

    }

    @DeleteMapping
    public void resetCache(){
        cacheManager.getCache("CACHE_PALAVRA").clear();
    }
}


