package com.Hotel.Hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hotel.Hotel.DTO.RegionDTO;
import com.Hotel.Hotel.model.Region;
import com.Hotel.Hotel.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    public RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodo() {
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La region no existe"));
        return convertirADTO(region);
    }

    public Region guardarRegion(Region region) {
        return regionRepository.save(region);
    }

    public String eliminar(Integer id) {
        try {
            Region region = regionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ID " + id + " no existe."));
            regionRepository.delete(region);
            return "Region '" + region.getNombre() + "' eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId_region(region.getId_Region());
        dto.setNombre(region.getNombre());
        return dto;
    }
}
