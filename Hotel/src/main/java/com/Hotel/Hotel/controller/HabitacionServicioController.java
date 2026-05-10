package com.Hotel.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel.Hotel.DTO.HabitacionServicioDTO;
import com.Hotel.Hotel.model.HabitacionServicio;
import com.Hotel.Hotel.service.HabitacionServicioService;

@RestController
@RequestMapping("/api/v1/habitacion-servicio")
public class HabitacionServicioController {

    @Autowired
    public HabitacionServicioService habitacionServicioService;

    @GetMapping
    public ResponseEntity<List<HabitacionServicioDTO>> todosLosHabitacionServicios() {
        List<HabitacionServicioDTO> lista = habitacionServicioService.obtenerTodo();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionServicioDTO> buscarPorId(@PathVariable Integer id) {
        try {
            HabitacionServicioDTO dto = habitacionServicioService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<HabitacionServicio> agregarHabitacionServicio(@RequestBody HabitacionServicio hs) {
        try {
            HabitacionServicio guardado = habitacionServicioService.guardarHabitacionServicio(hs);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacionServicio(@PathVariable Integer id) {
        String resultado = habitacionServicioService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
