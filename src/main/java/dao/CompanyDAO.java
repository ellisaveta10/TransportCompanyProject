package dao;

import configuration.SessionFactoryUtil;
import dto.EmployeeDTO;
import dto.TransportationDTO;
import dto.VehicleDTO;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyDAO {

    public static void saveCompany(Company company){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
        }
    }

    public static void saveCompanies(List<Company> companyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companyList.stream().forEach((company -> session.save(company)));
            transaction.commit();
        }
    }

    public static void saveOrUpdateCompany(Company company){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(company);
            transaction.commit();
        }
    }

    public static List<Company> readCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Company a", entity.Company.class).getResultList();
        }
    }

    public static Company getCompany(long id) {
        Company company;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static List<EmployeeDTO> getCompanyEmployeesDTO(long id) {
        List<EmployeeDTO> employees;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            "select new dto.EmployeeDTO(e.id, e.name, e.typeOfQualification, e.salary) from Employee e" +
                                    " join e.company c " +
                                    "where c.id = :id",
                            EmployeeDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    public static List<TransportationDTO> getCompanyTransportationsDTO(long id) {
        List<TransportationDTO> transportations;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportations = session.createQuery(
                            "select new dto.TransportationDTO(t.id, t.starting_point, t.ending_point, t.starting_date, t.ending_date, t.typeOfLoad, t.price) from Transportation t" +
                                    " join t.company c " +
                                    "where c.id = :id",
                            TransportationDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return transportations;
    }

    public static List<VehicleDTO> getCompanyVehiclesDTO(long id) {
        List<VehicleDTO> vehicles;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session.createQuery(
                            "select new dto.VehicleDTO(v.id, v.typeOfVehicle) from Vehicle v" +
                                    " join v.company c " +
                                    "where c.id = :id",
                            VehicleDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }

    public static void deleteCompany(Company company) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }

    public static List<Company> sortCompaniesByNameAndIncomes(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.orderBy(cb.asc(root.get("name")));


            Query<Company> query = session.createQuery(cr);
            List<Company> companies = query.getResultList();
            return  companies;
        }
    }
}
