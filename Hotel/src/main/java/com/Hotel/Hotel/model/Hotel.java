package com.Hotel.Hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Hotel")
    private Integer Id_Hotel;

    @NotBlank(message = "El nombre del hotel no puede estar en blanco")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La dirección no puede estar en blanco")
    @Column(nullable = false, length = 200)
    private String direccion;
}