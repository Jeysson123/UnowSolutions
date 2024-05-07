package com.unowsolutions.common.enums;

import lombok.Getter;
/**
 * Esta clase es utilizada para referenciar el contexto de base de datos que se quiere utilizar.
 * @author Robert Jeysson Nin Urena
 */
@Getter
public enum CommonEnum {
    Vehicle("Vehicle");
    private String context;
    CommonEnum(String context) {this.context = context;}
    public String getContext() {return context;}
    public void setContext(String context) {this.context = context;}
}
