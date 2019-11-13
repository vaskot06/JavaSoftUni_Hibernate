import entities.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Engine implements Runnable {

    private final EntityManager entityManager;

    Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        //    this.containsEmployee();
        //    this.emplsWithOver50kSalary();
        //    this.employeesFromDepartment();
        //    this.addNewAddress();
        //    this.addressesWithEmpCount();
        //    this.getEmployeeWithProject();
        //    this.findLatest10Projects();
        //    this.increaseSalaries();
        //    this.removeTowns();   <- Finish this one
        //    this.findEmplByFirstName();
            this.employeesMaximumSalaries();

    }

    private void employeesMaximumSalaries() {
        final double MINIMUM_RANGE = 30000;
        final double MAXIMUM_RANGE = 70000;

        List<Department> departmentList =
                this.entityManager.createQuery("FROM Department", Department.class)
                .getResultList();


        Map<String, Set<Employee>> map = new LinkedHashMap<>();

        for (Department department : departmentList) {
                map.putIfAbsent(department.getName(), department.getEmployees());

        }

        Map<String, Double>  maxSalary = new LinkedHashMap<>();

        for (Map.Entry<String, Set<Employee>> entry : map.entrySet()) {
            String name = entry.getKey();
            Set<Employee> employeeSet = entry.getValue();

            double maxSalaryPerDepartment = 0;

            for (Employee employee : employeeSet) {
                if (employee.getSalary().doubleValue() > maxSalaryPerDepartment) {
                    maxSalaryPerDepartment = employee.getSalary().doubleValue();
                    maxSalary.put(name, maxSalaryPerDepartment);
                }
            }
        }

        for (Map.Entry<String, Double> entry : maxSalary.entrySet()) {
            double salaryMax = entry.getValue();

            if (salaryMax <= MINIMUM_RANGE || salaryMax >= MAXIMUM_RANGE) {
                System.out.printf("%s - %.2f\n", entry.getKey(), salaryMax);
            }

        }

    }

    private void findEmplByFirstName() {


        // 12.	Find Employees by First Name

        Scanner scanner = new Scanner(System.in);

        List<Employee> employeeList = this.entityManager.createQuery("FROM Employee", Employee.class).getResultList();



        List<Employee> matchedEmployees = new ArrayList<>();
        String patternForFirstName = scanner.nextLine();
        final String regex = patternForFirstName + "(.*)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        for (Employee employee : employeeList) {
            final Matcher matcher = pattern.matcher(employee.getFirstName());
            if (matcher.matches()) {
                matchedEmployees.add(employee);
            }
        }

        matchedEmployees.forEach(e-> System.out.printf("%s %s - %s - %s\n", e.getFirstName(),
                e.getLastName(), e.getDepartment(), e.getSalary()));

    }

    private void increaseSalaries() {

        // 10.	Increase Salaries

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE department = 1 OR department = 2 OR department = 4 OR department = 11", Employee.class)
                .getResultList();


        for (Employee employee : employees) {
            this.entityManager.getTransaction().begin();

            employee.setSalary(employee.getSalary().add(employee.getSalary().multiply(BigDecimal.valueOf(0.12))));
            this.entityManager.flush();
            this.entityManager.getTransaction().commit();
        }

        for (Employee employee : employees) {
            System.out.printf("%s %s ($%s)\n", employee.getFirstName(), employee.getLastName(), employee.getSalary().setScale(2));
        }

    }

    private void findLatest10Projects() {

        // 9. Find Latest 10 Projects

        List<Project> project =
                this.entityManager.createQuery("FROM Project ORDER BY startDate DESC, name", Project.class)
                        .setMaxResults(10).getResultList();

        List<Project> sortedProjects = project.stream().sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());


        for (Project sortedProject : sortedProjects) {
            System.out.printf("Project name:%s\n\t" +
                    "Project Description:%s\n\t" +
                    "Project Start Date:%s\n\t" +
                    "Project End Date:%s\n"
            ,sortedProject.getName(),sortedProject.getDescription(), sortedProject.getStartDate(), sortedProject.getEndDate());
        }

    }


    private void getEmployeeWithProject() {

       // 8. Get Employee with Project

        Scanner scanner = new Scanner(System.in);

        int idToFind = Integer.parseInt(scanner.nextLine());

        Employee employee = this.entityManager
                .createQuery("FROM Employee WHERE id =: idToFind", Employee.class)
                .setParameter("idToFind", idToFind)
                .getSingleResult();


        System.out.printf("%s %s - %s\n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

      List<Project> sortedProjects = employee.getProjects()
                .stream().sorted(Comparator.comparing(Project::getName))
              .collect(Collectors.toList());

        for (Project sortedProject : sortedProjects) {
            System.out.println("\t" + sortedProject.getName());
        }

    }

    private void addressesWithEmpCount() {

        // 7.	Addresses with Employee Count

        List<Object[]> addresses = this.entityManager
                .createNativeQuery("SELECT a.address_text, t.name, COUNT(e.employee_id) as 'counter'\n" +
                        "FROM addresses as a\n" +
                        "join employees e\n" +
                        "on a.address_id = e.address_id\n" +
                        "join towns t\n" +
                        "on a.town_id = t.town_id\n" +
                        "group by a.address_text\n" +
                        "order by counter DESC, t.town_id ASC\n" +
                        "LIMIT 10;")
                .getResultList();

        for (Object[] address : addresses) {
            System.out.printf("%s, %s - %s%n", address[0], address[1], address[2]);
        }

    }

    private void addNewAddress() {

        // 6.	Adding a New Address and Updating Employee

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();


        this.entityManager.getTransaction().begin();


        Town town = this.entityManager
                .createQuery("FROM Town WHERE id = 32", Town.class)
                .getSingleResult();


        Address address = new Address();
        address.setText("Vitoshka 13");
        address.setTown(town);
        this.entityManager.persist(address);


        Employee employee = this.entityManager
                .createQuery("FROM Employee  WHERE lastName =:input", Employee.class)
                .setParameter("input", input)
                .getSingleResult();


        employee.setAddress(address);

        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

    }

    private void employeesFromDepartment() {

        // 5.	Employees from Department

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE department_id = 6 ORDER BY salary ASC, id ASC", Employee.class)
                .getResultList();

        employees.forEach(e -> System.out.printf("%s %s from Research and Development - $%s\n",
                e.getFirstName(), e.getLastName(), e.getSalary()));

    }

    private void emplsWithOver50kSalary() {

        // 4.	Employees with Salary Over 50 000

        List<Employee> employee = this.entityManager
                .createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();

        employee.forEach(e -> System.out.println(e.getFirstName()));
    }

    private void containsEmployee() {

        // 3.	Contains Employee

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        entityManager.getTransaction().begin();

        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE CONCAT(firstName, ' ', lastName) = :name", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();

            System.out.println("Yes");

        } catch (Exception e) {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
    }







}
