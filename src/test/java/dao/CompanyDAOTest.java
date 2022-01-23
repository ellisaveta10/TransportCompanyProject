package dao;

import entity.Company;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyDAOTest {
    @Test
    @Order(1)
    void saveCompany(){
        Company company = new Company(4, "Etap");
        CompanyDAO.saveCompany(company);
    }

    @Test
    @Order(2)
    public void saveCompanies(){
        List<Company> companyList = new ArrayList<>();
        Company company = CompanyDAO.getCompany(1);
        Company company2 = CompanyDAO.getCompany(2);
        companyList.add(company);
        companyList.add(company2);

        CompanyDAO.saveCompanies(companyList);
    }

    @Test
    @Order(3)
    public void saveOrUpdateCompany(){
        Company company = new Company(4, "Biomet");
        CompanyDAO.saveOrUpdateCompany(company);
    }

    @Test
    @Order(4)
    public void readCompanies(){
        CompanyDAO.readCompanies();
    }

    @Test
    @Order(5)
    public void getCompany(){
        Company company = new Company(4, "Etap");
        CompanyDAO.getCompany(company.getId());
    }

    @Test
    @Order(6)
    public void deleteCompany(){
        Company company = new Company();
        company.setId(4);
        CompanyDAO.deleteCompany(company);
    }

}
