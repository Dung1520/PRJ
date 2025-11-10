/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import dao.userReposistory;
import java.util.List;
import model.userInformation;

/**
 *
 * @author ADMIN
 */
public class userManager {
     userReposistory use = new userReposistory(); 
     
     //LOGIN 
    public boolean checkLogin(String userName, String password){
        List <userInformation> lst = use.getUserByUserNameAndPassword(userName, password); 
        return lst.isEmpty() ? false : true; 
    }
    
    public String checkRole(int id){
        String role = use.getRoleByID(id);
        if(role==null){
            return null; 
        }else 
            return role; 
    }
    
    // tạo user - đầy đủ thông tin, id đã được sql thêm tự động
    public boolean createUser(String username, String email, String password) {
        return use.createUser(username, email, password);
    }
    
    // list all user - không điều kiện, trả về 1 list
    public List<userInformation> getAllUsers() {
        return use.getAll_user();
    }
    // xóa user theo id
    public boolean deleteUser(int id) {
        return use.deleteUser(id);
    }
    
    // cập nhật thông tin user - toàn bộ thông tin
    public boolean updateUser(int id, String username, String email, String password, int role_id) {
        return use.updateUser(id, username, email, password, role_id);
    }
    
    public List<userInformation> getUserById(int id) {
        return  use.getUserById(id);
    }
    
}
