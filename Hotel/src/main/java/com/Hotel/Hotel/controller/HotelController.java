package com.Hotel.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel.Hotel.DTO.HotelDTO;
import com.Hotel.Hotel.model.Hotel;
import com.Hotel.Hotel.service.HotelService;

@RestController
@RequestMapping("/api/v1/hotel")
@CrossOrigin(origins = "*") 
public class HotelController {

    @Autowired
    private HotelService hotelService; 

    @GetMapping
    public ResponseEntity<List<HotelDTO>> todosLosHoteles() {
        List<HotelDTO> hoteles = hotelService.obtenerTodo();
        if (hoteles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hoteles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            HotelDTO hotel = hotelService.buscarPorId(id);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (RuntimeException e) {
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarHotel(@RequestBody Hotel hotel) {
        try {
            
            HotelDTO guardado = hotelService.guardarHotel(hotel);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el hotel: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHotel(@PathVariable Integer id) {
        String resultado = hotelService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
