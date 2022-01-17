package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @ManyToOne (fetch = FetchType.LAZY)
    //@ManyToOne
    private Transportation transportation;

    public Good() {
    }

    public Good(long id, String name, BigDecimal weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Good(long id, String name, BigDecimal weight, Transportation transportation) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.transportation = transportation;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
