/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DBContext {
        protected Connection connection;
    public DBContext()
    {
        try {
            String user = "sa";
            String pass = "123";  
            String url = "jdbc:sqlserver://localhost:1433;databaseName=LearningHub";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dao.DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void closeConnection() {
        //NHỚ LÀ KHÁC NULL THÌ MỚI ĐƯỢC CLOSE - NẾU KHÔNG SẼ BỊ LỖI 500 
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
