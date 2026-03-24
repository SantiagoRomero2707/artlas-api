package com.artists.artlasapi.repository;

import com.artists.artlasapi.entity.ArtWorks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtWorksRepository extends JpaRepository<ArtWorks, Integer> {

    Iterable<ArtWorks> findByFkIdArtistAndCountry(int fkIdArtist, String Country);

}
