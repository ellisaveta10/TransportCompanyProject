package dao;

import dto.TransportationDTO;
import entity.Company;
import entity.Employee;
import entity.Transportation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransportationDAOTest {

    @Test
    @Order(1)
    public void saveTransportation(){
        Company company = CompanyDAO.getCompany(1);
        Employee employee = EmployeeDAO.getEmployee(1);
        Transportation transportation = new Transportation(6, "Varna", "Sofia",
                LocalDate.of(2020, 12, 12),
                LocalDate.of(2020, 12, 14),
                1, BigDecimal.valueOf(750.00), company, employee);
        TransportationDAO.saveTransportation(transportation);
    }

    @Test
    @Order(2)
    public void saveTransportations(){
        List<Transportation> transportationList = new ArrayList<>();
        Transportation transportation = TransportationDAO.getTransportation(1);
        Transportation transportation2 = TransportationDAO.getTransportation(2);
        transportationList.add(transportation);
        transportationList.add(transportation2);

        TransportationDAO.saveTransportations(transportationList);
    }

    @Test
    @Order(3)
    public void saveOrUpdateTransportation(){
        Company company = CompanyDAO.getCompany(1);
        Employee employee = EmployeeDAO.getEmployee(1);
        Transportation transportation = new Transportation(6, "Burgas", "Sofia",
                LocalDate.of(2020, 12, 12),
                LocalDate.of(2020, 12, 14),
                1, BigDecimal.valueOf(750.00), company, employee);
        TransportationDAO.saveOrUpdateTransportation(transportation);
    }

    @Test
    @Order(4)
    public void readTransportations(){
        TransportationDAO.readTransportations();
    }

    @Test
    @Order(5)
    public void getTransportation(){
        Company company = CompanyDAO.getCompany(1);
        Employee employee = EmployeeDAO.getEmployee(1);
        Transportation transportation = new Transportation(6, "Varna", "Sofia",
                LocalDate.of(2020, 12, 12),
                LocalDate.of(2020, 12, 14),
                1, BigDecimal.valueOf(750.00), company, employee);
        TransportationDAO.getTransportation(transportation.getId());
    }

    @Test
    @Order(6)
    void deleteTransportation(){
        Transportation transportation = new Transportation();
        transportation.setId(6);
        TransportationDAO.deleteTransportation(transportation);
    }

    @Test
    @Order(7)
    public void sumOfTransportationsForOneCompany(){

        Company company = new Company();
        company.setId(1);

        List<TransportationDTO> transportationDAOList = CompanyDAO.getCompanyTransportationsDTO(1);
        BigDecimal price = BigDecimal.valueOf(0.0);
        for(TransportationDTO tr: transportationDAOList){
            price = price.add(tr.getPrice());
        }

        assertEquals(price, TransportationDAO.sumOfTransportationsForOneCompany(company));
    }

    @Test
    @Order(8)
    public void getNumberOfTransportationsByCompany(){
        Company company = new Company();
        company.setId(1);

        List<TransportationDTO> transportationDAOList = CompanyDAO.getCompanyTransportationsDTO(1);
        int number;
        number = transportationDAOList.size();
        assertEquals(number, TransportationDAO.getNumberOfTransportationsByCompany(company));

    }

    @Test
    @Order(9)
    public void listOfEmployeesAndTheirTransportations(){
        Employee employee = new Employee();
        employee.setId(1);

        List<TransportationDTO> transportationDAOList = EmployeeDAO.getEmployeeTransportationsDTO(1);
        int number;
        number = transportationDAOList.size();
        assertEquals(number, TransportationDAO.listOfEmployeesAndTheirTransportations(employee));
    }

    @Test
    @Order(10)
    public void incomesOfTheCompanyFromTo(){
        Company company = new Company();
        company.setId(1);

        List<TransportationDTO> transportationDAOList = CompanyDAO.getCompanyTransportationsDTO(1);
        BigDecimal price = BigDecimal.valueOf(0.0);
        for(TransportationDTO tr: transportationDAOList){
            price = price.add(tr.getPrice());
        }

        assertEquals(price, TransportationDAO.incomesOfTheCompanyFromTo(company));
    }

    @Test
    @Order(11)
    public void incomeForEmployee(){
        Employee employee = new Employee();
        employee.setId(1);

        List<TransportationDTO> transportationDAOList = EmployeeDAO.getEmployeeTransportationsDTO(1);
        BigDecimal price = BigDecimal.valueOf(0.0);
        for(TransportationDTO tr: transportationDAOList){
            price = price.add(tr.getPrice());
        }

        assertEquals(price, TransportationDAO.incomeForEmployee(employee));
    }

}
