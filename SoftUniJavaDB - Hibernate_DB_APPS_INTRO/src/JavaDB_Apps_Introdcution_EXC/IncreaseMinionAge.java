package JavaDB_Apps_Introdcution_EXC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class IncreaseMinionAge {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        int[] line = Arrays.stream(bufferedReader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        PreparedStatement statementForWholeDB =
                connection.prepareStatement("SELECT m.name,m.age FROM minions AS m");

        PreparedStatement statementWhichUpdates =
                connection.prepareStatement("UPDATE minions m SET m.name = LOWER(m.name), m.age = m.age + 1 WHERE m.id = ?");


        for (int i = 0; i < line.length; i++) {
            statementWhichUpdates.setInt(1, line[i]);
            statementWhichUpdates.executeUpdate();

        }

        ResultSet resultSetWhichUpdates = statementForWholeDB.executeQuery();

        while (resultSetWhichUpdates.next()) {
            System.out.println(resultSetWhichUpdates.getString("name") + " " + resultSetWhichUpdates.getInt("age"));
        }
    }
}
