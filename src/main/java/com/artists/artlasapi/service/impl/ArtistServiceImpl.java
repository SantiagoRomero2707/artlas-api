package com.artists.artlasapi.service.impl;

import com.artists.artlasapi.entity.Artist;
import com.artists.artlasapi.repository.ArtistRepository;
import com.artists.artlasapi.service.ArtistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistEntityRepository;

    public ArtistServiceImpl(ArtistRepository artistEntityRepository) {
        this.artistEntityRepository = artistEntityRepository;
    }

    @Override
    @Transactional
    public List<Artist> findAll() {
        return artistEntityRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Artist> findById(int id) {
        return artistEntityRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Artist artistEntity) {
        artistEntityRepository.save(artistEntity);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return artistEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id){
        try{
            artistEntityRepository.deleteById(id);
        }catch (Exception e){
            //TODO
        }
    }
}
