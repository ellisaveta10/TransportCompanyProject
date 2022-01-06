package dao;

import configuration.SessionFactoryUtil;
import entity.Company;;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.stream.Stream;

public class CompanyDAO {

    /** ===== CRUD =====*/
    /**C - create */
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
            companyList.forEach(session::save);
            transaction.commit();
        }
    }

    public static List<Company> readCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Company a", Company.class).getResultList();
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

    public static void deleteCompany(Company company) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }

    public static void sortCompaniesByNameAndIncomes(List<Company> companyList){
        Stream<Company> companyStream = companyList.stream();
        companyStream
                .sorted(Company.sortByName)
                .forEach(System.out::println);
    }
}
