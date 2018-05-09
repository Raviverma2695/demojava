
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ravi Verma
 */
public class DB {
    public static int tableCreates() {
        int status=0;
        try {
            Connection c = getConnection();
            String sql = "create table e_student( id serial primary key, name varchar(30) not null, email varchar(30) not null unique,	password varchar(30) not null, mobile varchar(10) not null unique)";
            PreparedStatement ps = c.prepareStatement(sql);
            if(ps.execute()) status=1;
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return status;
    }
    public static Connection getConnection() {
        
        
        Connection connection = null;
        try {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (URISyntaxException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
}
