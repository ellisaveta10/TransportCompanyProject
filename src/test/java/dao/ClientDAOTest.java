package dao;

import entity.Client;
import entity.Transportation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDAOTest {
    @Test
    @Order(1)
    public void saveClient(){
        Transportation transportation = TransportationDAO.getTransportation(1);
        Client client = new Client(6, "Ivan", transportation, true);
        ClientDAO.saveClient(client);
    }

    @Test
    @Order(2)
    public void saveClients(){
        List<Client> clientList = new ArrayList<>();
        Client client = ClientDAO.getClient(1);
        Client client2 = ClientDAO.getClient(2);
        clientList.add(client);
        clientList.add(client2);

        ClientDAO.saveClients(clientList);
    }

    @Test
    @Order(3)
    public void saveOrUpdateClient(){
        Transportation transportation = TransportationDAO.getTransportation(1);
        Client client = new Client(6, "Kalin", transportation, true);
        ClientDAO.saveOrUpdateClient(client);
    }

    @Test
    @Order(4)
    public void readClients(){
        ClientDAO.readClients();
    }

    @Test
    @Order(5)
    public void getClient(){
        Transportation transportation = TransportationDAO.getTransportation(1);
        Client client = new Client(6, "Ivan", transportation, true);
        ClientDAO.getClient(client.getId());
    }

    @Test
    @Order(6)
    void deleteClient(){
        Client client = new Client();
        client.setId(6);
        ClientDAO.deleteClient(client);
    }
}
