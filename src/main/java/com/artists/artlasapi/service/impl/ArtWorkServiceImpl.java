package com.artists.artlasapi.service.impl;


import com.artists.artlasapi.entity.ArtWorks;
import com.artists.artlasapi.repository.ArtWorksRepository;
import com.artists.artlasapi.service.ArtWorkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtWorkServiceImpl implements ArtWorkService {
    private final ArtWorksRepository artWorksEntityRepository;

    public ArtWorkServiceImpl(ArtWorksRepository artWorksEntityRepository) {
        this.artWorksEntityRepository = artWorksEntityRepository;
    }

    @Override
    @Transactional
    public List<ArtWorks> findAll() {
        return artWorksEntityRepository.findAll();
    }

    @Override
    @Transactional
    public Iterable<ArtWorks> findWorkArtsByArtist(int idArtist) {
        return artWorksEntityRepository.findByFkIdArtistAndCountry(idArtist, "Colombia");
    }

    @Override
    @Transactional
    public Optional<ArtWorks> findById(int id) {
        return artWorksEntityRepository.findById(id);
    }

    @Override
    @Transactional
    public ArtWorks save(ArtWorks artWorksEntity) {
        return artWorksEntityRepository.save(artWorksEntity);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return artWorksEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteById(int id){
        try{
            artWorksEntityRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

