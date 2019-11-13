package JavaDB_Apps_Introduction_Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SalaryFromEmployees {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", properties);

        PreparedStatement stmt = connection.prepareStatement("SELECT  first_name, last_name FROM employees WHERE salary > ?");
        String salary = scanner.nextLine();
        stmt.setDouble(1, Double.parseDouble(salary));

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
        }

    }
}
