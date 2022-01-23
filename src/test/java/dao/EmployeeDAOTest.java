package dao;

import entity.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTest {
    @Test
    @Order(1)
    public void saveEmployee(){
        Company company = new Company();
        company.setId(2);
        Employee employee = new Employee(6, "Sarra", TypeOfQualification.SPECIAL_LOAD,
                BigDecimal.valueOf(1200.00), company);
        EmployeeDAO.saveEmployee(employee);
    }

    @Test
    @Order(2)
    public void saveEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = EmployeeDAO.getEmployee(2);
        Employee employee2 = EmployeeDAO.getEmployee(3);
        employeeList.add(employee);
        employeeList.add(employee2);

        EmployeeDAO.saveEmployees(employeeList);
    }

    @Test
    @Order(3)
    public void saveOrUpdateEmployee(){
        Company company = new Company();
        company.setId(2);
        Employee employee = new Employee(6, "Anna", TypeOfQualification.SPECIAL_LOAD,
                BigDecimal.valueOf(1200.00), company);
        EmployeeDAO.saveOrUpdateEmployee(employee);
    }

    @Test
    @Order(4)
    public void readEmployees(){
        EmployeeDAO.readEmployees();
    }

    @Test
    @Order(5)
    public void getEmployee(){
        EmployeeDAO.getEmployee(6);
    }

    @Test
    @Order(6)
    void deleteClient(){
        Client client = new Client();
        client.setId(5);
        ClientDAO.deleteClient(client);
    }
}
