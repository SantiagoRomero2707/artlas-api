package com.artists.artlasapi.repository;

import com.artists.artlasapi.entity.Museum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuseumRepository extends JpaRepository<Museum, Integer> {

    Iterable<Museum> findByCityAndNameStartingWith(String city, String startWith);

    boolean existsByCity (String city);
}
