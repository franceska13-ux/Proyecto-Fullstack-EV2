package com.Hotel.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel.Hotel.DTO.ServicioDTO;
import com.Hotel.Hotel.model.Servicio;
import com.Hotel.Hotel.service.ServicioService;

@RestController
@RequestMapping("/api/v1/servicio")
public class ServicioController {

    @Autowired
    public ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> todosLosServicios() {
        List<ServicioDTO> servicios = servicioService.obtenerTodo();
        if (servicios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> buscarPorId(@PathVariable Integer id) {
        try {
            ServicioDTO servicio = servicioService.buscarPorId(id);
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Servicio> agregarServicio(@RequestBody Servicio servicio) {
        try {
            Servicio guardado = servicioService.guardarServicio(servicio);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Integer id) {
        String resultado = servicioService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
