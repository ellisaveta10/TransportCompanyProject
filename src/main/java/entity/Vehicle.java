package entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type_of_vehicle", nullable = false)
    private TypeOfVehicle typeOfVehicle;

    @ManyToOne
    private Company company;

    public Vehicle() {
    }

    public Vehicle(long id, TypeOfVehicle typeOfVehicle) {
        this.id = id;
        this.typeOfVehicle = typeOfVehicle;
    }

    public Vehicle(long id, TypeOfVehicle typeOfVehicle, Company company) {
        this.id = id;
        this.typeOfVehicle = typeOfVehicle;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", typeOfVehicle=" + typeOfVehicle +
                '}';
    }
}
