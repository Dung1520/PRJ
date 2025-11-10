/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Users {
    private String role_name; 
    private int id; 
    private String email; 
    private String password;
    private String userName; 

    public Users() {
    }

    public Users(String role_name, int id, String email, String password, String userName) {
        this.role_name = role_name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
    
    
    public String getRole_name() {
        return role_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
