package com.artists.artlasapi.repository;

import com.artists.artlasapi.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
