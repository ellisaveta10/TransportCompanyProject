package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Comparable<Employee>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="type_of_qualification", nullable = false)
    private TypeOfQualification typeOfQualification;

    @Column(name="salary")
    private BigDecimal salary;

    @ManyToOne
    private Company company;

    @OneToMany (mappedBy = "employee")
    private List<Transportation> transportationList;

    public Employee() {
    }

    public Employee(long id, String name, TypeOfQualification typeOfQualification, Company company) {
        this.id = id;
        this.name = name;
        this.typeOfQualification = typeOfQualification;
        this.company = company;
    }

    public Employee(long id, String name, TypeOfQualification typeOfQualification, BigDecimal salary, Company company) {
        this.id = id;
        this.name = name;
        this.typeOfQualification = typeOfQualification;
        this.salary = salary;
        this.company = company;
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

    public TypeOfQualification getTypeOfQualification() {
        return typeOfQualification;
    }

    public void setTypeOfQualification(TypeOfQualification typeOfQualification) {
        this.typeOfQualification = typeOfQualification;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeOfQualification=" + typeOfQualification +
                ", salary=" + salary +
                ", company=" + company +
                '}';
    }

    @Override
    public int compareTo(Employee employee) {
        return this.typeOfQualification.compareTo(employee.typeOfQualification);
    }

    public static Comparator<Employee> CompareByQualification = new Comparator<Employee>() {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.typeOfQualification.compareTo(e2.typeOfQualification);
        }
    };

    public static Comparator<Employee> CompareBySalary = new Comparator<Employee>() {
        @Override
        public int compare(Employee employee1, Employee employee2) {
            return employee1.salary.compareTo(employee2.salary);
        }
    };
}
