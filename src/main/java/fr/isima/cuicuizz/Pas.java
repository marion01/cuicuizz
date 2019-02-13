package fr.isima.cuicuizz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pas {
	
	   /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
    	// SQLite connection string
    	String url = "jdbc:sqlite:" + ClassLoader.getSystemClassLoader().getResource("database.db").getFile();
    	url=url.replace("/", "\\");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    
    /**
     * select all rows in the warehouses table
     */
    public void selectAll(){
        String sql = "SELECT Id, Question, ThemeId FROM Question";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("Id") +  "\t" + 
                                   rs.getString("Question") + "\t" +
                                   rs.getDouble("ThemeId"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
