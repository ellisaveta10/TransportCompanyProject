package dao;

import configuration.SessionFactoryUtil;
import dto.ClientDTO;
import dto.EmployeeDTO;
import dto.GoodDTO;
import entity.Company;
import entity.Employee;
import entity.Transportation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class TransportationDAO {
    public static void saveTransportation(Transportation transportation) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transportation);
            transaction.commit();
        }
    }

    public static void saveTransportations(List<Transportation> transportationList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportationList.stream().forEach(transportation -> session.save(transportation));
            transaction.commit();
        }
    }

    public static void saveOrUpdateCompany(Transportation transportation){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(transportation);
            transaction.commit();
        }
    }

    public static List<Transportation> readTransportations() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Transportation a", Transportation.class).getResultList();
        }
    }

    public static Transportation getTransportation(long id) {
        Transportation transportation;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportation = session.get(Transportation.class, id);
            transaction.commit();
        }
        return transportation;
    }

    public static List<ClientDTO> getTransportationClientsDTO(long id) {
        List<ClientDTO> clients;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session.createQuery(
                            "select new dto.ClientDTO(c.id, c.name) from Client c" +
                                    " join c.transportation t " +
                                    "where t.id = :id",
                            ClientDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return clients;
    }

    public static List<GoodDTO> getTransportationGoodDTO(long id) {
        List<GoodDTO> goods;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goods = session.createQuery(
                            "select new dto.GoodDTO(g.id, g.name, g.weight) from Good g" +
                                    " join g.transportation t " +
                                    "where t.id = :id",
                            GoodDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return goods;
    }

    public static void deleteTransportation(Transportation transportation) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(transportation);
            transaction.commit();
        }
    }

    /** 7-c: sort by destination */
    /*public static void sortTransportationOrderByDestination(List<Transportation> transportationList) {
        Stream<Transportation> transportationStream = transportationList.stream();
        transportationStream.sorted(Transportation.CompareByDestinationStartingPoint.
                        thenComparing(Transportation.CompareByDestinationEndingPoint))
                .forEach(System.out::println);
    }*/

    public static List<Transportation> sortTransportationOrderByDestination(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transportation> cr = cb.createQuery(Transportation.class);
            Root<Transportation> root = cr.from(Transportation.class);
            cr.orderBy(cb.asc(root.get("starting_point")), cb.asc(root.get("ending_point")));


            Query<Transportation> query = session.createQuery(cr);
            List<Transportation> transportationList = query.getResultList();
            return  transportationList;
        }
    }


    public static Long getNumberOfTransportationsByCompany(Company company){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);

            Root<Transportation> root = query.from(Transportation.class);

            query.select(cb.count(root)).where(cb.equal(root.get("company"), company));

            Query<Long> qr = session.createQuery(query);
            Long count = qr.getSingleResult();
            return count;
        }
    }

    public static BigDecimal sumOfTransportationsForOneCompany(Company company) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);

            Root<Transportation> root = query.from(Transportation.class);

            query.where(cb.equal(root.get("company"), company)).select(cb.sum(root.get("price"))).getSelection();
            Query<BigDecimal> qr = session.createQuery(query);
            BigDecimal sum = qr.getSingleResult();
            return sum;
        }
    }

    public static Long listOfEmployeesAndTheirTransportations(Employee employee) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);

            Root<Transportation> root = query.from(Transportation.class);

            query.select(cb.count(root)).where(cb.equal(root.get("employee"), employee));

            Query<Long> qr = session.createQuery(query);
            Long numberOfTransportations = qr.getSingleResult();
            return numberOfTransportations;
        }
    }

    public static BigDecimal incomesOfTheCompanyFromTo(Company company){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> cr = cb.createQuery(BigDecimal.class);
            Root<Transportation> root = cr.from(Transportation.class);

            cr.select(cb.sum(root.get("price"))).where(cb.equal(root.get("company"), company));
            Query<BigDecimal> query = session.createQuery(cr);
            BigDecimal income = query.getSingleResult();
            return income;
        }
    }


    public static BigDecimal incomeForEmployee(Employee employee){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);

            Root<Transportation> root = query.from(Transportation.class);
            query.select(cb.sum(root.get("price"))).where(cb.equal(root.get("employee"), employee));

            Query<BigDecimal> qr = session.createQuery(query);
            BigDecimal incomes = qr.getSingleResult();
            return  incomes;
        }
    }
}


