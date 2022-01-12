package dao;

import configuration.SessionFactoryUtil;
import entity.Company;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static void deleteEmployee(Employee employee) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    /** 7-b: sort by qualification and salary */

    public static void sortEmployeeByQualificationAndSalary(List<Employee> employeeList){
        Stream<Employee> employeeStream = employeeList.stream();
        employeeStream
                .sorted(Employee.CompareByQualification
                        .thenComparing(Employee.CompareBySalary.reversed()))
                .forEach(System.out::println);
    }
}
