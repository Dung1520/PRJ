/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class Validator {

    public Validator() {
    }
    //Chứa Regex + check inp string, double, String - check lỗi ở đây.
     // Patterns for web validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    // At least 8 chars, at least one letter and one digit
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");
    // Username: letters, digits, underscore, dot, min 3, max 50
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_.]{3,50}$");
    
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidUsername(String username) {
        if (username == null) return false;
        return USERNAME_PATTERN.matcher(username.trim()).matches();
    }

    public static boolean isPasswordMatch(String password, String confirm) {
        if (password == null || confirm == null) return false;
        return password.equals(confirm);
    }
    
    public static String validateRegistration(String username, String email,
        String password, String confirmPassword) {

    StringBuilder errors = new StringBuilder();

    // required
    if (username == null || username.trim().isEmpty()) {
        errors.append("Username là bắt buộc.\n");
    }
    if (email == null || email.trim().isEmpty()) {
        errors.append("Email là bắt buộc.\n");
    }
    if (password == null || password.isEmpty()) {
        errors.append("Password là bắt buộc.\n");
    }
    if (confirmPassword == null || confirmPassword.isEmpty()) {
        errors.append("Vui lòng xác nhận password.\n");
    }

    if (errors.length() > 0) {
        return errors.toString(); // trả luôn nếu thiếu trường bắt buộc
    }

    // username length & format
    if (!isValidUsername(username)) {
        errors.append("Username không hợp lệ (3-50 ký tự: chữ, số, '_' hoặc '.').\n");
    }

    // email format
    if (!isValidEmail(email)) {
        errors.append("Email không đúng định dạng.\n");
    }

    // password match
    if (!isPasswordMatch(password, confirmPassword)) {
        errors.append("Password và Confirm password không khớp.\n");
    } else {
        // password strength
        if (!isValidPassword(password)) {
            errors.append("Password phải có ít nhất 8 ký tự và chứa cả chữ và số.\n");
        }
    }
    return errors.toString(); // trả chuỗi chứa toàn bộ lỗi (rỗng nếu hợp lệ)
}

}
