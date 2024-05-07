package com.unowsolutions.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/**
 * Esta clase representa, una exception que puede ocurrir.
 * @author Robert Jeysson Nin Urena
 */
@AllArgsConstructor
@Data
@Builder
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

