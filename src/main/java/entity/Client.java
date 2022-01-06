package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "clients")
    private List<Company> companies;

    @ManyToOne
    private Transportation transportation;

    @Column(name = "is_transportation_paid", nullable = false)
    private boolean isPaid;

    public Client() {
    }

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(long id, String name, Transportation transportation, boolean isPaid) {
        this.id = id;
        this.name = name;
        this.transportation = transportation;
        this.isPaid = isPaid;
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


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
