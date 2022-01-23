package dao;

import entity.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VehicleDAOTest {

    @Test
    @Order(1)
    public void saveVehicle(){
        Company company = CompanyDAO.getCompany(1);
        Vehicle vehicle = new Vehicle(7, TypeOfVehicle.TRUCK, company);
        VehicleDAO.saveVehicle(vehicle);
    }

    @Test
    @Order(2)
    public void saveVehicles(){
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle = VehicleDAO.getVehicle(1);
        Vehicle vehicle2 = VehicleDAO.getVehicle(2);
        vehicleList.add(vehicle);
        vehicleList.add(vehicle2);

        VehicleDAO.saveVehicles(vehicleList);
    }

    @Test
    @Order(3)
    public void saveOrUpdateVehicle(){
        Company company = CompanyDAO.getCompany(1);
        Vehicle vehicle = new Vehicle(7, TypeOfVehicle.BUS, company);
        VehicleDAO.saveVehicle(vehicle);
    }

    @Test
    @Order(4)
    public void readVehicles(){
        VehicleDAO.readVehicles();
    }

    @Test
    @Order(5)
    public void getVehicle(){
        Company company = CompanyDAO.getCompany(1);
        Vehicle vehicle = new Vehicle(7, TypeOfVehicle.BUS, company);
        VehicleDAO.getVehicle(vehicle.getId());
    }

    @Test
    @Order(6)
    void deleteVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(5);
        VehicleDAO.deleteVehicle(vehicle);
    }
}
