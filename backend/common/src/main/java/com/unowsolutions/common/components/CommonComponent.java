package com.unowsolutions.common.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * Esta clase representa un componente.
 * Tiene los mensajes comunes de la aplicacion.
 * @author Robert Jeysson Nin Urena
 */
@Component
public class CommonComponent {
    @Value("${emptyList}")
    public String EmptyList;
    @Value("${listFound}")
    public String ListFound;
    @Value("${inserted}")
    public String SuccessRegister;
    @Value("${notInserted}")
    public String ErrorRegister;
    @Value("${found}")
    public String Found;
    @Value("${notFound}")
    public String NotFound;
    @Value("${updated}")
    public String Updated;
    @Value("${entity.type}")
    public String EntityType;
    @Value("${invalid.data}")
    public String InvalidData;
    @Value("${apiProblem}")
    public String ApiProblem;
}
