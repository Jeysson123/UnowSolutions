package com.unowsolutions.common.repositories;
import com.unowsolutions.common.entitys.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Esta clase es el repositorio que contiene todos los metodos, para hacer las transacciones a la base de datos.
 * Extiende de JpaRepository para reducir codigo de consulta.
 * Hace un casteo al Entity que vamos a utilizar en este caso Vehicle, definido por el tipo Long como identificador
 * @author Robert Jeysson Nin Urena
 */

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> { }
