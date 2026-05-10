package com.Hotel.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel.Hotel.DTO.RegionDTO;
import com.Hotel.Hotel.model.Region;
import com.Hotel.Hotel.service.RegionService;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    public RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> todasLasRegiones() {
        List<RegionDTO> regiones = regionService.obtenerTodo();
        if (regiones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> buscarPorId(@PathVariable Integer id) {
        try {
            RegionDTO region = regionService.buscarPorId(id);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Region> agregarRegion(@RequestBody Region region) {
        try {
            Region guardada = regionService.guardarRegion(region);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegion(@PathVariable Integer id) {
        String resultado = regionService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
