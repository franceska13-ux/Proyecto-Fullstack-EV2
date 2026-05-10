package com.Hotel.Hotel.DTO;

import lombok.Data;

@Data
public class HabitacionServicioDTO {
    private Integer id_hab_servicio;
    private Integer numeroHabitacion;
    private String nombreServicio;
    private Double precioServicio;
}
