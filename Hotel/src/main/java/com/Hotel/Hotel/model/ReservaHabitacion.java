package com.Hotel.Hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Reserva_Habitacion")
public class ReservaHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Reserva_Hab")
    private Integer idReservaHab;

    @NotNull(message = "El precio por noche no puede ser nulo")
    @Column(nullable = false)
    private Integer precioNoche; 

    @ManyToOne 
    @JoinColumn(name = "ID_reserva", nullable = false) 
    private Reservas reserva; 

    @ManyToOne 
    @JoinColumn(name = "ID_Habitacion", nullable = false) 
    private Habitacion habitacion;


}
