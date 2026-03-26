package com.artists.artlasapi.repository;

import com.artists.artlasapi.entity.Artist;
import com.artists.artlasapi.utils.ArtistTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArtistRepositoryIT {

    @Autowired
    private ArtistRepository artistEntityRepository;

    private static Integer idArtist;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void testSaveArtist() {
        Artist artistSaved = artistEntityRepository.save(ArtistTestUtils.buildArtist());
        idArtist = artistSaved.getIdArtists();
        assertNotNull(artistSaved);
        assertTrue(artistSaved.getIdArtists() > 0);
    }

    @Test
    @Order(2)
    public void testFindById() {
        Optional<Artist> artistEntity = artistEntityRepository.findById(idArtist);
        assertTrue(artistEntity.isPresent());
        assertEquals(idArtist, artistEntity.get().getIdArtists());
    }

    @Test
    @Rollback(value = false)
    @Order(3)
    public void testUpdateArtist() {
        String newLastName = "Sabogal Castro";

        Optional<Artist> existArtist = artistEntityRepository.findById(idArtist);
        assertTrue(existArtist.isPresent(), "El artista no está creado en base de datos");

        existArtist.get().setLastName(newLastName);
        artistEntityRepository.save(existArtist.get());

        Optional<Artist> updatedArtist = artistEntityRepository.findById(idArtist);
        assertTrue(updatedArtist.isPresent());
        assertEquals(newLastName, updatedArtist.get().getLastName());
    }

    @Test
    @Order(4)
    public void testListArtist() {
        List<Artist> allArtist = artistEntityRepository.findAll();
        allArtist.forEach(artist -> log.info("Artist: {}", artist));
        assertFalse(allArtist.isEmpty());
    }

    @Test
    @Rollback(value = false)
    @Order(5)
    public void testDeleteArtist() {
        assertTrue(artistEntityRepository.findById(idArtist).isPresent());
        artistEntityRepository.deleteById(idArtist);
        assertFalse(artistEntityRepository.findById(idArtist).isPresent());
    }
}
