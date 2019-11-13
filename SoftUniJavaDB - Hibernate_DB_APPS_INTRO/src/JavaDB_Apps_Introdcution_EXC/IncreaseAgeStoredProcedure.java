package JavaDB_Apps_Introdcution_EXC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        int inputID = Integer.parseInt(bufferedReader.readLine());

        CallableStatement statement = connection.prepareCall("CALL usp_get_older(?)");

        statement.setInt(1,inputID);
        statement.executeQuery();

        PreparedStatement selectStatement = connection.prepareStatement("SELECT m.name, m.age FROM minions as m where m.id = ?");
        selectStatement.setInt(1,inputID);


        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getString("age"));
        }

    }
}
