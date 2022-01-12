package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company implements Comparable<Company>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Vehicle> vehicleList;

    @OneToMany (mappedBy = "company")
    private Set<Employee> employeeList;

    @ManyToMany
    private Set<Client> clients;

    @OneToMany (mappedBy = "company")
    //@ElementCollection(fetch = EAGER)
    private List<Transportation> transportationList;

    //@Column(name="foundation_date", nullable = false)
    //private LocalDate foundationDate;

    public Company() {
    }

    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(long id, String name, List<Vehicle> vehicleList, Set<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.vehicleList = vehicleList;
        this.employeeList = employeeList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public List<Transportation> getTransportationList() {
        return transportationList;
    }

    public void setTransportationList(List<Transportation> transportationList) {
        this.transportationList = transportationList;
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Company company) {
        return this.name.compareTo(company.name);
    }

    public static Comparator<Company> sortByName = new Comparator<Company>() {
        @Override
        public int compare(Company o1, Company o2) {
            return o1.name.compareTo(o2.name);
        }
    };
}
