package com.unowsolutions.common.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase es utilizada para representar la respuesta de cada solicitud.
 * Contiene codigo de respuesta, msg de respuesta y el objeto devuelto de esta respuesta
 * @author Robert Jeysson Nin Urena
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    private int code;
    private String msg;
    private T data;
}
