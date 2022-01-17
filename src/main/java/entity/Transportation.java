package entity;

import javax.persistence.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "transportation")
public class Transportation implements Comparable<Transportation>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "starting_point")
    private String starting_point;

    @Column(name = "ending_point")
    private String ending_point;

    @Column(name = "starting_date")
    private LocalDate starting_date;

    @Column(name = "ending_date")
    private LocalDate ending_date;

    /** вид на товара - хора или стока
     * 1 - хора
     * 2 - стока
     * */
    //@Basic
    //@Enumerated(EnumType.STRING)
    @Column(name = "type_of_load", nullable = false)
    private long typeOfLoad;

    @Column(name = "price")
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

    @Override
    public int compareTo(Transportation transportation) {
        return this.starting_point.compareTo(transportation.starting_point);
    }

    public static Comparator<Transportation> CompareByDestinationStartingPoint =
            new Comparator<Transportation>() {
                @Override
                public int compare(Transportation t1, Transportation t2) {
                    return t1.starting_point.compareTo(t2.starting_point);
                }
            };

    public static Comparator<Transportation> CompareByDestinationEndingPoint =
            new Comparator<Transportation>() {
                @Override
                public int compare(Transportation t1, Transportation t2) {
                    return t1.ending_point.compareTo(t2.ending_point);
                }
            };

    public void writeInfoForTransportations(String filename){
        FileWriter fout = null;
        try{
            fout = new FileWriter((filename), true);
            fout.append(toString()).append(System.lineSeparator());
        }catch(FileNotFoundException e){
            System.out.println("File not found " + e);
        } catch (IOException e) {
            System.out.println("IOException " + e);
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException exception) {
                System.out.println(exception);
            }
        }
    }

    public static void readInfoForTransportations(String inputFile) {
        String currLine = null;
        try (FileReader fis = new FileReader(new File(inputFile))) {
            BufferedReader bufferedReader = new BufferedReader(fis);
            while ((currLine = bufferedReader.readLine()) != null) {
                System.out.println(currLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
