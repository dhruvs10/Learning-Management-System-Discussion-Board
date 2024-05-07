import java.io.*;
import java.util.ArrayList;

/**
 * Project 5
 * <p>
 * The class is the User class for the discussion program between
 * teachers and students along with creating accounts. Every user, be it a student or a teacher
 * is an object of this class.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */

public class User {
    private String name;
    private String role; // it will always either be t or s
    private String username;
    private String password;
    private String grade;

    public User(String name, String role, String username, String password, String grade) {

        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
        return String.format("%s,%s,%s,%s,%s", name, role, username, password, grade);
    }

}



