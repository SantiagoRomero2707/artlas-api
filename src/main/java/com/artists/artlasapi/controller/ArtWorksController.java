package com.artists.artlasapi.controller;

import com.artists.artlasapi.dto.artists.request.ArtWorksRequest;
import com.artists.artlasapi.dto.utils.Message;
import com.artists.artlasapi.entity.ArtWorks;
import com.artists.artlasapi.service.ArtWorkService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/artwork")
public class ArtWorksController {

    private final ArtWorkService artWorkService;

    public ArtWorksController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<ArtWorks>> getAllMovements(){
        List<ArtWorks> list = artWorkService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<ArtWorks> getProductById(@PathVariable int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity(new Message("NO existe"), HttpStatus.NOT_FOUND);
        ArtWorks artWorksEntity = artWorkService.findById(id).get();
        return new ResponseEntity<>(artWorksEntity, HttpStatus.OK);
    }


    @GetMapping("/work-art/detail/{id}")
    public ResponseEntity<Iterable<ArtWorks>> getWorkArtsByIdArtist(@PathVariable int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity(new Message("NO existe"), HttpStatus.NOT_FOUND);
        Iterable<ArtWorks> artWorksEntity = artWorkService.findWorkArtsByArtist(id);
        return new ResponseEntity<>(artWorksEntity, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ArtWorksRequest artWorksRequest){
        if(StringUtils.isBlank(String.valueOf(artWorksRequest.getName())))
            return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artWorksRequest.getCountry()))
            return new ResponseEntity<>(new Message("El país es obligatorio"), HttpStatus.BAD_REQUEST);
        ArtWorks newWorkArtist = new ArtWorks();

        newWorkArtist.setName(artWorksRequest.getName());
        newWorkArtist.setCountry(artWorksRequest.getCountry());
        newWorkArtist.setFkIdArtist(artWorksRequest.getFkIdArtist());
        artWorkService.save(newWorkArtist);
        return new ResponseEntity<>(new Message("Artista registrado y creado"), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArtWork(@PathVariable int id, @RequestBody ArtWorksRequest artWorksRequest){

        if(!artWorkService.existsById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(String.valueOf(artWorksRequest.getName())))
            return new ResponseEntity<>(new Message("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artWorksRequest.getCountry()))
            return new ResponseEntity<>(new Message("El tipo de movimiento es obligatario"), HttpStatus.BAD_REQUEST);

        ArtWorks savedArtWorks = artWorkService.findById(id).get();
        savedArtWorks.setName(artWorksRequest.getName());
        savedArtWorks.setCountry(artWorksRequest.getCountry());
        savedArtWorks.setFkIdArtist(artWorksRequest.getFkIdArtist());
        artWorkService.save(savedArtWorks);
        return new ResponseEntity<>(new Message("Artista actualizado"), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArtWorks(@PathVariable int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        artWorkService.deleteById(id);
        return new ResponseEntity<>(new Message("Artista eliminado"), HttpStatus.OK);
    }
}
