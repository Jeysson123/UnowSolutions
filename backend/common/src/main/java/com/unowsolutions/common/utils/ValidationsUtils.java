package com.unowsolutions.common.utils;
import com.unowsolutions.common.components.CommonComponent;
import com.unowsolutions.common.dtos.FieldForm;
import com.unowsolutions.common.dtos.FieldValidation;
import com.unowsolutions.common.entitys.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Clase encargada de manejar las validaciones de los formularios.
 * @author Robert Jeysson Nin Urena
 */
@Component
public class ValidationsUtils {
    @Autowired
    private CommonComponent commonComponent;
    private final String regexText = "\\b[A-Za-z ]+\\b";
    private final String regexModel = "\\b[A-Za-z0-9 ]+\\b";
    private final String regexLicensePlate = "\\b\\d{4} [A-Z]{3}\\b";
    private final String regexYear = "\\d{4}";
    private Map<String, FieldForm> REGEX_PATTERNS;

    /**
     * Este metodo es ejecutado, luego de que el ContextLoader de spring esta totalmente cargado,
     * Debido a las inyecciones uttilizadas por ejemplo CommonComponent.
     * Su objetivo principal, es llenar el HashMap con las validaciones a utilizar para cada campo del formulario
     * @author Robert Jeysson Nin Urena
     */
    @PostConstruct
    public void FillRegex() throws Exception {
        REGEX_PATTERNS = new HashMap<String, FieldForm>() {{
            put("brand", FieldForm.builder().regex(regexText).msg(commonComponent.InvalidData.replace("{0}", "Marca")
                    .replace("{1}", "#TEXTO#")).build());
            put("model", FieldForm.builder().regex(regexModel).msg(commonComponent.InvalidData.replace("{0}", "Modelo")
                    .replace("{1}", "#TEXTO#")).build());
            put("licensePlate", FieldForm.builder().regex(regexLicensePlate).msg(commonComponent.InvalidData.replace("{0}", "Matricula")
                    .replace("{1}", "#0000 ABC#")).build());
            put("color", FieldForm.builder().regex(regexText).msg(commonComponent.InvalidData.replace("{0}", "Color")
                    .replace("{1}", "#TEXTO#")).build());
            put("year", FieldForm.builder().regex(regexYear).msg(commonComponent.InvalidData.replace("{0}", "Año")
                    .replace("{1}", "#4 DIGITOS (Numericos)#")).build());
        }};
    }
    /**
     * Este metodo valida si el valor introduccido por el usario es correcto segun el regex establecido.
     * @param pattern formato deseado
     * @param input valor ingresado por el usuario
     * @return true si es un formato valido, false si no lo es.
     * @author Robert Jeysson Nin Urena
     */
    public boolean ValidData(String pattern, String input) throws Exception {

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(input);

        return matcher.matches();
    }
    /**
     * Este metodo se encarga de las validaciones propias de spring boot, ejemplo size ().
     * @param BindingResult contenedor de la informacion del campo
     * @return devuelve los errores en caso de ser encontrados.
     * @author Robert Jeysson Nin Urena
     */
    public String HasErrors(BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return errors.toString();
        }

        return "";
    }
    /**
     * Este metodo utiliza instropeccion, para acceder a los attributos de la clase [name, value].
     * Para hacer sus validaciones internas en relacion al regex y demas
     * @param Vehicle contenedor de la informacion del campo
     * @return devuelve un listado , donde se contienen los errores si estos estuvieron presente durante la valicacion.
     * @author Robert Jeysson Nin Urena
     */
    public List<FieldValidation> ValidForm(Vehicle vehicle) {
        List<FieldValidation> FIELDS_FORM = new ArrayList<>();
        Field[] fields = vehicle.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(attribute -> {
            attribute.setAccessible(true);
            try {
                if (!attribute.getName().equals("id")) {
                    FIELDS_FORM.add(FieldValidation.builder()
                            .isValid(ValidData(REGEX_PATTERNS.get(attribute.getName()).getRegex(),
                                    String.valueOf(attribute.get(vehicle))))
                            .msg(REGEX_PATTERNS.get(attribute.getName()).getMsg())
                            .build());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return FIELDS_FORM;
    }
}