package com.unowsolutions.common.services.impl;
import com.unowsolutions.common.entitys.Vehicle;
import com.unowsolutions.common.enums.CommonEnum;
import com.unowsolutions.common.repositories.VehicleRepository;
import com.unowsolutions.common.services.CommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
 * Clase que representa las implementaciones genericas para nuestras operaciones CRUD (get, add, update, delete).
 * Extiende de la interfaz CommonService quien define los metodos a utilizar.
 * @author Robert Jeysson Nin Urena
 */

@Service
public class CommonServiceImpl<T> implements CommonService<T> {
    private final VehicleRepository vehicleRepository;
    private JpaRepository<T, Long> commonRepository;
    private final String entityType;
    public CommonServiceImpl(VehicleRepository vehicleRepository, @Value("${entity.type}") String entityType) throws Exception {
        this.vehicleRepository = vehicleRepository;
        this.entityType = entityType;
        ValidateContext(this.entityType);
    }


    /**
     * Establece la tabla de la base de datos a utilizar.
     * @param eT tipo de entidad (Vehicle)
     * @author Robert Jeysson Nin Urena
     */
    @Override
    public void ValidateContext(String eT) throws Exception {
        this.commonRepository = null;
        if (CommonEnum.Vehicle.getContext().equals(eT)) {
            this.commonRepository = (JpaRepository<T, Long>) vehicleRepository;
        } else {
            throw new IllegalArgumentException("Unsupported entity type");
        }
    }
    /**
     * Devuelve el listado de vehiculos.
     * @author Robert Jeysson Nin Urena
     */
    @Override
    public List<T> List() throws Exception {

        return commonRepository.findAll();
    }
    /**
     * Devuelve el vehiculo consultado.
     * @param id identificador del vehiculo
     * @author Robert Jeysson Nin Urena
     */
    @Override
    public T Get(Long id) throws Exception {
        return commonRepository.findById(id).orElse(null);
    }
    /**
     * Agrega el vehiculo en especifico.
     * @param entity tipo de entidad (Vehicle)
     * @author Robert Jeysson Nin Urena
     */
    @Override
    public boolean Add(T entity) throws Exception {
        return commonRepository.save(entity) != null;
    }
    /**
     * Actualiza el vehiculo.
     * @param id identificador del vehiculo
     * @param updatedEntity nuevos datos a insertar
     * @author Robert Jeysson Nin Urena
     */
    @Override
    public boolean Update(Long id, T updatedEntity) throws Exception {
        boolean updated = false;
        Vehicle vehicleObject;
        Optional<T> rowExist = commonRepository.findById(id);
        if (rowExist.isPresent()) {
            if (updatedEntity instanceof Vehicle) {
                vehicleObject = (Vehicle) updatedEntity;
                vehicleObject.setId(id);
                commonRepository.save((T) vehicleObject);
            } else {
                throw new IllegalArgumentException("Unsupported entity type");
            }
            updated = true;
        }
        return updated;
    }
    /**
     * Elimina el vehiculo.
     * @param id identificador del vehiculo
     * @author Robert Jeysson Nin Urena
     */
    @Override
    public boolean Remove(Long id) throws Exception {
        if (commonRepository.existsById(id)) {
            commonRepository.deleteById(id);
            return !commonRepository.existsById(id);
        }
        return false;
    }
}
