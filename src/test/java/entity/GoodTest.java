package entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GoodTest {
    Good good;
    @BeforeEach
    public void setUp(){
        Transportation transportation = new Transportation();
        good = new Good(1, "water", BigDecimal.valueOf(500.00), transportation);
    }

    @Test
    public void setId(){
        good.setId(2);
        assertEquals(2, good.getId());
    }

    @Test
    public void getId(){
        assertEquals(1, good.getId());
    }

    @Test
    public void setName(){
        good.setName("water");
        assertEquals("water", good.getName());
    }

    @Test
    public void getName(){
        assertEquals("water", good.getName());
    }

    @Test
    public void setWeight(){
        good.setWeight(BigDecimal.valueOf(500.00));
        assertEquals(BigDecimal.valueOf(500.00), good.getWeight());
    }

    @Test
    public void getWeight(){
        assertEquals(BigDecimal.valueOf(500.00), good.getWeight());
    }

    @Test
    public void setTransportation(){
        Transportation transportation = new Transportation();
        good.setTransportation(transportation);
        assertEquals(good.getTransportation(), good.getTransportation());
    }

    @Test
    public void getTransportation(){
        assertEquals(good.getTransportation(), good.getTransportation());
    }
}
