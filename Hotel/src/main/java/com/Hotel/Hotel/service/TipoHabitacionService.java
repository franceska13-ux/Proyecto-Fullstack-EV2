package com.Hotel.Hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hotel.Hotel.DTO.TipoHabitacionDTO;
import com.Hotel.Hotel.model.TipoHabitacion;
import com.Hotel.Hotel.repository.TipoHabitacionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoHabitacionService {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    public List<TipoHabitacionDTO> obtenerTodo() {
        return tipoHabitacionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public TipoHabitacionDTO buscarPorId(Integer id) {
        TipoHabitacion tipo = tipoHabitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de habitacion no existe"));
        return convertirADTO(tipo);
    }

    public TipoHabitacionDTO guardarTipo(TipoHabitacion tipo) {
        TipoHabitacion guardado = tipoHabitacionRepository.save(tipo);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id) {
        if (!tipoHabitacionRepository.existsById(id)) {
            return "ID no encontrado.";
        }
        tipoHabitacionRepository.deleteById(id);
        return "Tipo de habitacion eliminado.";
    }

    private TipoHabitacionDTO convertirADTO(TipoHabitacion tipo) {
        if (tipo == null) return null;
        TipoHabitacionDTO dto = new TipoHabitacionDTO();
        
      
        dto.setId_tipo_hab(tipo.getId_Habitacion()); 
        
      
        
        return dto;
    }
}