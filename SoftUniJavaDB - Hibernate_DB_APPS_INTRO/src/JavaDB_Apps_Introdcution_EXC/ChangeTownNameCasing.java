package JavaDB_Apps_Introdcution_EXC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class ChangeTownNameCasing {

    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);


        PreparedStatement statementToModify = connection.prepareStatement("UPDATE towns as t\n" +
                "SET t.name = UPPER(t.name)\n" +
                "WHERE t.country = ?");


        String countryToChange = bufferedReader.readLine();

        statementToModify.setString(1, countryToChange);
        int resultsModified = statementToModify.executeUpdate();

        PreparedStatement statementForSelect = connection.prepareStatement("SELECT t.name FROM towns as t where t.country = ?");
        statementForSelect.setString(1, countryToChange);

        ResultSet resultSet = statementForSelect.executeQuery();

        int counter = 1;

        if (!resultSet.isBeforeFirst()) {
            System.out.println("No town names were affected.");
        }else {
            while (resultSet.next()) {
                if (counter == 1) {
                    System.out.printf("%d town names were affected.%n[", resultsModified);
                }
                if (counter != resultsModified) {
                    System.out.print(resultSet.getString("name") + ", ");
                } else {
                    System.out.println(resultSet.getString("name") + "]");
                }
                counter++;
            }
        }
        }


    }

