package DBTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataBaseTest3 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";//java based database, database is my SQL, ip address and name of DataBase
        String username = "syntax_hrm";
        String password = "syntaxhrm123";
        try {
            //we need to establish the connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created for batch 15");
            //create a statement to send sql queries
                Statement statement = conn.createStatement();
                String query="select * from person";
            ResultSet rset=statement.executeQuery(query);
            ResultSetMetaData rsmdata=rset.getMetaData();

            //extract data from resultset and store it in java data structure(jds)
            List<Map<String,String>> listFromRset=new ArrayList<>();
            while (rset.next()){
                 Map<String ,String>map=new LinkedHashMap<>();
                 //iterate over columns
                for(int i=1; i<=rsmdata.getColumnCount();i++){
                    //fetching key and value from the columns
                    String key=rsmdata.getColumnName(i);
                    String value= rset.getString(key);
                    map.put(key,value);
                }
                System.out.println(map);
                listFromRset.add(map);
            }







            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




