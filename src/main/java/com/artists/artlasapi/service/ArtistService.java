package com.artists.artlasapi.service;

import com.artists.artlasapi.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    List<Artist> findAll();

    Optional<Artist> findById(int id);

    void save(Artist artistEntity);

    boolean existsById (int id);

    void deleteById (int id);
}

