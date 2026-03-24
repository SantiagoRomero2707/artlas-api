package com.artists.artlasapi.controller;

import com.artists.artlasapi.dto.artists.request.MuseumRequest;
import com.artists.artlasapi.dto.utils.Message;
import com.artists.artlasapi.entity.Museum;
import com.artists.artlasapi.service.MuseumService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/museum")
public class MuseumController {

    private final MuseumService museumService;

    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<Museum>> getAllMuseums(){
        List<Museum> list = museumService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/city/{city}")
    public ResponseEntity<Iterable<Museum>> getMuseumByCity(@PathVariable String city){
        if(!museumService.existsByCity(city))
            return new ResponseEntity(new Message("NO existe"), HttpStatus.NOT_FOUND);
        Iterable<Museum> museumEntities = museumService.findMuseumsByCity(city);
        return new ResponseEntity<>(museumEntities, HttpStatus.OK);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<Museum> getMuseumById(@PathVariable("id") int id){
        if(!museumService.existsById(id))
            return new ResponseEntity(new Message("NO existe"), HttpStatus.NOT_FOUND);
        Museum museumEntity = museumService.findById(id).get();
        return new ResponseEntity<>(museumEntity, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createMuseum(@RequestBody MuseumRequest museumRequest){
        if(StringUtils.isBlank(String.valueOf(museumRequest.getName())))
            return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(museumRequest.getCountry()))
            return new ResponseEntity<>(new Message("El país es obligatorio"), HttpStatus.BAD_REQUEST);
        Museum newMuseum = new Museum();
        newMuseum.setName(museumRequest.getName());
        newMuseum.setCity(museumRequest.getCity());
        newMuseum.setCountry(museumRequest.getCountry());
        newMuseum.setAddress(museumRequest.getAddress());
        museumService.save(newMuseum);
        return new ResponseEntity<>(new Message("Museo registrado y creado"), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMuseum(@PathVariable int id){
        if(!museumService.existsById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        museumService.deleteById(id);
        return new ResponseEntity<>(new Message("Artista eliminado"), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMuseum(@PathVariable int id, @RequestBody MuseumRequest museumRequest){
        if(!museumService.existsById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(String.valueOf(museumRequest.getName())))
            return new ResponseEntity<>(new Message("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(museumRequest.getCountry()))
            return new ResponseEntity<>(new Message("El tipo de movimiento es obligatario"), HttpStatus.BAD_REQUEST);

        Museum savedMuseum = museumService.findById(id).get();
        savedMuseum.setName(museumRequest.getName());
        savedMuseum.setCity(museumRequest.getCity());
        savedMuseum.setCountry(museumRequest.getCountry());
        savedMuseum.setAddress(museumRequest.getAddress());
        museumService.save(savedMuseum);
        return new ResponseEntity<>(new Message("Artista actualizado"), HttpStatus.OK);
    }

}
