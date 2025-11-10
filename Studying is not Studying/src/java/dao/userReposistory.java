/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.userInformation;

/**
 *
 * @author ADMIN
 */
public class userReposistory extends DBContext{
     
        //Get role 
    public String getRoleByID(int id){
        String query = "SELECT r.role_name\n" +
                        "FROM users u\n" +
                        "JOIN roles r ON u.role_id = r.role_id\n" +
                        "WHERE u.id = ?;" ;
        String rolename = null; 
        try {
            PreparedStatement p = connection.prepareStatement(query); 
            p.setInt(1, id);
            ResultSet re = p.executeQuery();
            while(re.next()){
                rolename = re.getString("role_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.userReposistory.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection();
        }
        return rolename; 
        }
        
        
    //getUser
    public List<userInformation> getUserByUserNameAndPassword(String userName, String passWord){
        List<userInformation> lst = new ArrayList<>();
        String query = "SELECT * FROM users u\n" +
                                "WHERE u.userName = ? AND u.password = ?;";
        try {
                   PreparedStatement prestate = connection.prepareStatement(query);
                   prestate.setString(1, userName); 
                   prestate.setString(2, passWord);
                   ResultSet re = prestate.executeQuery(); 
                   while(re.next()){ 
                       userInformation user = new userInformation(
                            re.getInt("role_id")==0?"admin":"user",
                            re.getInt("id"),
                            re.getString("email"),
                            re.getString("password"),
                            re.getString("username"));
                       lst.add(user); 
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(dao.userReposistory.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    //Đóng connection đến database:
                    closeConnection();
                }
                return lst;
    }
    
    //getAll
        //getUser
    public List<userInformation> getAll_user(){
        List<userInformation> lst = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
                   PreparedStatement prestate = connection.prepareStatement(query);
                   ResultSet re = prestate.executeQuery(); 
                   while(re.next()){ 
                       userInformation user = new userInformation(
                            re.getInt("role_id")==0?"admin":"user",
                            re.getInt("id"),
                            re.getString("email"),
                            re.getString("password"),
                            re.getString("username"));
                       lst.add(user); 
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(dao.userReposistory.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    //Đóng connection đến database:
                    closeConnection();
                }
                return lst;
    }
    
    //dùng để pre-fill form 
    public List <userInformation> getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List <userInformation> lst = new ArrayList<>(); 
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setInt(1, id);
            try (ResultSet re = p.executeQuery()) {
                if (re.next()) {
                    lst.add(new userInformation(
                            re.getInt("role_id")==0?"admin":"user",
                            re.getInt("id"),
                            re.getString("email"),
                            re.getString("password"),
                            re.getString("username")));
                }
                return lst; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.userReposistory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }    
    
    //crete user 
   public boolean createUser(String userName, String email, String password) {
    String checkSql = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
    String insertSql = "INSERT INTO [users] (username, email, password) VALUES (?, ?, ?)";

    try {
        // check trùng username/email
        try (PreparedStatement check = connection.prepareStatement(checkSql)) {
            check.setString(1, userName);
            check.setString(2, email);
            try (ResultSet rs = check.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // đã tồn tại
                }
            }
        }

        // insert
        try (PreparedStatement p = connection.prepareStatement(insertSql)) {
            p.setString(1, userName);
            p.setString(2, email);
            p.setString(3, password); 
            int affected = p.executeUpdate();
            if (affected == 0) {
                return false; 
            }
        }
 
        return true; 
    } catch (SQLException ex) {
        Logger.getLogger(dao.userReposistory.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    } finally {
        closeConnection();
    }
}   public static void main(String[] args) {
        userReposistory res = new userReposistory(); 
        boolean check = res.createUser("sonnhhe190862", "nguyenhieuson6@gmail.com", "Sonno112233");
        System.out.println(check);
    }


    //update user -- Bắt buộc phải để "selected" trong form update user để đầy đủ các thông tin trước. 
    public boolean updateUser(int id, String username, String email, String password, int role_id) {
    String sql = "UPDATE users SET username = ?, email = ?, password = ?";
    try (PreparedStatement p = connection.prepareStatement(sql)) {
        p.setString(1, username);
        p.setString(2, email);
        p.setString(3, password);

        int rows = p.executeUpdate(); // = Update = số rows affected
        return rows > 0; // true nếu có ít nhất 1 dòng được cập nhật
    } catch (SQLException ex) {
        Logger.getLogger(userReposistory.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    } finally {
        closeConnection();
    }
    }
        
    //delete user
    public boolean deleteUser(int id) {
    String sql = "DELETE FROM users WHERE id = ?";
    try (PreparedStatement p = connection.prepareStatement(sql)) {
        p.setInt(1, id);
        int rows = p.executeUpdate();
        return rows > 0;
    } catch (SQLException ex) {
        Logger.getLogger(userReposistory.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    } finally {
        closeConnection();
    }
    }
}
