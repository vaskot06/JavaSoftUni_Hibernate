package JavaDB_Apps_Introdcution_EXC;

import JavaDB_Apps_Introduction_Lab.SalaryFromEmployees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);


        PreparedStatement statement = connection.prepareStatement("SELECT v.name, COUNT(mv.villain_id) AS \"counter\"\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains mv\n" +
                "on v.id = mv.villain_id\n" +
                "join minions m\n" +
                "on mv.minion_id = m.id\n" +
                "group by v.name\n" +
                "HAVING COUNT(mv.villain_id) > ?\n" +
                "ORDER BY v.name DESC\n" +
                "\n");

        statement.setInt(1, 15);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getInt("counter"));
        }



    }
}
