package com.Hotel.Hotel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Hotel.Hotel.model.TipoHabitacion;

@Repository
public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {


    List<TipoHabitacion> findByNombre(String nombre);

    List<TipoHabitacion> findByNombreContainingIgnoreCase(String nombre);

    List<TipoHabitacion> findByPrecioBaseLessThanEqual(Double precioBase);
}
