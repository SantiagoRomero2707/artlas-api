package com.artists.artlasapi.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.artists.artlasapi.repository.MuseumRepository;
import com.artists.artlasapi.service.MuseumService;
import org.springframework.stereotype.Service;
import com.artists.artlasapi.entity.Museum;

import java.util.List;
import java.util.Optional;

@Service
public class MuseumServiceImpl implements MuseumService {

    private final MuseumRepository museumEntityRepository;

    public MuseumServiceImpl(MuseumRepository museumEntityRepository) {
        this.museumEntityRepository = museumEntityRepository;
    }


    @Override
    @Transactional
    public List<Museum> findAll() {
        return museumEntityRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Museum> findById(int id) {
        return museumEntityRepository.findById(id);
    }

    @Override
    public Iterable<Museum> findMuseumsByCity(String city) {
        return museumEntityRepository.findByCityAndNameStartingWith(city, "L");
    }

    @Override
    @Transactional
    public Museum save(Museum museumEntity) {
        return museumEntityRepository.save(museumEntity);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return museumEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean existsByCity(String city) {
        return museumEntityRepository.existsByCity(city);
    }

    @Override
    @Transactional
    public boolean deleteById(int id){
        try{
            museumEntityRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

