package com.unowsolutions.common.entitys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Esta clase representa la relacion entre la tabla y mi lenguaje de programacion.
 * Contiene un id, marca, modelo, matricula, color y año.
 * @author Robert Jeysson Nin Urena
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    @NotEmpty(message = "Marca requerida")
    @Size(min = 3, max = 20, message = "Marca este campo debe contener entre 3 y 20 caracteres.")
    private String brand;

    @NotEmpty(message = "Modelo requerido")
    @Size(min = 3, max = 40, message = "Modelo este campo debe contener entre 3 y 40 caracteres.")
    @Column(name = "model")
    private String model;

    @Column(name = "licensePlate")
    @NotEmpty(message = "Matricula requerida")
    @Size(min = 3, max = 20, message = "Matricula este campo debe contener entre 3 y 20 caracteres.")
    private String licensePlate;

    @Column(name = "color")
    @NotEmpty(message = "Color requerido")
    @Size(min = 3, max = 20, message = "Color este campo debe contener entre 3 y 20 caracteres.")
    private String color;

    @Column(name = "year")
    @NotEmpty(message = "Año requerido")
    @Size(min = 4, max = 8, message = "Año este campo debe contener entre 4 y 8 caracteres.")
    private String year;

}
