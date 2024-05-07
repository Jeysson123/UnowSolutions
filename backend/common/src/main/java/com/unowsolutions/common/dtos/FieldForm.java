package com.unowsolutions.common.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase es utilizada para validacion de los formularios.
 * Contiene una expresion regular y un msg final en caso de que no sea un campo valido
 * @author Robert Jeysson Nin Urena
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldForm {
    private String regex;
    private String msg;
}
