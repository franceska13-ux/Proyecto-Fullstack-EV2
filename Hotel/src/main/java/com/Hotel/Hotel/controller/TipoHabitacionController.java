package com.Hotel.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel.Hotel.DTO.TipoHabitacionDTO;
import com.Hotel.Hotel.model.TipoHabitacion;
import com.Hotel.Hotel.service.TipoHabitacionService;

@RestController
@RequestMapping("/api/v1/tipo-habitacion")
@CrossOrigin(origins = "*") 
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionService tipoHabitacionService; 

    @GetMapping
    public ResponseEntity<List<TipoHabitacionDTO>> todosLosTipos() {
        List<TipoHabitacionDTO> tipos = tipoHabitacionService.obtenerTodo();
        if (tipos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            TipoHabitacionDTO tipo = tipoHabitacionService.buscarPorId(id);
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } catch (RuntimeException e) {
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarTipoHabitacion(@RequestBody TipoHabitacion tipo) {
        try {
            TipoHabitacionDTO guardado = tipoHabitacionService.guardarTipo(tipo);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTipoHabitacion(@PathVariable Integer id) {
        String resultado = tipoHabitacionService.eliminar(id);
      
        if (resultado.toLowerCase().contains("eliminado")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
