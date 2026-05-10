package com.Hotel.Hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hotel.Hotel.DTO.ServicioDTO;
import com.Hotel.Hotel.model.Servicio;
import com.Hotel.Hotel.repository.ServicioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioService {

    @Autowired
    public ServicioRepository servicioRepository;

    public List<ServicioDTO> obtenerTodo() {
        return servicioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ServicioDTO buscarPorId(Integer id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El servicio no existe"));
        return convertirADTO(servicio);
    }

    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public String eliminar(Integer id) {
        try {
            Servicio servicio = servicioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado."));
            servicioRepository.delete(servicio);
            return "Servicio '" + servicio.getNombre() + "' eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private ServicioDTO convertirADTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId_servicio(servicio.getId_Servicio());
        dto.setNombre(servicio.getNombre());
        dto.setPrecio(servicio.getPrecio());
        return dto;
    }
}
