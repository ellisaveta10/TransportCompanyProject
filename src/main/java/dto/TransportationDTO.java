package dto;

import entity.Company;
import entity.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransportationDTO {
    private long id;

    private String starting_point;

    private String ending_point;

    private LocalDate starting_date;

    private LocalDate ending_date;

    private long typeOfLoad;

    private BigDecimal price;

    private Company company;

    private Employee employee;

    public TransportationDTO() {
    }

    public TransportationDTO(long id, String starting_point, String ending_point,
                             LocalDate starting_date, LocalDate ending_date, long typeOfLoad,
                             BigDecimal price) {
        this.id = id;
        this.starting_point = starting_point;
        this.ending_point = ending_point;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.typeOfLoad = typeOfLoad;
        this.price = price;
    }

    public TransportationDTO(long id, String starting_point, String ending_point, LocalDate starting_date, LocalDate ending_date, long typeOfLoad, BigDecimal price, Company company, Employee employee) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TransportationDTO{" +
                "id=" + id +
                ", starting_point='" + starting_point + '\'' +
                ", ending_point='" + ending_point + '\'' +
                ", starting_date=" + starting_date +
                ", ending_date=" + ending_date +
                ", typeOfLoad=" + typeOfLoad +
                ", price=" + price +
                ", company=" + company +
                ", employee=" + employee +
                '}';
    }
}
