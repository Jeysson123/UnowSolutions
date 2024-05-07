package com.unowsolutions.common;
import com.unowsolutions.common.entitys.Vehicle;
import com.unowsolutions.common.services.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase testing, encargada de validar la integracion de la data.
 * Simula los metodos que son consumidos desde el entry point (controller)
 * @author Robert Jeysson Nin Urena
 */
@SpringBootTest
public class VehicleTest {
    @Autowired
    private CommonService<Vehicle> commonService;

    @Test
    public void ListTest() throws Exception {
        assertNotNull(commonService.List());
    }

    @Test
    public void GetTest() throws Exception {
        assertNotNull(commonService.Get(0L));
    }

    @Test
    public void InsertTest() throws Exception {
        assertTrue(commonService.Add(
                Vehicle.builder()
                .id(0L)
                .brand("Jeep")
                .model("Grand Cheroki")
                .licensePlate("2177 BCA")
                .color("Azul")
                .year("2024")
                .build()));
    }

    @Test
    public void UpdateTest() throws Exception {
        assertTrue(commonService.Update(0L,
                Vehicle.builder()
                .id(0L)
                .brand("Jeep")
                .model("Grand Patron")
                .licensePlate("2177 BCA")
                .color("Gris")
                .year("2024")
                .build()));
    }

    @Test
    public void DeleteTest() throws Exception {
        assertTrue(commonService.Remove(0L));
    }
}
