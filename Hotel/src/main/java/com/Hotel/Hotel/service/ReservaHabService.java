package com.Hotel.Hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel.Hotel.DTO.ReservaHabDTO;
import com.Hotel.Hotel.model.ReservaHabitacion;
import com.Hotel.Hotel.repository.ReservaHabitacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaHabService {

    @Autowired
    public ReservaHabitacionRepository reservaHabRepository;
    
    public List<ReservaHabDTO> obtenerTodos() {
        return reservaHabRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ReservaHabDTO buscarPorId(Integer Id_Reserva_Hab) {
        ReservaHabitacion reservaHab = reservaHabRepository.findById(Id_Reserva_Hab)
                .orElseThrow(() -> new RuntimeException("La reserva de la habitacion no existe"));
            return convertirADTO(reservaHab);
    }

    public ReservaHabitacion guardarReservaHab(ReservaHabitacion reservaHab) {
        return reservaHabRepository.save(reservaHab);
    }

    public String eliminar(Integer id) {
        try {
            ReservaHabitacion reservaHab = reservaHabRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La Reserva de Habitacion con ID " + id + " no existe."));
            reservaHabRepository.delete(reservaHab);
            return "La reserva de habitacion " + reservaHab.getHabitacion() + "' ha sido retirado de la reserva exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }    

    public List<ReservaHabDTO> buscarPorPrecioNoche(Integer precioNoche) {
        List<ReservaHabitacion> reservas = reservaHabRepository.findByPrecioNoche(precioNoche);
        return reservas.stream()
                .map(this::convertirADTO)
                .toList();
    }

    private ReservaHabDTO convertirADTO(ReservaHabitacion reservaHab) {
        ReservaHabDTO dto = new ReservaHabDTO();
        dto.setId__Reserva_Hab(reservaHab.getIdReservaHab());
        dto.setPrecio_noche(reservaHab.getPrecioNoche());

        return dto;
    }
}
