package com.artists.artlasapi.service;

import com.artists.artlasapi.entity.ArtWorks;

import java.util.List;
import java.util.Optional;

public interface ArtWorkService {

    public List<ArtWorks> findAll();

    public Iterable<ArtWorks> findWorkArtsByArtist(int idArtist);

    public Optional<ArtWorks> findById(int id);

    public ArtWorks save(ArtWorks artWorksEntity);

    public boolean existsById (int id);

    public boolean deleteById (int id);

}
