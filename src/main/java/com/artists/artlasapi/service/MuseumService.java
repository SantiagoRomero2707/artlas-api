package com.artists.artlasapi.service;

import com.artists.artlasapi.entity.Museum;

import java.util.List;
import java.util.Optional;

public interface MuseumService {

    public List<Museum> findAll();

    public Optional<Museum> findById(int id);

    public Iterable<Museum> findMuseumsByCity(String city);

    public Museum save(Museum artistEntity);

    public boolean existsById (int id);

    public boolean existsByCity (String city);

    public boolean deleteById (int id);

}
