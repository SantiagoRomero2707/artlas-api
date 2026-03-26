package com.artists.artlasapi.repository;

import com.artists.artlasapi.entity.Artist;
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
    public void testSaveArtist(){
        Artist artistEntity = new Artist.ArtistBuilder()
                .setName("Johan Santiago")
                .setLastName("Romero Duarte")
                .setTypeIDE("AB-CD")
                .setNumberIDE("EF-GH")
                .build();

        Artist artistSaved = artistEntityRepository.save(artistEntity);
        idArtist = artistSaved.getIdArtists();
        assertNotNull(artistSaved);
    }

    @Test
    @Order(2)
    public void testFindById(){
        Optional<Artist> artistEntity = artistEntityRepository.findById(idArtist);
        assertTrue(artistEntity.isPresent());
        assertEquals(idArtist, artistEntity.get().getIdArtists());
    }

    @Test
    @Rollback(value = false)
    @Order(3)
    public void testUpdateArtist(){
        String newLastName = "Sabogal Castro";

        Optional<Artist> existArtist = artistEntityRepository.findById(idArtist);
        if(existArtist.isPresent()){
            Artist artistEntity = new Artist.ArtistBuilder()
                    .setIdArtists(idArtist)
                    .setName(existArtist.get().getName())
                    .setLastName(newLastName)
                    .setTypeIDE(existArtist.get().getTypeIDE())
                    .setNumberIDE(existArtist.get().getNumberIDE())
                    .build();

            artistEntityRepository.save(artistEntity);
        }

        Optional<Artist> updateArtist = artistEntityRepository.findById(idArtist);
        if(updateArtist.isEmpty()){
            throw new RuntimeException("El artista no está creado en base de datos");
        }
        assertEquals(newLastName, updateArtist.get().getLastName());
    }

    @Test
    @Order(4)
    public void testListArtist(){
        List<Artist> allArtist = artistEntityRepository.findAll();

        for(Artist artistEntity: allArtist){
            System.out.println(artistEntity);
        }
        assertFalse(allArtist.isEmpty());
    }

    @Test
    @Rollback(value = false)
    @Order(5)
    public void testDeleteArtist(){
        boolean isExistBeforeDelete = artistEntityRepository.findById(idArtist).isPresent();
        artistEntityRepository.deleteById(idArtist);

        boolean notExistAfterDelete = artistEntityRepository.findById(idArtist).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
