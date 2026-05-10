package com.Hotel.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel.Hotel.DTO.ComunaDTO;
import com.Hotel.Hotel.model.Comuna;
import com.Hotel.Hotel.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comuna")
public class ComunaController {

    @Autowired
    public ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> todasLasComunas() {
        List<ComunaDTO> comunas = comunaService.obtenerTodo();
        if (comunas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comunas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            ComunaDTO comuna = comunaService.buscarPorId(id);
            return new ResponseEntity<>(comuna, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comuna> agregarComuna(@RequestBody Comuna comuna) {
        try {
            Comuna guardada = comunaService.guardarComuna(comuna);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarComuna(@PathVariable Integer id) {
        String resultado = comunaService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
