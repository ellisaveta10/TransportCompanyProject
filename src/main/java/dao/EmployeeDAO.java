package dao;

import configuration.SessionFactoryUtil;
import dto.EmployeeDTO;
import dto.TransportationDTO;
import entity.Company;
import entity.Employee;
import entity.Transportation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeDAO {
    public static void saveEmployee(Employee employee){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void saveEmployees(List<Employee> employeeList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeList.stream().forEach(employee -> session.save(employee));
            transaction.commit();
        }
    }

    public static void saveOrUpdateEmployee(Employee employee){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static List<Employee> readEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
        }
    }

    public static Employee getEmployee(long id) {
        Employee employee;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static List<TransportationDTO> getEmployeeTransportationsDTO(long id) {
        List<TransportationDTO> transportations;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportations = session.createQuery(
                            "select new dto.TransportationDTO(t.id, t.starting_point, t.ending_point, t.starting_date, t.ending_date, t.typeOfLoad, t.price) from Transportation t" +
                                    " join t.employee e " +
                                    "where e.id = :id",
                            TransportationDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return transportations;
    }

    public static void deleteEmployee(Employee employee) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    /** 7-b: sort by qualification and salary */

    /*public static void sortEmployeeByQualificationAndSalary(List<Employee> employeeList){
        Stream<Employee> employeeStream = employeeList.stream();
        employeeStream
                .sorted(Employee.CompareByQualification
                        .thenComparing(Employee.CompareBySalary.reversed()))
                .forEach(System.out::println);
    }*/

    public static List<Employee> sortEmployeeByQualificationAndSalary(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.orderBy(cb.asc(root.get("typeOfQualification")), cb.desc(root.get("salary")));


            Query<Employee> query = session.createQuery(cr);
            List<Employee> employeeList = query.getResultList();
            return  employeeList;
        }
    }
}
