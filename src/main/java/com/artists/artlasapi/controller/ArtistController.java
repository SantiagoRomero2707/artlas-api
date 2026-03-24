package com.artists.artlasapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.micrometer.common.util.StringUtils;

import com.artists.artlasapi.dto.artists.request.ArtistRequest;
import com.artists.artlasapi.dto.utils.Message;

import com.artists.artlasapi.service.ArtistService;

import com.artists.artlasapi.entity.Artist;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Artist>> getAllMovements(){
        List<Artist> list = artistService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Artist> getProductById(@PathVariable int id){
        if(!artistService.existsById(id))
            return new ResponseEntity(new Message("NO existe"), HttpStatus.NOT_FOUND);

        Artist artistEntity = artistService.findById(id).orElseThrow();
        return new ResponseEntity<>(artistEntity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ArtistRequest artistRequest){
        if(StringUtils.isBlank(String.valueOf(artistRequest.getName())))
            return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artistRequest.getLastName()))
            return new ResponseEntity<>(new Message("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);

        Artist movementSave =
                new Artist.ArtistBuilder()
                        .setName(artistRequest.getName())
                        .setLastName(artistRequest.getLastName())
                        .setTypeIDE(artistRequest.getTypeIDE())
                        .setNumberIDE(artistRequest.getNumberIDE()).build();

        artistService.save(movementSave);

        return new ResponseEntity<>(new Message("Artista registrado y creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable int id, @RequestBody ArtistRequest artistRequest){
        if(!artistService.existsById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(String.valueOf(artistRequest.getName())))
            return new ResponseEntity<>(new Message("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artistRequest.getLastName()))
            return new ResponseEntity<>(new Message("El tipo de movimiento es obligatario"), HttpStatus.BAD_REQUEST);

        Artist artistEntity = artistService.findById(id).orElseThrow();

        artistEntity.setName(artistRequest.getName());
        artistEntity.setLastName(artistRequest.getLastName());
        artistEntity.setNumberIDE(artistRequest.getNumberIDE());
        artistEntity.setTypeIDE(artistRequest.getTypeIDE());

        artistService.save(artistEntity);
        return new ResponseEntity<>(new Message("Artista actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        if(!artistService.existsById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        artistService.deleteById(id);
        return new ResponseEntity<>(new Message("Artista eliminado"), HttpStatus.OK);
    }
}
