package dao;

import entity.Good;
import entity.Transportation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class GoodDAOTest {

    @Test
    @Order(1)
    public void saveGood(){
        Transportation transportation = TransportationDAO.getTransportation(1);
        Good good = new Good(6, "water", BigDecimal.valueOf(200.00), transportation);
        GoodDAO.saveGood(good);
    }

    @Test
    @Order(2)
    public void saveGoods(){
        List<Good> goodList = new ArrayList<>();
        Good good = GoodDAO.getGood(1);
        Good good2 = GoodDAO.getGood(2);
        goodList.add(good);
        goodList.add(good2);

        GoodDAO.saveGoods(goodList);
    }

    @Test
    @Order(3)
    public void saveOrUpdateGood(){
        Transportation transportation = TransportationDAO.getTransportation(1);
        Good good = new Good(6, "chips", BigDecimal.valueOf(200.00), transportation);
        GoodDAO.saveGood(good);
    }

    @Test
    @Order(4)
    public void readGoods(){
        GoodDAO.readGoods();
    }

    @Test
    @Order(5)
    public void getGood(){
        GoodDAO.getGood(2);
    }

    @Test
    @Order(6)
    void deleteGood(){
        Good good = new Good();
        good.setId(6);
        GoodDAO.deleteGood(good);
    }
}
