package com.Hotel.Hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hotel.Hotel.DTO.ComunaDTO;
import com.Hotel.Hotel.model.Comuna;
import com.Hotel.Hotel.repository.ComunaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    public ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodo() {
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La comuna no existe"));
        return convertirADTO(comuna);
    }

    public Comuna guardarComuna(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public String eliminar(Integer id) {
        try {
            Comuna comuna = comunaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ID " + id + " no existe."));
            comunaRepository.delete(comuna);
            return "Comuna '" + comuna.getNombre() + "' eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setId_comuna(comuna.getId_Comuna());
        dto.setNombre(comuna.getNombre());
        if (comuna.getRegion() != null) {
            dto.setNombreRegion(comuna.getRegion().getNombre());
        }
        return dto;
    }
}
