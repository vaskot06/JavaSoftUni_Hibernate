package JavaDB_Apps_Introduction_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class diabloDBProblem {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        PreparedStatement statement = connection.prepareStatement("SELECT u.user_name, u.first_name, u.last_name, COUNT(G.user_id) as \"count\"\n" +
                "FROM users AS u\n" +
                "JOIN users_games g\n" +
                "ON u.id = g.user_id\n" +
                "WHERE u.user_name = ?\n" +
                "GROUP BY u.user_name\n" +
                "\n");

        String username = bufferedReader.readLine();

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();



            if (!resultSet.isBeforeFirst()) {
                System.out.println("No such user exists");
            }else {

                while (resultSet.next()) {
                    System.out.printf("User: %s%n%s %s has played %d games",
                            resultSet.getString("user_name"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("count"));
                }

            }





    }
}
