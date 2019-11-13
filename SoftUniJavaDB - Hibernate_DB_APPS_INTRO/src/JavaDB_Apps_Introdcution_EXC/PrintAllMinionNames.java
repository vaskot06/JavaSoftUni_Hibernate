package JavaDB_Apps_Introdcution_EXC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM minions");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> names = new ArrayList<>();

        while (resultSet.next()) {

            String name = resultSet.getString("name");
            names.add(name);
        }


        for (int i = 0; i < names.size() / 2; i++) {

            System.out.println(names.get(i));
            System.out.println(names.get(names.size() - i - 1));
        }
    }
}
