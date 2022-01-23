package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    Vehicle vehicle;
    @BeforeEach
    public void setUp(){
        Company company = new Company();
        vehicle = new Vehicle(1, TypeOfVehicle.BUS, company);
    }

    @Test
    public void setId(){
        vehicle.setId(2);
        assertEquals(2, vehicle.getId());
    }

    @Test
    public void getId(){
        assertEquals(1, vehicle.getId());
    }

    @Test
    public void setTypeOfVehicle(){
        vehicle.setTypeOfVehicle(TypeOfVehicle.BUS);
        assertEquals(TypeOfVehicle.BUS, vehicle.getTypeOfVehicle());
    }

    @Test
    public void getTypeOfVehicle(){
        assertEquals(TypeOfVehicle.BUS, vehicle.getTypeOfVehicle());
    }

    @Test
    public void setCompany(){
        vehicle.setCompany(vehicle.getCompany());
        assertEquals(vehicle.getCompany(), vehicle.getCompany());
    }

    @Test
    public void getCompany(){
        assertEquals(vehicle.getCompany(), vehicle.getCompany());
    }
}
