package entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transportation")
public class Transportation{

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Transportation starting point cannot be blank!")
    @Size(max = 20, message = "Transportation starting point has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Transportation starting point has to start with capital letter!")
    @Column(name = "starting_point", nullable = false)
    private String starting_point;

    @NotBlank(message = "Transportation ending point cannot be blank!")
    @Size(max = 20, message = "Transportation ending point has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Transportation ending point has to start with capital letter!")
    @Column(name = "ending_point", nullable = false)
    private String ending_point;

    @PastOrPresent(message = "Starting date must be in the past or in the present")
    @Column(name = "starting_date")
    private LocalDate starting_date;

    @Column(name = "ending_date")
    private LocalDate ending_date;

    /** вид на товара - хора или стока
     * 1 - хора
     * 2 - стока
     * */
    @Column(name = "type_of_load", nullable = false)
    private long typeOfLoad;

    @Positive
    @DecimalMin(value = "100.00", message = "Price has to be more than or equal to 100.00", inclusive = true)
    @DecimalMax(value = "900.00", message = "Price has to be less than or equal to 900.00")
    @Digits(integer = 3, fraction = 2, message = "Price has to be with 3 digits before and 2 digits after the decimal separator!")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne (fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "transportation")
    private List<Good> goods;

    @OneToMany(mappedBy = "transportation")
    private List<Client> clients;

    @ManyToOne (fetch = FetchType.LAZY)
    private Employee employee;


    public Transportation() {
    }

    public Transportation(long id, String starting_point, String ending_point, LocalDate starting_date,
                          LocalDate ending_date,
                          long typeOfLoad, BigDecimal price, Company company, Employee employee) {
        this.id = id;
        this.starting_point = starting_point;
        this.ending_point = ending_point;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.typeOfLoad = typeOfLoad;
        this.price = price;
        this.company = company;
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStarting_point() {
        return starting_point;
    }

    public void setStarting_point(String starting_point) {
        this.starting_point = starting_point;
    }

    public String getEnding_point() {
        return ending_point;
    }

    public void setEnding_point(String ending_point) {
        this.ending_point = ending_point;
    }

    public LocalDate getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(LocalDate starting_date) {
        this.starting_date = starting_date;
    }

    public LocalDate getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(LocalDate ending_date) {
        this.ending_date = ending_date;
    }

    public long getTypeOfLoad() {
        return typeOfLoad;
    }

    public void setTypeOfLoad(long typeOfLoad) {
        this.typeOfLoad = typeOfLoad;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", starting_point='" + starting_point + '\'' +
                ", ending_point='" + ending_point + '\'' +
                ", starting_date=" + starting_date +
                ", ending_date=" + ending_date +
                ", typeOfLoad=" + typeOfLoad +
                ", price=" + price +
                '}';
    }

}
