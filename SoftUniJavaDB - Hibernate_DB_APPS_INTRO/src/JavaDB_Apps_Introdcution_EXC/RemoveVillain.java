package JavaDB_Apps_Introdcution_EXC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);


        int receivedId = Integer.parseInt(bufferedReader.readLine());

        PreparedStatement villainNameQuery =
                connection.prepareStatement("SELECT name FROM villains where id = ?");


        villainNameQuery.setInt(1, receivedId);

        String name = null;

        ResultSet nameSet = villainNameQuery.executeQuery();

        if (!nameSet.isBeforeFirst()) {
            System.out.println("No such villain was found");
        } else {

            while (nameSet.next()) {
                name = nameSet.getString("name");
            }

            PreparedStatement statementWhichDeletesVillain =
                    connection.prepareStatement("DELETE FROM villains WHERE id = ?");

            PreparedStatement statementWhichDeletesMinions =
                    connection.prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");

            statementWhichDeletesMinions.setInt(1, receivedId);
            int numberOfDeletedRecords = statementWhichDeletesMinions.executeUpdate();

            statementWhichDeletesVillain.setInt(1, receivedId);
            statementWhichDeletesVillain.executeUpdate();

            System.out.println(name + " was deleted");
            System.out.println(numberOfDeletedRecords + " minions released");
        }
    }
}
