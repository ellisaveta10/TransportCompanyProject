package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class TransportationTest {

    Transportation transportation;
    @BeforeEach
    public void setUp(){
        Company company = new Company();
        Employee employee = new Employee();
        transportation= new Transportation(1, "Varna", "Sofia",
                LocalDate.of(2020, 12, 12),
                LocalDate.of(2020, 12, 14),
                1, BigDecimal.valueOf(750.00), company, employee);
    }

    @Test
    public void setId(){
        transportation.setId(2);
        assertEquals(2, transportation.getId());
    }

    @Test
    public void getId(){
        assertEquals(1, transportation.getId());
    }

    @Test
    public void setStarting_point(){
        transportation.setStarting_point("Varna");
        assertEquals("Varna", transportation.getStarting_point());
    }

    @Test
    public void getStarting_point(){
        assertEquals("Varna", transportation.getStarting_point());
    }

    @Test
    public void setEnding_point(){
        transportation.setEnding_point("Sofia");
        assertEquals("Sofia", transportation.getEnding_point());
    }

    @Test
    public void getEnding_point(){
        assertEquals("Sofia", transportation.getEnding_point());
    }

    @Test
    public void setStarting_date(){
        transportation.setEnding_date(LocalDate.of(2020, 12, 12));
        assertEquals(LocalDate.of(2020, 12, 12), transportation.getEnding_date());
    }

    @Test
    public void getStarting_date(){
        assertEquals(LocalDate.of(2020, 12, 14), transportation.getEnding_date());
    }

    @Test
    public void setEnding_date(){
        transportation.setEnding_date(LocalDate.of(2020, 12, 14));
        assertEquals(LocalDate.of(2020, 12, 14), transportation.getEnding_date());
    }

    @Test
    public void getEnding_date(){
        assertEquals(LocalDate.of(2020, 12, 14), transportation.getEnding_date());
    }

    @Test
    public void setTypeOfLoad(){
        transportation.setTypeOfLoad(1);
        assertEquals(1, transportation.getTypeOfLoad());
    }

    @Test
    public void getTypeOfLoad(){
        assertEquals(1, transportation.getTypeOfLoad());
    }

    @Test
    public void setPrice(){
        transportation.setPrice(BigDecimal.valueOf(750.00));
        assertEquals(BigDecimal.valueOf(750.00), transportation.getPrice());
    }

    @Test
    public void getPrice(){
        assertEquals(BigDecimal.valueOf(750.00), transportation.getPrice());
    }

    @Test
    public void setCompany(){
        transportation.setCompany(transportation.getCompany());
        assertEquals(transportation.getCompany(), transportation.getCompany());
    }

    @Test
    public void getCompany(){
        assertEquals(transportation.getCompany(), transportation.getCompany());
    }

    @Test
    public void setEmployee(){
        transportation.setEmployee(transportation.getEmployee());
        assertEquals(transportation.getEmployee(), transportation.getEmployee());
    }

    @Test
    public void getEmployee(){
        assertEquals(transportation.getEmployee(), transportation.getEmployee());
    }

}
