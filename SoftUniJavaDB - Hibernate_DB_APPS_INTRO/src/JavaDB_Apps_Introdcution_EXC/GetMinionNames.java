package JavaDB_Apps_Introdcution_EXC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);


        PreparedStatement statement = connection.prepareStatement("SELECT m.name ,m.age,v.name AS villain_name\n" +
                "FROM minions m\n" +
                "JOIN minions_villains mv\n" +
                "on m.id = mv.minion_id\n" +
                "JOIN villains v\n" +
                "on mv.villain_id = v.id\n" +
                "WHERE mv.villain_id = ?");

        int villain_id = Integer.parseInt(bufferedReader.readLine());

        statement.setInt(1, villain_id);
        ResultSet resultSet = statement.executeQuery();


        int rowCount = 0;


        if (!resultSet.isLast()) {
            resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        }

        if (!resultSet.isBeforeFirst()) {
            System.out.printf("No villain with ID %d exists in the database.", villain_id);
        } else {


            while (resultSet.next()) {
                System.out.println("Villain: " + resultSet.getString("villain_name"));
                break;
            }

            int counter = 1;
            while (resultSet.next()) {

                System.out.println(counter + ". " + resultSet.getString("name") + " " + resultSet.getInt("age"));

                counter++;
            }

        }


    }
}
