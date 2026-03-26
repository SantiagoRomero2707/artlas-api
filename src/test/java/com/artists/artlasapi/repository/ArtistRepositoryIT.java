package com.artists.artlasapi.repository;

import com.artists.artlasapi.entity.Artist;
import com.artists.artlasapi.utils.ArtistTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class ArtistRepositoryIT {

    @Autowired
    private ArtistRepository artistEntityRepository;

    @Test
    public void testSaveArtist() {
        Artist saved = artistEntityRepository.save(ArtistTestUtils.buildArtist());
        assertNotNull(saved);
        assertTrue(saved.getIdArtists() > 0);
    }

    @Test
    public void testFindById() {
        Artist saved = artistEntityRepository.save(ArtistTestUtils.buildArtist());

        Optional<Artist> found = artistEntityRepository.findById(saved.getIdArtists());

        assertTrue(found.isPresent());
        assertEquals(saved.getIdArtists(), found.get().getIdArtists());
    }

    @Test
    public void testUpdateArtist() {
        String newLastName = "Sabogal Castro";
        Artist saved = artistEntityRepository.save(ArtistTestUtils.buildArtist());

        saved.setLastName(newLastName);
        artistEntityRepository.save(saved);

        Optional<Artist> updated = artistEntityRepository.findById(saved.getIdArtists());
        assertTrue(updated.isPresent());
        assertEquals(newLastName, updated.get().getLastName());
    }

    @Test
    public void testListArtist() {
        artistEntityRepository.save(ArtistTestUtils.buildArtist());

        List<Artist> allArtists = artistEntityRepository.findAll();

        assertFalse(allArtists.isEmpty());
    }

    @Test
    public void testDeleteArtist() {
        Artist saved = artistEntityRepository.save(ArtistTestUtils.buildArtist());
        int id = saved.getIdArtists();

        artistEntityRepository.deleteById(id);

        assertFalse(artistEntityRepository.findById(id).isPresent());
    }
}
