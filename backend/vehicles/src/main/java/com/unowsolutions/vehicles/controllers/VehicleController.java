package com.unowsolutions.vehicles.controllers;
import com.unowsolutions.common.components.CommonComponent;
import com.unowsolutions.common.dtos.CustomException;
import com.unowsolutions.common.dtos.ResponseWrapper;
import com.unowsolutions.common.entitys.Vehicle;
import com.unowsolutions.common.services.CommonService;
import com.unowsolutions.common.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Clase encargada de recibir los request de nuesta API.
 * Inyecta los diferentes servicios, repositorios, bean y componentes que son necesarios para la ejecucion del mismo
 * @author Robert Jeysson Nin Urena
 */
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/unowsoltions/vehicles")
public class VehicleController {
    private final CommonComponent commonComponent;
    private final CommonService<Vehicle> commonService;
    private final ValidationsUtils validationUtils;
    private final String entityType;
    /**
     * Inyeccion de los diferentes objetos principales.
     * @author Robert Jeysson Nin Urena
     */
    public VehicleController(CommonComponent commonComponent, CommonService<Vehicle> commonService,
                             ValidationsUtils validationUtils, @Value("${entity.type}") String entityType) {
        this.commonComponent = commonComponent;
        this.commonService = commonService;
        this.validationUtils = validationUtils;
        this.entityType = entityType;
    }

    @GetMapping("/get")
    public ResponseWrapper<Object> ListVehicles() {
        try{
            List<Vehicle> listVehicles = commonService.List();
            if(listVehicles.size() == 0) {
                return ResponseWrapper.builder().code(HttpStatus.OK.value())
                        .msg(commonComponent.EmptyList)
                        .build();
            }
            listVehicles.sort(Comparator.comparing(Vehicle::getYear));
            return ResponseWrapper.builder().code(HttpStatus.OK.value()).msg(commonComponent.ListFound).data(listVehicles).build();
        }
        catch (Exception e){
            throw new CustomException(String.format(commonComponent.ApiProblem, e.getMessage()));
        }
    }

    @GetMapping("/find")
    public ResponseWrapper<Object> GetVehicle(@RequestParam Long id) {

        try{
            return ResponseWrapper.builder().code(HttpStatus.OK.value()).msg(commonService.Get(id) != null ? commonComponent.Found
                    : commonComponent.NotFound).data(commonService.Get(id)).build();
        }
        catch (Exception e){
            throw new CustomException(String.format(commonComponent.ApiProblem, e.getMessage()));
        }
    }

    @PostMapping("/insert")
    public ResponseWrapper<Object> InsertVehicle(@Valid @RequestBody Vehicle vehicle, BindingResult bindingResult) {

        try{
            boolean formatError = validationUtils.ValidForm(vehicle).stream().anyMatch(field -> !field.getIsValid());
            boolean validationError = !validationUtils.HasErrors(bindingResult).isEmpty();
            return ResponseWrapper.builder().code(HttpStatus.OK.value()).msg(formatError || validationError ?
                                commonComponent.ErrorRegister.replace("{0}", "Vehiculo") : null)
                                .data(formatError ? validationUtils.ValidForm(vehicle).stream()
                                .filter(field -> !field.getIsValid()).collect(Collectors.toList())
                                : validationError ? validationUtils.HasErrors(bindingResult) : commonService.Add(vehicle)
                                ? commonComponent.SuccessRegister : commonComponent.ErrorRegister.replace("{0}",
                                        "Vehiculo")).build();
        }
        catch (Exception e){
            throw new CustomException(String.format(commonComponent.ApiProblem, e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseWrapper<Object> UpdateVehicle(@PathVariable Long id, @RequestBody @Valid Vehicle vehicle, BindingResult bindingResult) {

        try{
            boolean formatError = validationUtils.ValidForm(vehicle).stream().anyMatch(field -> !field.getIsValid());
            boolean validationError = !validationUtils.HasErrors(bindingResult).isEmpty();
            return ResponseWrapper.builder().code(HttpStatus.OK.value()).msg(formatError || validationError ?
                            commonComponent.Updated.replace("{0}", "Error, ").replace("{1}",
                                    "actualizando vehiculo") : null).data(formatError ? validationUtils.ValidForm
                            (vehicle).stream().filter(field -> !field.getIsValid()).collect(Collectors.toList()) : validationError ?
                            validationUtils.HasErrors(bindingResult) : commonService.Update(id, vehicle)
                                    ? commonComponent.Updated.replace("{0}", "Vehiculo,")
                                    .replace("{1}", "actualizado ") : commonComponent.Updated
                                    .replace("{0}", "Error, ").replace("{1}",
                            "actualizando vehiculo")).build();
        }
        catch (Exception e){
            throw new CustomException(String.format(commonComponent.ApiProblem, e.getMessage()));
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseWrapper<Object> RemoveVehicle(@PathVariable Long id) {

        try {
            return ResponseWrapper.builder().code(HttpStatus.OK.value()).msg(commonService.Get(id) != null ?
                    commonComponent.Found : commonComponent.NotFound).data(commonService.Remove(id)
                    ? commonComponent.Updated.replace("{0}", "Vehiculo").replace("{1}",
                    " eliminado") : commonComponent.Updated.replace("{0}", "Error, ")
                    .replace("{1}", "eliminando ")).build();
        }
        catch (Exception e){
            throw new CustomException(String.format(commonComponent.ApiProblem, e.getMessage()));
        }
    }

}
