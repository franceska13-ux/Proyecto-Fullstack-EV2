package com.Hotel.Hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel.Hotel.DTO.HabitacionDTO;
import com.Hotel.Hotel.model.Habitacion;
import com.Hotel.Hotel.repository.HabitacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitacionService {

    @Autowired
    public HabitacionRepository habitacionRepository;

    public List<HabitacionDTO> obtenerTodo() {
        return habitacionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public HabitacionDTO buscarPorId(Integer id_habitacion) {
        Habitacion habitacion = habitacionRepository.findById(id_habitacion)
                .orElseThrow(() -> new RuntimeException("La habitacion no existe"));
        return convertirADTO(habitacion); 
    }

    public Habitacion guardarHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public String eliminar(Integer id) {
        try {
            Habitacion habitacion = habitacionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La habitacion con ID " + id + " no existe."));
            habitacionRepository.delete(habitacion);
            return "La habitacion " + habitacion.getNumero() + "' ha sido retirado del hotel exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }   
    
    public List<HabitacionDTO> buscarPorEstado(String estado) {
        return habitacionRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .toList();
    }

    private HabitacionDTO convertirADTO(Habitacion habitacion) {
        HabitacionDTO dto = new HabitacionDTO();
        dto.setEstado(habitacion.getEstado());
        dto.setId_Habitacion(habitacion.getIdHabitacion());
        dto.setNumero(habitacion.getNumero());

        if (habitacion.getHotel() != null) {
            dto.setID_Hotel(habitacion.getHotel().getId_hotel());
        } else {
            dto.setID_Hotel(null);
        }

        return dto;
    }
}
