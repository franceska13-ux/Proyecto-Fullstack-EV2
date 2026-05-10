package com.Hotel.Hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comuna")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comuna")
    private Integer Id_Comuna;

    @ManyToOne
    @JoinColumn(name = "ID_region", nullable = false)
    private Region region;

    @NotBlank(message = "El nombre de la comuna no puede estar en blanco")
    @Column(nullable = false, length = 100)
    private String nombre;
}