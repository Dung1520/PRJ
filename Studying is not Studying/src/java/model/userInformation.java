/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;

/**
 *
 * @author ADMIN
 */

/*  - sau add thêm validate vào setter rồi gọi vào constructor
[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[role_id] [int] NOT NULL,*/
public class userInformation {
       
    private String role_name; 
    private int id; 
    private String email; 
    private String password;
    private String userName; 

    public userInformation() {
    }

    public userInformation(String role_name, int id, String email, String password, String userName) {
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

    @Override
    public String toString() {
        return "userInformation{" + "role_id=" + role_name + ", id=" + id + ", email=" + email + ", password=" + password + ", userName=" + userName + '}';
    }
}
