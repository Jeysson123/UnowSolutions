package com.unowsolutions.common.services;
import java.util.List;
/**
 * Interfaz que defina todos los metodos que se utilizaran en el API.
 * @author Robert Jeysson Nin Urena
 */
public interface CommonService<T> {
    void ValidateContext(String eT) throws Exception;
    List<T> List() throws Exception;
    T Get(Long id) throws Exception;
    boolean Add(T entity) throws Exception;
    boolean Update(Long id, T entity) throws Exception;
    boolean Remove(Long id) throws Exception;
}

