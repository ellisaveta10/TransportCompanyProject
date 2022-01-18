import dao.*;
import entity.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args){
        /** company */
        Company company = new Company();
        company.setId(1);
        company.setName("Biomet");

        CompanyDAO.saveCompany(company);

        List<Company> companies = Arrays.asList(new Company(2, "Etap"), new Company(3, "TransT"));

        CompanyDAO.saveCompanies(companies);

        companies = CompanyDAO.readCompanies();

        /** 7-a: sort by name and incomes */
        companies = CompanyDAO.sortCompaniesByNameAndIncomes();
        System.out.println("\n\n--------------------- Sort companies by name and incomes --------------------\n");
        companies.stream().forEach(System.out::println);
        System.out.println("\n\n");

        Employee asen = new Employee(1, "Asen", TypeOfQualification.FLAMMABLE, BigDecimal.valueOf(1200.), company);
        EmployeeDAO.saveEmployee(asen);

        Employee ivo = new Employee(2, "Ivo", TypeOfQualification.OVERSIZE_LOAD, BigDecimal.valueOf(1500), company);
        EmployeeDAO.saveEmployee(ivo);

        List<Employee> employees = List.of(new Employee(3, "Peter", TypeOfQualification.GREATER_THAN_12_PASSENGERS, BigDecimal.valueOf(1300), companies.get(0)));
        EmployeeDAO.saveEmployees(employees);

        Employee ana = new Employee(4, "Ana", TypeOfQualification.SPECIAL_LOAD, BigDecimal.valueOf(1100), companies.get(2));
        EmployeeDAO.saveEmployee(ana);

        Employee maria = new Employee(5, "Maria", TypeOfQualification.SPECIAL_LOAD, BigDecimal.valueOf(1200), companies.get(1));
        EmployeeDAO.saveEmployee(maria);

        employees = EmployeeDAO.readEmployees();

        //CompanyDAO.getCompanyEmployeesDTO(1).stream().forEach(System.out::println);
        /** 7-b: sort employees by their qualification and salary */
        employees = EmployeeDAO.sortEmployeeByQualificationAndSalary();
        System.out.println("\n           ============= Employees ============\n");
        System.out.println("""
                Qualifications are compared by their first letter,
                 salaries are compared from the biggest to the smallest for the same qualification

                """);
        employees.stream().forEach(System.out::println);
        System.out.println("\n\n");


        Transportation transportation1 = new Transportation(1, "Sofia", "Burgas",
                LocalDate.of(2018, 8, 21), LocalDate.of(2018, 8, 28), 1,
                BigDecimal.valueOf(428.00), company, asen);

        TransportationDAO.saveTransportation(transportation1);

        Transportation transportation2 = new Transportation(4, "Varna", "Sofia",
                LocalDate.of(2020, 8, 25), LocalDate.of(2020, 9, 25), 1,
                BigDecimal.valueOf(750.00), companies.get(1), ana);

        TransportationDAO.saveTransportation(transportation2);

        List<Transportation> transportations = Arrays.asList(new Transportation(2, "Ruse", "Plovdiv",
                        LocalDate.of(2021, 12, 9), LocalDate.of(2022, 1, 8), 2,
                        BigDecimal.valueOf(589.00), companies.get(companies.size() - 1), maria),
                new Transportation(3, "Svoge", "Varna", LocalDate.of(2021, 3, 17),
                        LocalDate.of(2022, 7, 3), 1, BigDecimal.valueOf(289.00), companies.get(0), maria));

        TransportationDAO.saveTransportations(transportations);

        Transportation transportation5 = new Transportation(5, "Varna", "Burgas", LocalDate.of(2021, 9, 7),
                LocalDate.of(2021, 9, 12), 1, BigDecimal.valueOf(720.00), company, ivo);

        TransportationDAO.saveTransportation(transportation5);


        transportations = TransportationDAO.readTransportations();

        //EmployeeDAO.getEmployeeTransportationsDTO(1).stream().forEach(System.out::println);

        //CompanyDAO.getCompanyTransportationsDTO(1).stream().forEach(System.out::println);


        /** Task 7: c) - sort and filter by destination */

        transportations = TransportationDAO.sortTransportationOrderByDestination();

        System.out.println("\n\n\n============Sort and filter transportations by destination=============\n\n\n");
        System.out.println("""
                Transportations are compared first by the name of the starting point(town),
                 then compared by the name of the ending point(town)

                """);
        transportations.stream().forEach(System.out::println);

        /** 8 - save data from transportations then show it in the console */

        FileWriter writer = null;
        String msg = "\n\n                                   DATA FOR TRANSPORTATIONS\n\n\n";
        try {
            writer = new FileWriter("files/TransportationsData.txt", true);

            writer.write(msg);

            for (Transportation tr : transportations) {
                writer.write(tr + System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }


        /** read from file */

        String currLine;
        try (FileReader fis = new FileReader("files/TransportationsData.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fis);
            while ((currLine = bufferedReader.readLine()) != null) {
                System.out.println(currLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** 9 - number of transportations for each company */
        for(Company c: companies){
            Long number = TransportationDAO.getNumberOfTransportationsByCompany(c);
            System.out.println("\n\n++++++++++++++++++ Number of transportations for companies ++++++++++++++++++++++++++++");
            System.out.println("\n" + c.getName() + " - " + number + " transportations\n");
        }


        /** 9 - sum of all transportations for a company */
        for(Company c: companies){
            BigDecimal s = TransportationDAO.sumOfTransportationsForOneCompany(c);
            System.out.println("\n\n++++++++++++++++++ Sum of the price of transportations for companies ++++++++++++++++++++++++++++");
            System.out.println("\n" + c.getName() + " - " + s + "$\n");
        }

        /** 9 - List of all employees and their transportations*/
        for(Employee e: employees){
            Long number = TransportationDAO.listOfEmployeesAndTheirTransportations(e);
            System.out.println("\n\n----------------------------- Employees and their transportations ---------------------------\n\n");
            System.out.println("\n" + e.getName() + " - " + number + " transportations\n");
        }

        /** 9 - get incomes of the companies for a period of time */
        for(Company c: companies){
            BigDecimal income = TransportationDAO.incomesOfTheCompanyFromTo(c);
            System.out.println("\n\n========================= Incomes of the companies =========================\n");
            System.out.println("\n" + c.getName() + " - " + income + "$\n");
        }

        /** 9 - get incomes of the companies for a period of time */
        for(Employee employee: employees){
            BigDecimal i = TransportationDAO.incomeForEmployee(employee);
            System.out.println("\n\n ++++++++++++++++ Incomes from an employee +++++++++++++++++++");
            System.out.println("\n" + employee.getName() + " - " + i + "$\n\n");
        }


        /** client */
        Client ivan = new Client(1, "Ivan", transportation1,true);
        ClientDAO.saveClient(ivan);

        Client georgi = new Client(2, "Georgi", transportation2, false);
        ClientDAO.saveClient(georgi);

        Client stefan = new Client(3, "Stefan", transportation5, true);
        ClientDAO.saveClient(stefan);

        List<Client> clients = Arrays.asList(new Client(4, "Darina", transportation2, true),
        new Client(5, "Simona", transportation1, false));

        ClientDAO.saveClients(clients);

        clients = ClientDAO.readClients();
        System.out.println("\n ============ Clients =========== \n");
        clients.stream().forEach(System.out::println);
        System.out.println("\n\n");

        //TransportationDAO.getTransportationClientsDTO(1).stream().forEach(System.out::println);

        Good good1 = new Good(1, "water", BigDecimal.valueOf(500.00), transportation1);
        GoodDAO.saveGood(good1);

        List<Good> goods = Arrays.asList(new Good(2, "milk", BigDecimal.valueOf(250.00), transportation2),
                new Good(3, "fruits", BigDecimal.valueOf(450.00), transportation5),
                new Good(4, "bread", BigDecimal.valueOf(502.00), transportation2),
                new Good(5, "paper", BigDecimal.valueOf(710.00), transportation1));
        GoodDAO.saveGoods(goods);

        goods = GoodDAO.readGoods();
        System.out.println("\n ============ Goods =========== \n");
        goods.stream().forEach(System.out::println);
        System.out.println("\n\n");

        //TransportationDAO.getTransportationGoodDTO(1).stream().forEach(System.out::println);

        Vehicle vehicle1 = new Vehicle(1, TypeOfVehicle.BUS, company);
        VehicleDAO.saveVehicle(vehicle1);

        Vehicle vehicle2 = new Vehicle(2, TypeOfVehicle.TRUCK, companies.get(0));
        VehicleDAO.saveVehicle(vehicle2);

        Vehicle vehicle3 = new Vehicle(3, TypeOfVehicle.BUS, companies.get(1));
        VehicleDAO.saveVehicle(vehicle3);

        Vehicle vehicle4 = new Vehicle(4, TypeOfVehicle.CISTERN, companies.get(2));
        VehicleDAO.saveVehicle(vehicle4);

        List<Vehicle> vehicles = Arrays.asList(new Vehicle(5, TypeOfVehicle.BUS, company),
                new Vehicle(6, TypeOfVehicle.CISTERN, companies.get(1)));

        VehicleDAO.saveVehicles(vehicles);

        vehicles = VehicleDAO.readVehicles();
        System.out.println("\n ============ Vehicles =========== \n");
        vehicles.stream().forEach(System.out::println);
        System.out.println("\n\n");

        //CompanyDAO.getCompanyVehiclesDTO(1).stream().forEach(System.out::println);

   }
}
