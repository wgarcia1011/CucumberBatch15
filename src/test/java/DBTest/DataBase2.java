package DBTest;

import java.sql.*;

public class DataBase2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";//java based database, database is my SQL, ip address and name of DataBase
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        try {
            //we need to establish the connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created for batch 15");
            //create a statement to send sql queries
            Statement statement = conn.createStatement();
            //  ResultSet rset= statement.executeQuery("select FirstName, LastName from person");
            ResultSet rset = statement.executeQuery("select firstname, lastname, age, city " +
                    "from person where city is not null;");
            //  while(rset.next()){
            //   String fName=rset.getString("FirstName");
            //  String lName=rset.getString("LastName");
            // System.out.println(fName+ " "+lName);


            //  }
            //print all the columns header values
            ResultSetMetaData metaData = rset.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                System.out.println(columnName);
            }
            System.out.println();
            //we want to loop through evert column and every row
            while (rset.next()){
                for (int i=1;i<+metaData.getColumnCount();i++){
                    String value= rset.getString(metaData.getColumnName(i));
                    System.out.println(value+" ");
                }
                //space
                System.out.println();
            }

            //resultsetMetaData -object that contains info about results, info such as in table how many columns are there,
            //name of the columns, rows, and number of rows


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
