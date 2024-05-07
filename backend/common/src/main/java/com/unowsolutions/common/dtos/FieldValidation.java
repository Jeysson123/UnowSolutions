package com.unowsolutions.common.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase es representa los campos individuales de los formularios.
 * Contiene un key que determina si es valido o no y un msg final en caso de que no sea un campo valido
 * @author Robert Jeysson Nin Urena
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidation {
    private Boolean isValid;
    private String msg;
}
